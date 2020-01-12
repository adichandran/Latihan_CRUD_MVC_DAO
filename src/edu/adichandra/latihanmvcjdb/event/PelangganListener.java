/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.adichandra.latihanmvcjdb.event;

import edu.adichandra.latihanmvcjdb.entity.Pelanggan;
import edu.adichandra.latihanmvcjdb.model.PelangganModel;

/**
 *
 * @author ACER
 * NAMA                 : Adi Chandra Nugraha
 * KELAS                : IF-1
 * NIM                  : 10118015
 */
public interface PelangganListener {
    
    public void onChange(PelangganModel model);
    public void oninSert(Pelanggan pelanggan);
    public void onDelete();
    public void onUpdate(Pelanggan model);
    
}
