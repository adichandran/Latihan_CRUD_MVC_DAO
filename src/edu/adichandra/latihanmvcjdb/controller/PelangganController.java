/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.adichandra.latihanmvcjdb.controller;

import edu.adichandra.latihanmvcjdb.model.PelangganModel;
import edu.adichandra.latihanmvcjdb.view.PelangganView;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 * NAMA                 : Adi Chandra Nugraha
 * KELAS                : IF-1
 * NIM                  : 10118015
 */
public class PelangganController {
    
    private PelangganModel model;

    public void setModel(PelangganModel model) {
        this.model = model;
    }
    
    public void resetPelanggan(PelangganView view){
        model.resetPelanggan();
    }
    
    public void insertPelanggan(PelangganView view){
        String nama = view.getTxtnama().getText();
        String alamat = view.getTxtalamat().getText();
        String telepon = view.getTxttelepon().getText();
        String email = view.getTxtemail().getText();
        
        if (nama.trim().equals("")) {
            JOptionPane.showMessageDialog(view, "Nama Tidak Boleh Kosong");
        } else if (nama.length()>255) {
            JOptionPane.showMessageDialog(view, "Nama Tidak Boleh lebih dari 255 karakter");
        } else if (telepon.length()>12) {
            JOptionPane.showMessageDialog(view, "Nomot telepon tidak boleh lebih dari 12 digit");
        } else if (!email.contains("@")|| !email.contains(".")) {
            JOptionPane.showMessageDialog(view, "Email tidak valid");
        } else {
            model.setNama(nama);
            model.setAlamat(alamat);
            model.setTelepon(telepon);
            model.setEmail(email);
        
        
            try {
                model.insertPelanggan();
                JOptionPane.showMessageDialog(view, "Pelanggan Berhasil Ditambahkan");
                model.resetPelanggan();
            } catch (Throwable throwable) {
                JOptionPane.showMessageDialog(view, new Object[]{"Terjadi Error di database dengan pesan", throwable.getMessage()});
            }
        }
    }
    
    public void updatePelanggan(PelangganView view){
        
        if (view.getTablePelangan().getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(view, "Silahkan seleksi baris data yang akan di ubah");
            return;
        }
        
        Integer id = Integer.parseInt(view.getTxtid().getText());
        
        String nama = view.getTxtnama().getText();
        String alamat = view.getTxtalamat().getText();
        String telepon = view.getTxttelepon().getText();
        String email = view.getTxtemail().getText();
        
        if (nama.trim().equals("")) {
            JOptionPane.showMessageDialog(view, "Nama Tidak Boleh Kosong");
        } else if (nama.length()>255) {
            JOptionPane.showMessageDialog(view, "Nama Tidak Boleh lebih dari 255 karakter");
        } else if (telepon.length()>12) {
            JOptionPane.showMessageDialog(view, "Nomot telepon tidak boleh lebih dari 12 digit");
        } else if (!email.contains("@")|| !email.contains(".")) {
            JOptionPane.showMessageDialog(view, "Email tidak valid");
        } else {
            
            model.setId(id);
            model.setNama(nama);
            model.setAlamat(alamat);
            model.setTelepon(telepon);
            model.setEmail(email);
        
        
            try {
                model.updatePelanggan();
                JOptionPane.showMessageDialog(view, "Pelanggan Berhasil Di ubah");
                model.resetPelanggan();
            } catch (Throwable throwable) {
                JOptionPane.showMessageDialog(view, new Object[]{"Terjadi Error di database dengan pesan", throwable.getMessage()});
            }
        }
    }
    
    public void deletePelanggan(PelangganView view){
        if (view.getTablePelangan().getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(view, "Silahkan seleksi baris data yang akan di hapus");
            return;
        }
        
        if (JOptionPane.showConfirmDialog(view, "Anda Yakin Akan Menghapus ?") == JOptionPane.OK_OPTION) {
            Integer id = Integer.parseInt(view.getTxtid().getText());
            model.setId(id);
            
            try {
                model.deletePelanggan();
                JOptionPane.showMessageDialog(view, "Pelanggan Berhasil Di hapus");
                model.resetPelanggan();
            } catch (Throwable throwable) {
                JOptionPane.showMessageDialog(view, new Object[]{"Terjadi Error di database dengan pesan", throwable.getMessage()});
        }
        }
        
        
    }
    
}
