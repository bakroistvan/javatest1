package hu.bakro.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread {
	private Socket _socket;
	private OutputStream _ostream;
	private PrintWriter _pwrite;
	
	public Client() {
		
	}
	
	public void run() {
		while (true) {
			try {
				_socket = new Socket("localhost", 4242);
				_ostream = _socket.getOutputStream();
				_pwrite = new PrintWriter(_ostream, true);
				break;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Uzenet kuldese a slave-nek
	 * 
	 * @param msg
	 **/
	public void sendMsg(String msg) {
		if(_pwrite != null) {
			_pwrite.println(msg);
		}
	}
}



