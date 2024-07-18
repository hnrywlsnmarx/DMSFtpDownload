/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.app.dmsftpdownload.dbapi;


import com.app.dmsftpdownload.model.Data_file;
import com.app.dmsftpdownload.model.Detail_file;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author guita
 */
public interface ISQLDetail_fileServerBaru {
    public Detail_file[] getAll(Connection conn)  throws SQLException;
    public Detail_file[] addDetail_file(Detail_file dFile, Connection conn) throws SQLException;
    public Detail_file[] updateExistFlag(Detail_file detailFile, Connection conn) throws SQLException;
    public Detail_file[] updateExistFlagNullSubstr(Detail_file detailFile, Connection conn, String namafile) throws SQLException;
    public Detail_file[] updateExistFlagFileNull(Detail_file detailFile, Connection conn, String namafile) throws SQLException;
    public Data_file[] addData_file(Data_file dataFile, Connection conn) throws SQLException;
    public Data_file[] updateFlagProcess(Data_file dataFile, Connection conn, String where) throws SQLException;
   
}
