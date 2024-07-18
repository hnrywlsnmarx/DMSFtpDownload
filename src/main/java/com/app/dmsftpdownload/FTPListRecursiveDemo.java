/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.dmsftpdownload;

import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author guita
 */
public class FTPListRecursiveDemo {
    static void listDirectory(FTPClient ftpClient, String parentDir,
            String currentDir, int level) throws IOException {
        String dirToList = parentDir;
        if (!currentDir.equals("")) {
            dirToList += "/" + currentDir;
        }
        FTPFile[] subFiles = ftpClient.listFiles(dirToList);
        if (subFiles != null && subFiles.length > 0) {
            for (FTPFile aFile : subFiles) {
                String currentFileName = aFile.getName();
                if (currentFileName.equals(".")
                        || currentFileName.equals("..")) {
                    // skip parent directory and directory itself
                    continue;
                }
                for (int i = 0; i < level; i++) {
                    System.out.print("\t");
                }
                if (aFile.isDirectory()) {
                    System.out.println("[" + currentFileName + "]");
                    listDirectory(ftpClient, dirToList, currentFileName, level + 1);
                } else {
                    System.out.println(currentFileName);
                }
            }
        }
    }
//    public static void main(String[] args) {
//         String server = "172.28.140.200";
//        int port = 21;
//        String user = "dms";
//        String pass = "dms@1906";
//        FTPClient ftpClient = new FTPClient();
//        try {
//            ftpClient.connect(server, port);
//            int replyCode = ftpClient.getReplyCode();
//            if (!FTPReply.isPositiveCompletion(replyCode)) {
//                System.out.println("Connect failed");
//                return;
//            }
//            boolean success = ftpClient.login(user, pass);
//            if (!success) {
//                System.out.println("Could not login to the server");
//                return;
//            }
//            String dirToList = "";
//            listDirectory(ftpClient, dirToList, "", 0);
//        } catch (IOException ex) {
//            System.out.println("Oops! Something wrong happened");
//            ex.printStackTrace();
//        } finally {
//            // logs out and disconnects from server
//            try {
//                if (ftpClient.isConnected()) {
//                    ftpClient.logout();
//                    ftpClient.disconnect();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
}