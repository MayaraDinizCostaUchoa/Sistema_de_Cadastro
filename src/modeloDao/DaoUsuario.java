/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modeloBeans.BeansUsuario;
import modeloConection.ConectarNoBanco;

/**
 *
 * @author User
 */
public class DaoUsuario {
    ConectarNoBanco connBanco = new ConectarNoBanco();
    ConectarNoBanco conectarUsu = new ConectarNoBanco();
    BeansUsuario mod = new BeansUsuario();
    String nomeUsu;
    
    public void Salvar(BeansUsuario mod) throws SQLException{
        connBanco.conexao();
        try{
            PreparedStatement pst = connBanco.conn.prepareStatement("insert into usuarios(nome_usuario,senha_usuario,tipo_usuario) values (?,?,?)");
            pst.setString(1, mod.getNomeUsuario());
            pst.setString(2, mod.getSenhaUsuario());
            pst.setString(3, mod.getTipoUsuario());            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Usuário inseridos com sucesso!");
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao inserir usuário!/nERRO:" + ex);
        }
            
        connBanco.desconecta();
    }
    
    public void Editar(BeansUsuario mod){
        connBanco.conexao();
        try {
            PreparedStatement pst =  connBanco.conn.prepareStatement("update usuarios set nome_usuario=?,senha_usuario=?,tipo_usuario=? where cod_usuario=?");
            pst.setString(1, mod.getNomeUsuario());
            pst.setString(2, mod.getSenhaUsuario());
            pst.setString(3, mod.getTipoUsuario());
            pst.setInt(4, mod.getCodUsuario());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na alteração do usuário!/nERRO:" + ex);
        }
        
    }
    
    public void Excluir(BeansUsuario mod){
        connBanco.conexao();
        try {
            PreparedStatement pst = connBanco.conn.prepareStatement("delete from usuarios where cod_usuario=?");
            pst.setInt(1, mod.getCodUsuario());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir o usuário!/nERRO:" + ex);
        }
        
        connBanco.desconecta();
    }
    
    public BeansUsuario buscaUsuario(BeansUsuario mod){
        connBanco.conexao();
        connBanco.executaSQL("select *from usuarios where nome_usuario like'%" + mod.getPesquisar()+"%'");
        try {
            connBanco.rs.first();
            buscaNomeUsuario(connBanco.rs.getInt("cod_usuario"));
            mod.setCodUsuario(connBanco.rs.getInt("cod_usuario"));
            mod.setNomeUsuario(connBanco.rs.getString("nome_usuario"));
            mod.setTipoUsuario(connBanco.rs.getString("tipo_usuario"));            
            mod.setNomeUsuario(nomeUsu);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");
        }      
        connBanco.desconecta();
        return mod;
    }
    
    public void buscaNomeUsuario(int nom){
        conectarUsu.conexao();
        conectarUsu.executaSQL("select * from usuarios where cod_usuario=" + nom + "");
        try{
            conectarUsu.rs.first();
            nomeUsu = conectarUsu.rs.getString("nome_usuario");
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao buscar o codigo!\nERRO:" + ex);
        }   
        conectarUsu.desconecta();
    }
    
    
}
