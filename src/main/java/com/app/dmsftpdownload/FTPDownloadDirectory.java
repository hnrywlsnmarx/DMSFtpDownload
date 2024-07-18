/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.dmsftpdownload;

import com.app.dmsftpdownload.dbapi.ISQLDetail_fileServerLama;
import com.app.dmsftpdownload.dbapi.Koneksi;
import com.app.dmsftpdownload.dbapi.SQLDetail_fileServerLama;
import com.app.dmsftpdownload.model.Detail_file;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author guita
 */
public class FTPDownloadDirectory {

    String branch_dir;
    Detail_file detbranch;
    String hminsatu;
    String year;
    String path;
    String namafile;
    Connection connServerLama = new Koneksi().getConnectionServerLama();
    Connection connServerBaru = new Koneksi().getConnectionServerBaru();
    ISQLDetail_fileServerLama sql = new SQLDetail_fileServerLama();

    public FTPDownloadDirectory() throws SQLException {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat formatteryear = new SimpleDateFormat("yyyy");
        //        hminsatu = formatter.format(cal.getTime());
//        year = formatteryear.format(cal.getTime());
//        hminsatu = "2022/06/20";
//            year = "2019";

        PreparedStatement prep11 = connServerBaru.prepareStatement("SELECT tahun FROM movingtable where movingstatus is null");
        ResultSet rse11 = prep11.executeQuery();
        while (rse11.next()) {
            year = rse11.getString("tahun");
        }

        rse11.close();
        prep11.close();

        System.out.println("Mulai proses perpindahan data tahun = " + year);
        SimpleDateFormat formatterdet = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date datedet = new Date();
        String tgldet = formatterdet.format(datedet);
        PreparedStatement prep12 = connServerBaru.prepareStatement("SELECT count(id) as jmldata FROM movingtable where tahun = '" + year + "'and movingstatus is null");
        int hasilsqlCarix = 0;
        int jmldatacarix = 0;
//            int jml = 0;
        ResultSet rse12 = prep12.executeQuery();
        while (rse12.next()) {
            jmldatacarix = rse12.getInt(1);
//                jml = rse12.getInt("jmldata");
        }

        rse12.close();
        prep12.close();

        PreparedStatement prep14 = connServerLama.prepareStatement("SELECT count(id) as jmldata FROM detail_file where SUBSTR(file, 6, 4) = '" + year + "'");
        int hasilsqljml = 0;
        int jml = 0;
        ResultSet rse14 = prep14.executeQuery();
        while (rse14.next()) {
            jml = rse14.getInt("jmldata");

        }
        System.out.println("Total Jumlah Data tahun " + year + " = " + jml);
        rse14.close();
        prep14.close();

        if (jmldatacarix != 0) {
            int baris = 0;
            int baris2 = 0;
            PreparedStatement sqlCarix = connServerLama.prepareStatement("SELECT branch_dir FROM detail_file where SUBSTR(file, 6, 4) = '" + year + "'");
//            System.out.println("year = " + year);
            ResultSet rsec2 = sqlCarix.executeQuery();
            while (rsec2.next()) {

                baris = baris + 1;
                baris2 = baris2 + 1;
//                    path = rsec2.getString("file");
//                    namafile = rsec2.getString("file").substring(16);
//                    year = rsec2.getString("file").substring(5, 9);
                branch_dir = rsec2.getString("branch_dir");
                String timeStamp2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
                System.out.println(baris2 + " Kantor Cabang " + branch_dir + " di proses pada " + timeStamp2);
                writeLog(baris2 + " Kantor Cabang " + branch_dir + " di proses pada ");

//                hasilsqlCarix = 0;
//                    System.out.println(baris2 + " Kantor Cabang "+branch_dir);
//                    System.out.println("TTTTT " + branch_dir);
//                String server = "172.28.97.131";
//                int port = 21;
//                String user = "dms";
//                String pass = "dms@1906";
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

//                            System.out.println("Connected");
                        String remoteDirPath = "/indexed/" + branch_dir + "/" + year;
//                        System.out.println("TTTTT " + branch_dir);
//                            String saveDirPath = "/Documents/moving"; //folder hasill download
                        String saveDirPath = "D:/moving"; //folder hasill download
//                        System.out.println("tes " + remoteDirPath);
                        FTPUtil.downloadDirectory(ftpClient, remoteDirPath, "", saveDirPath);
//                            boolean success = FTPUtil.downloadSingleFile(ftpClient, remoteDirPath, saveDirPath);
//                            writeLog("File Downloaded");
//                              System.out.println("ttttt = "+success);
//                            if (success) {
//                                try {
//                                    int hasilsqlUpdateFlag = 0;
//                                    PreparedStatement sqlUpdateFlagUpload = connServerLama.prepareStatement("UPDATE detail_file SET flag_exist = 1 where SUBSTR(file, 6, 4) = '" + year + "'");
//                                    hasilsqlUpdateFlag = sqlUpdateFlagUpload.executeUpdate();
//                                    sqlUpdateFlagUpload.close();
//                                    hasilsqlUpdateFlag = 0;
//                                    writeLog("flag for " + namafile + "has been updated at: ");
//                                    System.out.println("DOWNLOADED the file: " + remoteDirPath + " at: " + tgldet);
//                                    writeLog(namafile + " Downloaded at ");
//                                } catch (SQLException ex) {
//                                    Logger.getLogger(FTPUtil.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                            }
//                    else {
//                            try {
//                                int hasilSqlUpdateMovingFlag = 0;
//                            PreparedStatement sqlUpdateMovingFlag = connServerBaru.prepareStatement("UPDATE movingtable SET movingstatus = 'OK' "
//                                    + "where tahun = '" + year + "'");
//                            hasilSqlUpdateMovingFlag = sqlUpdateMovingFlag.executeUpdate();
//                            sqlUpdateMovingFlag.close();
//                            hasilSqlUpdateMovingFlag = 0;
//                            writeLog("flag for year " + year + " has been updated at: ");
//                            System.out.println("Update moving flag for year: " + year + " at: ");
//                            } catch (SQLException ex) {
//                                Logger.getLogger(FTPUtil.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                        }

                    }

                } catch (IOException ex) {
                    error = true;
                    ex.printStackTrace();
                } finally {
                    if (ftpClient.isConnected()) {
                        try {
//                                System.out.println("Disconnect");
                            ftpClient.logout();
                            ftpClient.disconnect();
                        } catch (IOException ioe) {
                            System.out.println("Error " + ioe.toString());
                        }
                    }
//                                System.exit(error ? 1:0);
                }
            }

            if (baris / 100 == 1) {
                // System.out.println("baris = "+baris);
                baris = 0;
                // System.out.println("akan system GC");
                System.gc();
                long current = Runtime.getRuntime().freeMemory();
                try {
                    // System.out.println("sleep1..");
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                // System.out.println("Sudah system GC ");
            }
            rsec2.close();
            sqlCarix.close();
        }

    }

    public static void main(String[] args) throws SQLException {
        new FTPDownloadDirectory();
    }

    private static void writeLog(String ket) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String tgl = formatter.format(date);
//        System.out.println(tgl);
        try {
            FileWriter log = new FileWriter("D:/moving/MovingLog.txt", true);
            BufferedWriter bw = new BufferedWriter(log);
            bw.newLine();
            bw.write(ket + " " + tgl);
            bw.close();
        } catch (IOException e) {
            System.out.println("Failed to Create Log front");
        }
    }

//    private static void writeLogUnderline(String ket) {
//        try {
//            FileWriter log = new FileWriter("C:/Users/adminapp/Documents/moving", true);
//            BufferedWriter bw = new BufferedWriter(log);
//            bw.newLine();
//            bw.close();
//        } catch (IOException e) {
//            System.out.println("Failed to Create Log");
//        }
//    }
}
