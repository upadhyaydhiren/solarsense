package com.utilaider.solarsense.ftpclient;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;

public class FtpConnection {
	private static final String SERVER = "54.187.25.68";
	private static final String USERNAME = "solarsense";
	private static final String PASSWORD = "dhiren";
	private FTPClient ftpClient;

	public FtpConnection() throws SocketException, IOException {
		ftpClient = new FTPClient();
		ftpClient.connect(SERVER, 21);
		ftpClient.login(USERNAME, PASSWORD);
		showServerReply(ftpClient);
		System.out.println(ftpClient.changeWorkingDirectory("/solarsensedata"));
		showServerReply(ftpClient);
	}

	public FTPClient getFtpClient() {
		return ftpClient;
	}

	private static void showServerReply(FTPClient ftpClient) {
		String[] replies = ftpClient.getReplyStrings();
		if (replies != null && replies.length > 0) {
			for (String aReply : replies) {
				System.out.println("SERVER: " + aReply);
			}
		}
	}

}
