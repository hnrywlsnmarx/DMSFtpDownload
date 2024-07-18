/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.dmsftpdownload;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author guita
 */
public class FTPUtil2 {
 
    public static void downloadDirectory(FTPClient ftpClient, String parentDir,
        String currentDir, String saveDir) throws IOException {
        
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");  
            String hminsatu = formatter.format(cal.getTime());
            System.out.println("H Min 1 = "+hminsatu);
        
    String dirToList = parentDir;
    
    if (!currentDir.equals("")) {
        dirToList += "/" + currentDir;
    }
        FTPFile[] subFiles = ftpClient.listFiles(dirToList);
        
    if (subFiles != null && subFiles.length > 0) {
        for (FTPFile aFile : subFiles) {
            String currentFileName = aFile.getName();
            if (currentFileName.equals(".") || currentFileName.equals("..")) {
                continue;
            }
            String filePath = parentDir + "/" + currentDir + "/"
                    + currentFileName;
            if (currentDir.equals("")) {
                filePath = parentDir + "/" + currentFileName;
            }
 
            String newDirPath = saveDir + parentDir + File.separator
                    + currentDir + File.separator + currentFileName;
            if (currentDir.equals("")) {
                newDirPath = saveDir + parentDir + File.separator
                          + currentFileName;
            }
 
            if (aFile.isDirectory()) {
                File newDir = new File(newDirPath);
                boolean created = newDir.mkdirs();
                if (created) {
                    System.out.println("CREATED the directory: " + newDirPath);
                } else {
                    System.out.println("COULD NOT create the directory: " + newDirPath);
                }
 
                downloadDirectory(ftpClient, dirToList, currentFileName,
                        saveDir);
            } else {
                boolean success = downloadSingleFile(ftpClient, filePath,
                        newDirPath);
                if (success) {
                    System.out.println("DOWNLOADED the file: " + filePath);
                    writeLog(filePath+" Downloaded on");
                } else {
                    System.out.println("COULD NOT download the file: "
                            + filePath);
                    writeLog(filePath+" Could Not Downloaded. File already exist.");
                }
                
            }
        }
    }
    else {
        System.out.println("NO DIRECTORY FOUND");
    }
}
 
    public static boolean downloadSingleFile(FTPClient ftpClient,
        String remoteFilePath, String savePath) throws IOException {
    File downloadFile = new File(savePath);
     
    File parentDir = downloadFile.getParentFile();
    File singlefile = downloadFile.getAbsoluteFile();
    
    if (!parentDir.exists()) {
        parentDir.mkdirs();
    } else {
        if(!singlefile.exists()){
            OutputStream outputStream = new BufferedOutputStream(
            new FileOutputStream(downloadFile));
                try {
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                    return ftpClient.retrieveFile(remoteFilePath, outputStream);
                } catch (IOException ex) {
                    throw ex;
                } finally {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                }
        } else {
            return false;
        }
    }
        return true;
}
    
    private static void writeLog(String filePath){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date date = new Date();
        String tgl = formatter.format(date);
        System.out.println(tgl);
        try {
            FileWriter log = new FileWriter("D:/storage_file/TeslogFTPDownload.txt", true);
            BufferedWriter bw = new BufferedWriter(log);
            bw.newLine();
            bw.write(filePath+" "+tgl);
            bw.close();
        } catch (Exception e) {
            System.out.println("Failed to Create Log");
        }
    }
}