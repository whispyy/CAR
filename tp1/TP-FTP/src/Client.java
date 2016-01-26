import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.omg.CORBA.portable.UnknownException;

public class Client {

	private Socket connection;
	private DataInputStream input;
	private DataOutputStream output;
	private boolean checksum;
	private MessageDigest md;
	private Thread inputThread;

	public static void main(String[] args) throws IOException{
		String hostname = "localhost";
		int port = 3000;
		System.out.print("Connection to : " + hostname + ":" + port);
		String s = new Scanner(System.in).next();

		new Client().connect(hostname,port);
	}

	public void connect(String hostname,int port) throws IOException{
		try{
			connection = new Socket(InetAddress.getByName(hostname), port);
			input = new DataInputStream(connection.getInputStream());
			output = new DataOutputStream(connection.getOutputStream());
		}
		catch(ConnectException e){
			System.out.print("Connection refused");
			System.exit(1);
		}
		catch(UnknownException e){
			System.out.print("Connection not found");
			System.exit(1);
		}
		Scanner scan= new Scanner(System.in);
		/*
		a completer test d'erreur sur lecture de boolean
		 */
		checksum=input.readBoolean();
		try{md=MessageDigest.getInstance("SHA-1");}
		catch(NoSuchAlgorithmException e){e.printStackTrace();}
		inputThread = new Thread();
		inputThread.start();

		//ecriture sur le serveur
		while(true){
			try{String request=scan.nextLine().trim();
			if(request.equalsIgnoreCase("exit")||request.equalsIgnoreCase("quit")||request.equalsIgnoreCase("bye")||request.equalsIgnoreCase("close"))
				System.exit(0);
			output.writeUTF(request);
			}
			catch(NoSuchElementException e){
				System.exit(0);
			}
		}
	}
}