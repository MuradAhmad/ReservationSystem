import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client1 {
	private Socket clientSocket;
	private BufferedReader input;
	private PrintStream output;
	private Scanner scanner = new Scanner(System.in);
	String request, message, reply ;
	
	
	
	
	public static void main (String args[]) 
	{
		Client1 client1 = new Client1();
		client1.run();
		
		
		
	}
	
	public void run() {
		try {
			clientSocket = new Socket("127.0.0.1",9999);
			
			output = new PrintStream(clientSocket.getOutputStream());
			output.println("Client1 is connected");
			
			 input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			 
			 System.out.println("Enter a message");
			
			 
			
			
			
			while(clientSocket.isConnected()) {
				message = input.readLine();
				System.out.println("Server:" + message);
				
				 reply = scanner.nextLine();
				 output.println(reply);
				
			}
			
			clientSocket.close();
			input.close();
			output.close();
			scanner.close(); 
			
			
			
			
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
