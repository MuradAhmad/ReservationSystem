import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	private Socket clientSocket;
	private BufferedReader input;
	private PrintStream output;
	private Scanner scanner = new Scanner(System.in);
	String request, message, reply ;
	
	
	
	
	public static void main (String args[]) 
	{
		Client client = new Client();
		client.run();
		
		
		
	}
	
	public void run() {
		try {
			clientSocket = new Socket("127.0.0.1",5555);
			
			output = new PrintStream(clientSocket.getOutputStream());
			output.println("Client1 is connected");
			
			 input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			 
			 System.out.println("Enter a message");
			
			 
			
			
			
			while(clientSocket.isConnected()) {
				message = input.readLine();
				System.out.println("Server:" + message);
				
				
				
				
				
				  System.out.println("Send secret message to server");
				  Scanner in = new Scanner(System.in);
				  
				  
				 
					 System.out.println("Encryption:");
					  in.nextLine();
					  System.out.println("Enter Message:");
					  String message =  in.nextLine();
					  System.out.println("Enter Key (0 - 25):");
					  int key = in.nextInt();
					  
					  String encrypMsg = "";
					  
					  for (int i = 0; i < message.length(); i++ ) {
						  if((int)message.charAt(i) == 32) {
							  encrypMsg += (char)32;
							  
							  
						  }else if((int)message.charAt(i)+ key > 122) {
							  int temp = (int)(message.charAt(i) + key) - 122;
							  encrypMsg += (char) (96 + temp);
							  
							  
							  
						  }else if((int)message.charAt(i)+ key > 90 && (int)message.charAt(i) < 96) {
							  int temp = (int)(message.charAt(i) + key) - 90;
							  encrypMsg += (char) (64 + temp);
							  
							  
						  }else {
							  encrypMsg +=(char) ((int) message.charAt(i) + key);
							  
						  }
						  
						  System.out.println(encrypMsg);
						  
						  
					  }
				
					  output.println(encrypMsg);
				
				
				
				/*
				 reply = scanner.nextLine();
				 output.println(reply);*/
				
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
