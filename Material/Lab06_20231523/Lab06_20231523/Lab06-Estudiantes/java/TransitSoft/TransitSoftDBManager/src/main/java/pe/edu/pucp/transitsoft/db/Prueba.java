/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.transitsoft.db;

import java.sql.Connection;

/**
 *
 * @author santi
 */
public class Prueba {
    
    public static void main(String[] args) {
        
        Connection con = DBManager.getInstance().getConnection();
        
        if(con != null){
            System.out.println("Conexion exitosa");
        }
        else
            System.out.println("Conexion fallida");


        
    }
    
}
