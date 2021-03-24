/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modeloBeans.BeansAgendamento;
import modeloBeans.BeansPacientes;
import modeloConection.ConectarNoBanco;

/**
 *
 * @author User
 */
public class DaoAgendamento {
    ConectarNoBanco connBanco = new ConectarNoBanco();    
    BeansAgendamento mod = new BeansAgendamento();
    
    
    public void Salvar(BeansAgendamento mod) throws SQLException{
        connBanco.conexao();
        try{
            PreparedStatement pst = connBanco.conn.prepareStatement("insert into agendamento(agenda_codpac,agenda_terapia,agenda_dia,agenda_horario,agenda_codfuncionario) values (?,?,?,?,?)");
            pst.setInt(1, mod.getCodpac());
            pst.setString(2, mod.getTerapia());
            pst.setString(3, mod.getDia());
            pst.setString(4, mod.getHora());
            pst.setInt(5, mod.getCodfuncionario());            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados!/nERRO:" + ex);
        }
            
        connBanco.desconecta();
    }
    
    
    
    
}
