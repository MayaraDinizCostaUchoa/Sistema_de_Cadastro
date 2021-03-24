/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloConection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ConectarNoBanco {
    
    public Statement stm;
    public ResultSet rs;
    private String driver = "org.postgresql.Driver";
    private String caminho = "jdbc:postgresql://localhost:5432/projetoclinica";
    private String usuario = "postgres";
    private String senha = "11011989";
    public Connection conn;
    
    public void conexao(){
        try {//tentativa inicial
            System.setProperty("jdbc.Drivers", driver); //seta a propriedade do driver de conexao
            conn = DriverManager.getConnection(caminho, usuario, senha); //realiza a conexao com o banco de dados
            //JOptionPane.showMessageDialog(null, "Conectado com sucesso!"); //imprime uma caixa de mensagens
        } catch (SQLException ex) {//excessão
            JOptionPane.showMessageDialog(null, "Erro de conexão!\n Erro:" + ex.getMessage());
        }
    }
    
    
    public void executaSQL(String sql){
        try {
            stm = conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
            rs =  stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de ExecutaSQL!\n Erro:" + ex.getMessage());
        }
    }
    
    public void desconecta(){
         try {
            conn.close();//fecha a conexão
            //JOptionPane.showMessageDialog(null, "Desconectado com sucesso!"); //imprime uma caixa de mensagens
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão!\n Erro:" + ex.getMessage());
        }
    }
}
