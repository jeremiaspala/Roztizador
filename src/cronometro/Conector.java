/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cronometro;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jpalazzesi
 */
public class Conector {
    String url = System.getProperty("user.dir")+"\\cronometro.db";
    Connection connect;
   
    public void connect(){
        try {
             Class.forName("org.sqlite.JDBC");
        }catch (Exception e){
        }
    try {
        connect = DriverManager.getConnection("jdbc:sqlite:"+url);
        if (connect!=null) {
            System.out.println("Conectado");
        }
    }catch (SQLException ex) {
        System.err.println("No se ha podido conectar a la base de datos\n"+ex.getMessage());
    }
   }
    public void close(){
           try {
               connect.close();
           } catch (SQLException ex) {
               Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    
    //Métodos Guardar
    public void setJuego(String juego, String path){
        try {
            PreparedStatement st = connect.prepareStatement("insert into juegos(juego, path, tiempo) values (?,?, ?)");
            st.setString(1, juego);
            st.setString(2, path);
            st.setString(3, "00:00:00");
            st.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    //Métodos pedir archivos
    public void getJuegos(JTable tabla){
        try{
            PreparedStatement st = connect.prepareStatement("select * from juegos");
            ResultSet re = st.executeQuery();
                DefaultTableModel mo;
                String titulos[] = {"ID", "Juego", "Tiempo", "Path"};
                mo = new DefaultTableModel(null, titulos);
                String fila[] = new String[4];
                while(re.next()){
                    fila[0] = re.getString("id");
                    fila[1] = re.getString("juego");
                    fila[2] = re.getString("tiempo");
                    fila[3] = re.getString("path");
                    mo.addRow(fila);
                }
                tabla.setModel(mo);
        }catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    //Método para eliminar
    public void delJuegos(int id){
        try {
            PreparedStatement st = connect.prepareStatement("delete from juegos where id = ?");
            st.setInt(1, id);
            st.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    //Método para Actualizar
    public void setTiempo(int id, String tiempo){
        try {
            PreparedStatement st = connect.prepareStatement("update juegos set tiempo = ? where id = ?");
            st.setString(1, tiempo);
            st.setInt(2, id);
            st.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


}
