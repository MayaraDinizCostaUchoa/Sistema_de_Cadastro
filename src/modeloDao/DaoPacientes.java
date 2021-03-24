/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modeloBeans.BeansPacientes;
import modeloConection.ConectarNoBanco;

/**
 *
 * @author User
 */
public class DaoPacientes {
    ConectarNoBanco connBanco = new ConectarNoBanco();
    ConectarNoBanco conexaoPaciente = new ConectarNoBanco();
    BeansPacientes mod = new BeansPacientes();
    String nomePac;
    
    public void Salvar(BeansPacientes mod) throws SQLException{
        connBanco.conexao();
        try{
            PreparedStatement pst = connBanco.conn.prepareStatement("insert into pacientes(nome_paciente,data_de_nascimento_paciente,fisio_paciente,to_paciente,fono_paciente,psi_paciente,therasuit_paciente,musicoterapia_paciente,estimulacaovisual_paciente,filiacao_1,tel_filiacao_1,filiacao_2,tel_filiacao_2,parentesco_filiacao_1,parentesco_filiacao_2,dados_relatorio,cpf_paciente,rg_paciente,telefone_paciente,endereco_paciente,complemento_paciente,bairro_paciente,cidade_paciente,estado_paciente) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, mod.getNome());
            pst.setString(2, mod.getDatadenascimento());
            pst.setString(3, mod.getQntdFisio());
            pst.setString(4, mod.getQntdTO());
            pst.setString(5, mod.getQntdFono());
            pst.setString(6, mod.getQntdPsi());
            pst.setString(7, mod.getQntdTherasuit());
            pst.setString(8, mod.getQntdMusicoterapia());
            pst.setString(9, mod.getQntdEstVisual());
            pst.setString(10, mod.getFiliacao_1());
            pst.setString(11, mod.getTel_fil_1());
            pst.setString(12, mod.getFiliacao_2());
            pst.setString(13, mod.getTel_fil_2());
            pst.setString(14, mod.getParentesco_1());
            pst.setString(15, mod.getParentesco_2());
            pst.setString(16, mod.getDados());
            pst.setString(17, mod.getCpf());
            pst.setString(18, mod.getRg());
            pst.setString(19, mod.getTelefone());
            pst.setString(20, mod.getEndereço());
            pst.setString(21, mod.getComplemento());
            pst.setString(22, mod.getBairro());
            pst.setString(23, mod.getCidade());
            pst.setString(24, mod.getEstado());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados!/nERRO:" + ex);
        }
            
        connBanco.desconecta();
    }
    
    public void Editar(BeansPacientes mod){
        connBanco.conexao();
        try {
            PreparedStatement pst =  connBanco.conn. prepareStatement("update pacientes set nome_paciente=?,data_de_nascimento_paciente=?,fisio_paciente=?,to_paciente=?,fono_paciente=?,psi_paciente=?,therasuit_paciente=?,musicoterapia_paciente=?,estimulacaovisual_paciente=?,filiacao_1=?,tel_filiacao_1=?,filiacao_2=?,tel_filiacao_2=?,parentesco_filiacao_1=?,parentesco_filiacao_2=?,dados_relatorio=?,cpf_paciente=?,rg_paciente=?,telefone_paciente=?,endereco_paciente=?,complemento_paciente=?,bairro_paciente=?,cidade_paciente=?,estado_paciente=? where cod_paciente=?");
            pst.setString(1, mod.getNome());
            pst.setString(2, mod.getDatadenascimento());
            pst.setString(3, mod.getQntdFisio());
            pst.setString(4, mod.getQntdTO());
            pst.setString(5, mod.getQntdFono());
            pst.setString(6, mod.getQntdPsi());
            pst.setString(7, mod.getQntdTherasuit());
            pst.setString(8, mod.getQntdMusicoterapia());
            pst.setString(9, mod.getQntdEstVisual());
            pst.setString(10, mod.getFiliacao_1());
            pst.setString(11, mod.getTel_fil_1());
            pst.setString(12, mod.getFiliacao_2());
            pst.setString(13, mod.getTel_fil_2());
            pst.setString(14, mod.getParentesco_1());
            pst.setString(15, mod.getParentesco_2());
            pst.setString(16, mod.getDados());
            pst.setString(17, mod.getCpf());
            pst.setString(18, mod.getRg());
            pst.setString(19, mod.getTelefone());
            pst.setString(20, mod.getEndereço());
            pst.setString(21, mod.getComplemento());
            pst.setString(22, mod.getBairro());
            pst.setString(23, mod.getCidade());
            pst.setString(24, mod.getEstado());
            pst.setInt(25, mod.getCodigo());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na alteração dos dados!/nERRO:" + ex);
        }
        
    }
    
    public void Excluir(BeansPacientes mod){
        connBanco.conexao();
        try {
            PreparedStatement pst = connBanco.conn.prepareStatement("delete from pacientes where cod_paciente=?");
            pst.setInt(1, mod.getCodigo());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir dos dados!/nERRO:" + ex);
        }
        
        connBanco.desconecta();
    }
    
    public BeansPacientes buscaPacientes(BeansPacientes mod){
        connBanco.conexao();
        connBanco.executaSQL("select *from pacientes where nome_paciente like'%" + mod.getPesquisar()+"%'");
        try {
            connBanco.rs.first();
            buscaNomePaciente(connBanco.rs.getString("cod_paciente"));
            mod.setCodigo(connBanco.rs.getInt("cod_paciente"));
            mod.setNome(connBanco.rs.getString("nome_paciente"));
            mod.setDatadenascimento(connBanco.rs.getString("data_de_nascimento_paciente"));
            mod.setQntdFisio(connBanco.rs.getString("fisio_paciente"));
            mod.setQntdTO(connBanco.rs.getString("to_paciente"));
            mod.setQntdFono(connBanco.rs.getString("fono_paciente"));
            mod.setQntdPsi(connBanco.rs.getString("psi_paciente"));
            mod.setQntdTherasuit(connBanco.rs.getString("therasuit_paciente"));
            mod.setQntdMusicoterapia(connBanco.rs.getString("musicoterapia_paciente"));
            mod.setQntdEstVisual(connBanco.rs.getString("estimulacaovisual_paciente"));
            mod.setFiliacao_1(connBanco.rs.getString("filiacao_1"));
            mod.setParentesco_1(connBanco.rs.getString("parentesco_filiacao_1"));
            mod.setTel_fil_1(connBanco.rs.getString("tel_filiacao_1"));
            mod.setFiliacao_2(connBanco.rs.getString("filiacao_2"));
            mod.setParentesco_2(connBanco.rs.getString("parentesco_filiacao_2"));
            mod.setTel_fil_2(connBanco.rs.getString("tel_filiacao_2"));
            mod.setDados(connBanco.rs.getString("dados_relatorio"));
            mod.setCpf(connBanco.rs.getString("cpf_paciente"));
            mod.setRg(connBanco.rs.getString("rg_paciente"));
            mod.setEndereço(connBanco.rs.getString("endereco_paciente"));
            mod.setComplemento(connBanco.rs.getString("complemento_paciente"));
            mod.setBairro(connBanco.rs.getString("bairro_paciente"));            
            mod.setCidade(connBanco.rs.getString("cidade_paciente"));            
            mod.setEstado(connBanco.rs.getString("estado_paciente"));                        
            mod.setTelefone(connBanco.rs.getString("telefone_paciente"));
            mod.setNome(nomePac);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar paciente!/nERRO:" + ex);
        }      
        connBanco.desconecta();
        return mod;
    }
    
    public void buscaNomePaciente(String nom){
        conexaoPaciente.conexao();
        conexaoPaciente.executaSQL("select * from pacientes where cod_paciente=" + nom + "");
        try{
            conexaoPaciente.rs.first();
            nomePac = conexaoPaciente.rs.getString("nome_paciente");
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao buscar o codigo!\nERRO:" + ex);
        }   
        connBanco.desconecta();
    }
}
