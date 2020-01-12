/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.adichandra.latihanmvcjdb.database;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import edu.adichandra.latihanmvcjdb.impl.PelangganDaoImpl;
import edu.adichandra.latihanmvcjdb.service.PelangganDao;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author ACER
 * NAMA                 : Adi Chandra Nugraha
 * KELAS                : IF-1
 * NIM                  : 10118015
 */
public class KingsbarbershopDatabase {
    
    private static Connection connection;
    private static PelangganDao pelangganDao;
    
    public static Connection geConnection() throws SQLException{
        if (connection==null) {
            
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setURL("jdbc:mysql://localhost:3306/kingsbarbershop");
            dataSource.setUser("root");
            dataSource.setPassword("");
            connection = dataSource.getConnection();
        }
        
        
        return connection;
    }
    
    public static PelangganDao getPelangganDao() throws SQLException{
        
        if (pelangganDao==null) {
            pelangganDao = new PelangganDaoImpl(geConnection());
        }
        
        return pelangganDao;
    }
}
