package br.ufc.web.aula.util;

public class Main {

	public static void main(String[] args) {
		String url = "files.000webhost.com";
		String username = "almada-web";
		String password = "@Almada91";
		String resource = "uaser";
		String id = "2";

		FileUtil fileUtil = new FileUtil();
		fileUtil.uploadFileServer(null, url, username, password, resource, id);
	}

}
