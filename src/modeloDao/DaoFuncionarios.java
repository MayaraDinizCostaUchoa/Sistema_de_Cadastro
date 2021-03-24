/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDao;

import modeloConection.ConectarNoBanco;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeloBeans.BeansFuncionarios;

/**
 *
 * @author User
 */
public class DaoFuncionarios {
    
    ConectarNoBanco connBanco = new ConectarNoBanco();
    ConectarNoBanco conectarFunc = new ConectarNoBanco();
    BeansFuncionarios mod = new BeansFuncionarios();
    String nomeFunc;
    
    public void Salvar(BeansFuncionarios mod) throws SQLException{
        connBanco.conexao();
        try{
            PreparedStatement pst = connBanco.conn.prepareStatement("insert into funcionarios(nome_funcionario,telefone_funcionario, rg_funcionario, cpf_funcionario, data_de_nascimento, profissao, conselho) values (?,?,?,?,?,?,?)");
            pst.setString(1, mod.getNome());
            pst.setString(2, mod.getTelefone());
            pst.setString(3, mod.getRg());
            pst.setString(4, mod.getCpf());
            pst.setString(5, mod.getDatadenascimento());
            pst.setString(6, mod.getProfissao());
            pst.setString(7, mod.getConselho());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados!/nERRO:" + ex);
        }
            
        connBanco.desconecta();
    }
    
    public void Editar(BeansFuncionarios mod){
        connBanco.conexao();
        try {
            PreparedStatement pst =  connBanco.conn. prepareStatement("update funcionarios set nome_funcionario=?,telefone_funcionario=?,rg_funcionario=?,cpf_funcionario=?,data_de_nascimento=?,profissao=?,conselho=? where cod_funcionario=?");
            pst.setString(1, mod.getNome());
            pst.setString(2, mod.getTelefone());
            pst.setString(3, mod.getRg());
            pst.setString(4, mod.getCpf());
            pst.setString(5, mod.getDatadenascimento());
            pst.setString(6, mod.getProfissao());
            pst.setString(7, mod.getConselho());
            pst.setInt(8, mod.getCodigo());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na alteração dos dados!/nERRO:" + ex);
        }
        
    }
    
    public void Excluir(BeansFuncionarios mod){
        connBanco.conexao();
        try {
            PreparedStatement pst = connBanco.conn.prepareStatement("delete from funcionarios where cod_funcionario=?");
            pst.setInt(1, mod.getCodigo());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir dos dados!/nERRO:" + ex);
        }
        
        connBanco.desconecta();
    }
    
    public BeansFuncionarios buscaFuncionarios(BeansFuncionarios mod){
        connBanco.conexao();
        connBanco.executaSQL("select *from funcionarios where nome_funcionario like'%" + mod.getPesquisar()+"%'");
        try {
            connBanco.rs.first();
            buscaNomeFuncionario(connBanco.rs.getInt("cod_funcionario"));
            mod.setCodigo(connBanco.rs.getInt("cod_funcionario"));
            mod.setNome(connBanco.rs.getString("nome_funcionario"));
            mod.setTelefone(connBanco.rs.getString("telefone_funcionario"));
            mod.setRg(connBanco.rs.getString("rg_funcionario"));
            mod.setCpf(connBanco.rs.getString("cpf_funcionario"));
            mod.setDatadenascimento(connBanco.rs.getString("data_de_nascimento"));
            mod.setProfissao(connBanco.rs.getString("profissao"));
            mod.setConselho(connBanco.rs.getString("conselho"));
            mod.setNome(nomeFunc);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Médico não cadastrado!");
        }      
        connBanco.desconecta();
        return mod;
    }
    
    public void buscaNomeFuncionario(int nom){
        conectarFunc.conexao();
        conectarFunc.executaSQL("select * from funcionarios where cod_funcionario=" + nom + "");
        try{
            conectarFunc.rs.first();
            nomeFunc = conectarFunc.rs.getString("nome_funcionario");
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao buscar o codigo!\nERRO:" + ex);
        }   
        conectarFunc.desconecta();
    }
    
}
