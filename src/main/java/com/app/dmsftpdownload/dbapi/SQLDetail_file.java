/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.dmsftpdownload.dbapi;

import com.app.dmsftpdownload.model.Detail_file;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author guita
 */
public class SQLDetail_file implements ISQLDetail_file{

    @Override
    public Detail_file[] ambilDetailfile(Connection conn) throws SQLException {
        Statement st = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM detail_file");
            Vector v = new Vector();
            Detail_file detailFile = null;
            while(rs.next()){
                detailFile = new Detail_file();
                detailFile.setId(rs.getLong("id"));
                detailFile.setLoan_app_no(rs.getString("loan_app_no"));
                detailFile.setFile(rs.getString("file"));
                detailFile.setBranch_dir(rs.getString("branch_dir"));
                detailFile.setAlias(rs.getString("alias"));
                detailFile.setFlag(rs.getInt("flag"));
                v.add(detailFile);
            }
            Detail_file[] detailFiles = new Detail_file[v.size()];
            v.copyInto(detailFiles);
            return detailFiles;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if(st != null){
                st.close();
            }
        }
        return null;
    }

    @Override
    public Detail_file[] searchDetailfile(String where, Connection conn) throws SQLException {
        Statement st = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM detail_file WHERE loan_app_no LIKE '%"+where+"%'");
            Vector v = new Vector();
            Detail_file detailFile = null;
            while(rs.next()){
                detailFile = new Detail_file();
                detailFile.setId(rs.getLong("id"));
                detailFile.setLoan_app_no(rs.getString("loan_app_no"));
                detailFile.setFile(rs.getString("file"));
                detailFile.setBranch_dir(rs.getString("branch_dir"));
                detailFile.setAlias(rs.getString("alias"));
                detailFile.setFlag(rs.getInt("flag"));
                v.add(detailFile);

            }
            Detail_file[] detailFiles = new Detail_file[v.size()];
            v.copyInto(detailFiles);
            return detailFiles;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if(st != null){
                st.close();
            }
        }
        return null;
    }

    @Override
    public Detail_file[] cariDetailfile(String where, Connection conn) throws SQLException {
        Statement st = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM detail_file WHERE loan_app_no = " + where);
            Vector v = new Vector();
            Detail_file detailFile = null;
            while(rs.next()){
                detailFile = new Detail_file();
                detailFile.setId(rs.getLong("id"));
                detailFile.setLoan_app_no(rs.getString("loan_app_no"));
                detailFile.setFile(rs.getString("file"));
                detailFile.setBranch_dir(rs.getString("branch_dir"));
                detailFile.setAlias(rs.getString("alias"));
                detailFile.setFlag(rs.getInt("flag"));
                v.add(detailFile);
                Detail_file[] detailFiles = new Detail_file[v.size()];
                v.copyInto(detailFiles);
                return detailFiles;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if(st != null){
                st.close();
            }
        }
        return null;
    }

    @Override
    public Detail_file insertDetailfile(Detail_file dFile, Connection conn) throws SQLException {
        PreparedStatement psql = null;
        try {
            psql = conn.prepareStatement("INSERT INTO detail_file (loan_app_no, file, branch_dir, alias, flag) values (?,?,?,?,?)");
            psql.setString(1, dFile.getLoan_app_no());
            psql.setString(2, dFile.getFile());
            psql.setString(3, dFile.getBranch_dir());
            psql.setString(4, dFile.getAlias());
            psql.setInt(5, dFile.getFlag());
            psql.executeUpdate();
            System.out.println("Success");
            return dFile;
        } catch (SQLException e) {
            System.out.println("Error "+e.toString());
        } finally{
            if(psql != null){
                psql.close();
            }
        }
        return null;
    }

    @Override
    public Detail_file[] getNullFlag(Connection conn) throws SQLException {
        Statement st = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM detail_file where flag IS NULL");
            Vector v = new Vector();
            Detail_file detailFile = null;
            while(rs.next()){
                detailFile = new Detail_file();
                detailFile.setId(rs.getLong("id"));
                detailFile.setLoan_app_no(rs.getString("loan_app_no"));
                detailFile.setFile(rs.getString("file"));
                detailFile.setBranch_dir(rs.getString("branch_dir"));
                detailFile.setAlias(rs.getString("alias"));
                detailFile.setFlag(rs.getInt("flag"));
                v.add(detailFile);
            }
            Detail_file[] detailFiles = new Detail_file[v.size()];
            v.copyInto(detailFiles);
            return detailFiles;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if(st != null){
                st.close();
            }
        }
        return null;
    }
}
