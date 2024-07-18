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

/**
 *
 * @author guita
 */
public class SQLDetail_fileServerLama implements ISQLDetail_fileServerLama {

    @Override
    public Detail_file[] ambilDetailfile(Connection conn) throws SQLException {
        Statement st = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM detail_file");
            Vector v = new Vector();
            Detail_file detailFile = null;
            while (rs.next()) {
                detailFile = new Detail_file();
                detailFile.setId(rs.getLong("id"));
                detailFile.setLoan_app_no(rs.getString("loan_app_no"));
                detailFile.setFile(rs.getString("file"));
                detailFile.setBranch_dir(rs.getString("branch_dir"));
                detailFile.setAlias(rs.getString("alias"));
                detailFile.setFlag(rs.getInt("flag"));
                detailFile.setFlag_exist(rs.getInt("flag_exist"));
                v.add(detailFile);
            }
            Detail_file[] detailFiles = new Detail_file[v.size()];
            v.copyInto(detailFiles);
            return detailFiles;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
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
            ResultSet rs = st.executeQuery("SELECT * FROM detail_file WHERE loan_app_no LIKE '%" + where + "%'");
            Vector v = new Vector();
            Detail_file detailFile = null;
            while (rs.next()) {
                detailFile = new Detail_file();
                detailFile.setId(rs.getLong("id"));
                detailFile.setLoan_app_no(rs.getString("loan_app_no"));
                detailFile.setFile(rs.getString("file"));
                detailFile.setBranch_dir(rs.getString("branch_dir"));
                detailFile.setAlias(rs.getString("alias"));
                detailFile.setFlag(rs.getInt("flag"));
                detailFile.setFlag_exist(rs.getInt("flag_exist"));
                v.add(detailFile);

            }
            Detail_file[] detailFiles = new Detail_file[v.size()];
            v.copyInto(detailFiles);
            return detailFiles;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
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
            while (rs.next()) {
                detailFile = new Detail_file();
                detailFile.setId(rs.getLong("id"));
                detailFile.setLoan_app_no(rs.getString("loan_app_no"));
                detailFile.setFile(rs.getString("file"));
                detailFile.setBranch_dir(rs.getString("branch_dir"));
                detailFile.setAlias(rs.getString("alias"));
                detailFile.setFlag(rs.getInt("flag"));
                detailFile.setFlag_exist(rs.getInt("flag_exist"));
                v.add(detailFile);
                Detail_file[] detailFiles = new Detail_file[v.size()];
                v.copyInto(detailFiles);
                return detailFiles;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                st.close();
            }
        }
        return null;
    }

    @Override
    public Detail_file[] getNullFlag(Connection conn, String where) throws SQLException {
        Statement st = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM detail_file where flag IS NULL AND branch_dir like '%" + where + "%'");
            Vector v = new Vector();
            Detail_file detailFile = null;
            while (rs.next()) {
                detailFile = new Detail_file();
                detailFile.setId(rs.getLong("id"));
                detailFile.setLoan_app_no(rs.getString("loan_app_no"));
                detailFile.setFile(rs.getString("file"));
                detailFile.setBranch_dir(rs.getString("branch_dir"));
                detailFile.setAlias(rs.getString("alias"));
                detailFile.setFlag(rs.getInt("flag"));
                detailFile.setFlag_exist(rs.getInt("flag_exist"));
                v.add(detailFile);
            }
            Detail_file[] detailFiles = new Detail_file[v.size()];
            v.copyInto(detailFiles);
            return detailFiles;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                st.close();
            }
        }
        return null;

    }

    @Override
    public Detail_file[] updateFlag(Detail_file detailFile, Connection conn, String where) throws SQLException {
        PreparedStatement psql = null;
        try {
            psql = conn.prepareStatement("UPDATE detail_file SET flag = 1 where branch_dir like '%" + where + "%'");
            psql.executeUpdate();
//            System.out.println("updating success");        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (psql != null) {
                try {
                    psql.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    @Override
    public Detail_file[] getAllNullFlag(Connection conn, String where) throws SQLException {
        Statement st = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM detail_file where flag IS NULL and SUBSTR(file, 6, 10)  LIKE '%" + where + "%'");
            Vector v = new Vector();
            Detail_file detailFile = null;
            while (rs.next()) {
                detailFile = new Detail_file();
                detailFile.setId(rs.getLong("id"));
                detailFile.setLoan_app_no(rs.getString("loan_app_no"));
                detailFile.setFile(rs.getString("file"));
                detailFile.setBranch_dir(rs.getString("branch_dir"));
                detailFile.setAlias(rs.getString("alias"));
                detailFile.setFlag(rs.getInt("flag"));
                detailFile.setFlag_exist(rs.getInt("flag_exist"));
                v.add(detailFile);
            }
            Detail_file[] detailFiles = new Detail_file[v.size()];
            v.copyInto(detailFiles);
            return detailFiles;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                st.close();
            }
        }
        return null;
    }

    @Override
    public Detail_file[] updateAllFlag(Detail_file detailFile, Connection conn, String where) throws SQLException {
        PreparedStatement psql = null;
        try {
            psql = conn.prepareStatement("UPDATE detail_file SET flag = 1 where SUBSTR(file, 6, 10)  LIKE '%" + where + "%'");
            psql.executeUpdate();
//            System.out.println("updating success");        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (psql != null) {
                try {
                    psql.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    @Override
    public Detail_file[] getAllExistFlag(Connection conn) throws SQLException {
        Statement st = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM detail_file where flag = 1");
            Vector v = new Vector();
            Detail_file detailFile = null;
            while (rs.next()) {
                detailFile = new Detail_file();
                detailFile.setId(rs.getLong("id"));
                detailFile.setLoan_app_no(rs.getString("loan_app_no"));
                detailFile.setFile(rs.getString("file"));
                detailFile.setBranch_dir(rs.getString("branch_dir"));
                detailFile.setAlias(rs.getString("alias"));
                detailFile.setFlag(rs.getInt("flag"));
                detailFile.setFlag_exist(rs.getInt("flag_exist"));
                v.add(detailFile);
            }
            Detail_file[] detailFiles = new Detail_file[v.size()];
            v.copyInto(detailFiles);
            return detailFiles;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                st.close();
            }
        }
        return null;
    }

    @Override
    public Detail_file[] getBranchDir(Connection conn, String where) throws SQLException {
        Statement st = null;
        try {
            st = conn.createStatement();
//            ResultSet rs = st.executeQuery("SELECT DISTINCT * from detail_file where created_at between '2024-07-08 00:00:00' and '2024-07-09 00:00:00'");
//            ResultSet rs = st.executeQuery("SELECT * from detail_file where SUBSTR(file, 6, 10) LIKE '%"+where+"%' group by branch_dir");
            ResultSet rs = st.executeQuery("SELECT id,branch_dir from detail_file where  SUBSTR(file, 6, 10) = '" + where + "' group by branch_dir");
            Vector v = new Vector();
            Detail_file detailFile = null;
            while (rs.next()) {
                detailFile = new Detail_file();
                detailFile.setId(rs.getLong("id"));
                detailFile.setBranch_dir(rs.getString("branch_dir"));
                v.add(detailFile);
            }
            Detail_file[] detailFiles = new Detail_file[v.size()];
            v.copyInto(detailFiles);
            return detailFiles;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                st.close();
            }
        }
        return null;
    }
}
