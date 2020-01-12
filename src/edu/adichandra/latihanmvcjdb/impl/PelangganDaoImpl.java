/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.adichandra.latihanmvcjdb.impl;

import edu.adichandra.latihanmvcjdb.entity.Pelanggan;
import edu.adichandra.latihanmvcjdb.error.PelangganException;
import edu.adichandra.latihanmvcjdb.service.PelangganDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 * NAMA                 : Adi Chandra Nugraha
 * KELAS                : IF-1
 * NIM                  : 10118015
 */
public class PelangganDaoImpl implements PelangganDao{
    
    private Connection connection;
    
    private final String insertPelanggan = "insert into pelanggan (nama,alamat,telepon,email) values (?,?,?,?)";
    
    private final String updatePelanggan = "update pelanggan set nama=?,alamat=?,telepon=?,email=? where id=?";
    
    private final String deletePelanggan = "delete from pelanggan where id=?";
    
    private final String getById = "select * from pelanggan where id=?";
    
    private final String getByeEmail = "select * from pelanggan where email=?";
    
    private final String selectAll = "select * from pelanggan";
    
    public PelangganDaoImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insertPelanggan(Pelanggan pelanggan) throws PelangganException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            
            statement = connection.prepareStatement(insertPelanggan,  statement.RETURN_GENERATED_KEYS);
            statement.setString(1, pelanggan.getNama());
            statement.setString(2, pelanggan.getAlamat());
            statement.setString(3, pelanggan.getTelepon());
            statement.setString(4, pelanggan.getEmail());
            statement.executeUpdate();
            
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                pelanggan.setId(result.getInt(1));
            }
            
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new PelangganException(e.getMessage());
        } finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement!= null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    @Override
    public void updatePelanggan(Pelanggan pelanggan) throws PelangganException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            
            statement = connection.prepareCall(updatePelanggan);
            statement.setString(1, pelanggan.getNama());
            statement.setString(2, pelanggan.getAlamat());
            statement.setString(3, pelanggan.getTelepon());
            statement.setString(4, pelanggan.getEmail());
            statement.setInt(5,pelanggan.getId());
            statement.executeUpdate();
            
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new PelangganException(e.getMessage());
        } finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement!= null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    @Override
    public void deletePelanggan(Integer id) throws PelangganException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            
            statement = connection.prepareStatement(deletePelanggan);
            statement.setInt(1,id);
            statement.executeUpdate();
            
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new PelangganException(e.getMessage());
        } finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement!= null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    @Override
    public Pelanggan getPelanggan(Integer id) throws PelangganException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            
            statement = connection.prepareCall(getById);
            statement.setInt(1,id);
            
            ResultSet result = statement.executeQuery();
            Pelanggan pelanggan = null;
            
            if (result.next()) {
                pelanggan = new Pelanggan();
                pelanggan.setId(result.getInt("id"));
                pelanggan.setNama(result.getString("nama"));
                pelanggan.setAlamat(result.getString("alamat"));
                pelanggan.setTelepon(result.getString("telepon"));
                pelanggan.setEmail(result.getString("email"));
            } else {
                throw new PelangganException("pelanggan dengan id "+id+" tidak ditemukan");
            }
            connection.commit();
            return pelanggan;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new PelangganException(e.getMessage());
        } finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement!= null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    @Override
    public Pelanggan getPelanggan(String email) throws PelangganException {
        PreparedStatement statement = null;
        try {
            connection.setAutoCommit(false);
            
            statement = connection.prepareCall(getByeEmail);
            statement.setString(1,email);
            
            ResultSet result = statement.executeQuery();
            Pelanggan pelanggan = null;
            
            if (result.next()) {
                pelanggan = new Pelanggan();
                pelanggan.setId(result.getInt("id"));
                pelanggan.setNama(result.getString("nama"));
                pelanggan.setAlamat(result.getString("alamat"));
                pelanggan.setTelepon(result.getString("telepon"));
                pelanggan.setEmail(result.getString("email"));
            } else {
                throw new PelangganException("pelanggan dengan email "+email+" tidak ditemukan");
            }
            connection.commit();
            return pelanggan;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new PelangganException(e.getMessage());
        } finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement!= null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    @Override
    public List<Pelanggan> selectAllPelanggan() throws PelangganException {
        Statement statement = null;
        List<Pelanggan> list = new ArrayList<Pelanggan>();
        try {
            connection.setAutoCommit(false);
            
            statement = connection.createStatement();
            
            ResultSet result = statement.executeQuery(selectAll);
            Pelanggan pelanggan = null;
            
            while (result.next()) {
                pelanggan = new Pelanggan();
                pelanggan.setId(result.getInt("id"));
                pelanggan.setNama(result.getString("nama"));
                pelanggan.setAlamat(result.getString("alamat"));
                pelanggan.setTelepon(result.getString("telepon"));
                pelanggan.setEmail(result.getString("email"));
                list.add(pelanggan);
            }
            
            return list;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
            throw new PelangganException(e.getMessage());
        } finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
            if (statement!= null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
        }
    }
    
    
}
