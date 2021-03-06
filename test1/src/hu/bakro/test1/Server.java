package hu.bakro.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server extends Thread {
	private ServerSocket _serverSocket;
	private Socket _socket;
	private InputStream _istream;
	
	private ToliCore _core;
	private SurfacePanel _surface;
	
	/**
	 * A port parameteren hallgato socketet hoz letre.
	 * A core, surface, gui objektumok a MASTER altal kuldott parancsok vegrehajthatosaga vegett adodik at.
	 *  
	 * @param port Socket portja
	 * @param core A jatekmag
	 * @param surface A felulet kirajzolasert felelo osztaly
	 * @throws IOException
	 */
	public Server(int port, ToliCore core, SurfacePanel surface) throws IOException {
		_core = core;
		_surface = surface;
		
		_serverSocket = new ServerSocket(port);
		_serverSocket.setSoTimeout(10000);
		System.out.println("Socket opened!");
	}
	
	/**
	 * Szerver szaljanak fuggvenye. Var akliensre timeout-ig, azutan ujraprobalkozik a varassal.
	 * Kapcsolodas utan a kuldott adatokat polling-olja. Erkezett adatot a decode fgv.-el hajtattja vegre.
	 *
	**/
	public void run() {
		while (true) {
			try {
				_socket = _serverSocket.accept();
				System.out.println("Master connected!");
				
				_istream = _socket.getInputStream();
				BufferedReader receiveRead = new BufferedReader(new InputStreamReader(_istream));
				
				String receiveMessage;
				String[] tokens;
				while (true) {
					if ((receiveMessage = receiveRead.readLine()) != null) {
						System.out.println(receiveMessage);
						tokens = receiveMessage.split("[ ]+");
						decode(tokens);
						System.out.flush();
					}
					if(!_socket.isConnected()) {
						break;
					}
				}
			} catch (SocketTimeoutException s) {
				System.out.println("Socket timed out!");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Kapott uzenet/parancs dekodolasa es vegrehajtasa
	 * 
	 * @param tokens Tombbe szervezett parancs es az ergumentumai
	 **/	
	private void decode(String[] tokens) {
		switch(tokens[0]) {
		case "set":
			_core.setItemXY(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
			break;
		case "clear":
			_core.clearItemXY(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
			break;
		case "paint":
			_surface.repaint();
			break;
		case "move":
			_surface.movement(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
			break;
		}
		
		
		
		if(tokens[0].contentEquals("move")) {
			_core.move(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
		}
	}
}