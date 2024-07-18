/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.dmsftpdownload;

import com.app.dmsftpdownload.dbapi.ISQLDetail_fileServerLama;
import com.app.dmsftpdownload.dbapi.Koneksi;
import com.app.dmsftpdownload.dbapi.SQLDetail_fileServerLama;
import com.app.dmsftpdownload.model.Detail_file;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author guita
 */
public class FTPDownloadDirectoryTest {

    String branch;
    Detail_file detbranch;
    String hminsatu;
    Connection connServerLama = new Koneksi().getConnectionServerLama();
    ISQLDetail_fileServerLama sql = new SQLDetail_fileServerLama();

    public FTPDownloadDirectoryTest() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        hminsatu = formatter.format(cal.getTime());
//        hminsatu = "2024/07/12";
        System.out.println("H Min 1 = " + hminsatu);
        try {
            Detail_file[] detailFiles = sql.getBranchDir(connServerLama, hminsatu);
            if (detailFiles.length != 0) {
                for (Detail_file detailFile : detailFiles) {
                    detbranch = detailFile;
                    String branch_dir = detailFile.getBranch_dir();
                    System.out.println("Branch Dir = " + branch_dir);
                    System.out.println("Detail files length = " + detailFiles.length);
//                    String server = "172.28.97.30";
//                    int port = 21;
//                    String user = "irfan";
//                    String pass = "bws@dipo28";

                    String server = "172.28.140.200";
                    int port = 21;
                    String user = "dms";
                    String pass = "dms@1906";
                    FTPClient ftpClient = new FTPClient();
                    boolean error = false;
                    try {
                        int reply;
                        // connect and login to the server
                        ftpClient.connect(server, port);
                        ftpClient.login(user, pass);

                        reply = ftpClient.getReplyCode();
                        if (!FTPReply.isPositiveCompletion(reply)) {
                            ftpClient.disconnect();
                            System.err.println("FTP server refused connection.");
                            System.exit(1);
                        } else {
                            ftpClient.enterLocalPassiveMode();

                            System.out.println("Connected");
                            String remoteDirPath = "/indexed/" + branch_dir + "/" + hminsatu;
//                            String remoteDirPath = "/DMS_Backup/" + branch_dir + "/" + hminsatu;
                            String saveDirPath = "D:/storage_file"; //folder hasill download
                            FTPUtil.downloadDirectory(ftpClient, remoteDirPath, "", saveDirPath);
                        }

                    } catch (IOException ex) {
                        error = true;
                        ex.printStackTrace();
                    } finally {
                        if (ftpClient.isConnected()) {
                            try {
                                System.out.println("Disconnect");
                                ftpClient.logout();
                                ftpClient.disconnect();
                            } catch (IOException ioe) {
                                System.out.println("Error " + ioe.toString());
                            }
                        }
//                                System.exit(error ? 1:0);
                    }
                }
            } else {
                System.out.println("No data");
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.toString());
        }
    }

    public static void main(String[] args) {
        new FTPDownloadDirectoryTest();
    }
}
