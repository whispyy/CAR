import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
	
	private Socket connection;
	private DataInputStream input;
	private DataOutputStream output;

	public static void main(String[] args) throws IOException{

		System.out.print("Connection to :");
		String s = new Scanner(System.in).next();
		String hostname = null;
		int port = 8080;

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
        inputThread = new Thread( this );
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