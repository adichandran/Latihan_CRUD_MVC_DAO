/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.adichandra.latihanmvcjdb.main;

import edu.adichandra.latihanmvcjdb.database.KingsbarbershopDatabase;
import edu.adichandra.latihanmvcjdb.entity.Pelanggan;
import edu.adichandra.latihanmvcjdb.error.PelangganException;
import edu.adichandra.latihanmvcjdb.service.PelangganDao;
import edu.adichandra.latihanmvcjdb.view.MainViewPelanggan;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author ACER
 * NAMA                 : Adi Chandra Nugraha
 * KELAS                : IF-1
 * NIM                  : 10118015
 */
public class LatihanCRUDMVCDAO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, PelangganException {
        // TODO code application logic here
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainViewPelanggan pelanggan = new MainViewPelanggan();
                try {
                    pelanggan.loadDatabase();
                    pelanggan.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(LatihanCRUDMVCDAO.class.getName()).log(Level.SEVERE, null, ex);
                } catch (PelangganException ex) {
                    Logger.getLogger(LatihanCRUDMVCDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }
}
