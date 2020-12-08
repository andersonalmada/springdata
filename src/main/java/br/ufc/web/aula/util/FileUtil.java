package br.ufc.web.aula.util;

import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FileUtil {

	public void uploadFileServer(InputStream uploadedInputStream, String url, String username, String password,
			String resource, String id) {
		try {
			FTPClient ftp = new FTPClient();
			ftp.connect(url);
			ftp.login(username, password);
			System.out.println(ftp.getReplyString());
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();

			String dir = "/public_html/uploads/" + resource;
			String file = dir + "/" + id;

			ftp.makeDirectory(dir);
			ftp.storeFile(file, uploadedInputStream);
			System.out.println(ftp.getReplyString());
			ftp.logout();
		} catch (Exception e) {
		}
	}
}