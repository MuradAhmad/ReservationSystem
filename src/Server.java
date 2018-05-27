import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	ServerSocket serverSocket;
	Socket acceptSocket;
	static Scanner machineScan = new Scanner(System.in);
/*	private PrintStream output;
	private BufferedReader input;
	private Scanner scanner = new Scanner(System.in);*/
	String message, reply;
	int port;
	ExecutorService pool = null;
	int clientCount =0;
	
	static String[] machinelist = {"1) Printer", "2) 3D Printer","3) Scanner","4) Cutter" }; 

	public static void main(String args[]) throws IOException {
		Server server = new Server(9999);
		server.startServer();

	}
	Server(int port){
		this.port = port;
		pool = Executors.newFixedThreadPool(5);
	}

	public void startServer() throws IOException {
		
			serverSocket = new ServerSocket(9999);
			
			
			while(true) {
			acceptSocket = serverSocket.accept();
			clientCount++;
			ServerThread runnable = new ServerThread(acceptSocket,clientCount,this);
			pool.execute(runnable);
			}

			/*output = new PrintStream(acceptSocket.getOutputStream());

			input = new BufferedReader(new InputStreamReader(acceptSocket.getInputStream()));

			while (acceptSocket.isConnected()) {
				message = input.readLine();
				System.out.println("Client1:" + message);
				
				
				 reply = scanner.nextLine();
				 output.println(reply);
				 
				 
				 
				// Print Machines
				 String filterrequest = "status";
					if (filterrequest.equalsIgnoreCase(message)) {

						String machines = "1.Printer,2.Scanner,3.3DPrinter";
						output.println(machines);
						

					}
 

			}
			
			 */
			
			}




  private static class ServerThread implements Runnable {
	
	
	Server server = null;
	Socket clientSocket;
	PrintStream output;
	BufferedReader input;
	Scanner scanner = new Scanner(System.in);
	int id;
	String message;
	
	ServerThread(Socket client, int count, Server server) throws IOException {
		
		this.clientSocket = client;
		this.server = server;
		this.id = count;
		System.out.println("Connection "+id + "established with Clients" + client);
		
		output = new PrintStream(client.getOutputStream());
		input = new BufferedReader(new InputStreamReader(client.getInputStream()));


	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
		getMachine();
	
		try {
			while(true)
			{
				message = input.readLine();
				
				System.out.println("Client ("+ id +" ):" + message);
				
/*				
				if(message.equalsIgnoreCase("R") ) {
				
				System.out.println("Client ("+ id +" ):" + message);
				//System.out.println("Server :");
				System.out.println("Available Machines \n ");
				System.out.println("1) Printer" +" "+ "2) 3D Printer" +" "+ "3) Scanner" +" "+ "4) Cutter \n ");
				
				}*/
								// Print Machines
				 String filterrequest = "show";
					if (filterrequest.equalsIgnoreCase(message)) {
						//getMachine();
						String machines = "1) Printer" +" "+ "2) 3D Printer" +" "+ "3) Scanner" +" "+ "4) Cutter" ;
						 System.out.println("1) Printer" +" "+ "2) 3D Printer" +" "+ "3) Scanner" +" "+ "4) Cutter");
						 output.println(machines);
						
						
												
					}
					
				
				
				if(message.equalsIgnoreCase("1")) {
					System.out.println("You Reserved Machine: Printer "  );
					output.println("You Reserved Machine: Printer ");
					
				}else
				if(message.equalsIgnoreCase("2")) {
					System.out.println("You Reserved Machine: 3D Printer "  );
					output.println("You Reserved Machine: 3D Printer ");
					
				}else
				if(message.equalsIgnoreCase("3")) {
					System.out.println("You Reserved Machine: Scanner "  );
					output.println("You Reserved Machine: Scanner ");
					
				}else
				if(message.equalsIgnoreCase("4")) {
					System.out.println("You Reserved Machine: Cutter "  );
					output.println("You Reserved Machine: Cutter ");
					
				}else {
					//System.out.println("Select machine "  );
					getMachine();
				}
				
				
				
				// Client Message Decription 
				/*
				
					
					System.out.println("Client ("+ id +" ):" + message);
					System.out.println("Decrypted Message \n");
					
					Scanner in = new Scanner(System.in);
					
					  String encypText =  message;
					  System.out.println("Enter Key (0 - 25):");
					  int decypkey = in.nextInt();
					  
					  String decrypMsg = "";
					  
					  for(int i =0; i< encypText.length(); i++) {
						  if((int)encypText.charAt(i) == 32) {
							  decrypMsg += (char)32;
							  
						  }else if(((int)encypText.charAt(i) - decypkey) < 97 && ((int)encypText.charAt(i) - decypkey) > 90){
							  int temp = (encypText.charAt(i) - decypkey) + 26;
							  decrypMsg += (char)temp;	  
							  
						  }
						  else if(((int)encypText.charAt(i) - decypkey) < 65){
							  int temp = ((int)encypText.charAt(i) - decypkey) + 26;
							  decrypMsg += (char)temp;
							  
						  }else {
							  
							  decrypMsg +=  (char)((int)encypText.charAt(i) - decypkey) ;
						  }
						  
						  System.out.println(decrypMsg); 
					
					
					
				}*/
				
				
						  
					// Send Message to Client 	 
				
				
				
				
				
				message = scanner.nextLine();
				output.println(message);
			
			}
			
			
		}catch(IOException e) {
			System.out.println("Error:" + e );
			
		
	}
	
	
	
	
}
}
  
  
  public static void getMachine() {
	  System.out.println("Please select: \n 1. Encryption \n 2. Decryption");
	  Scanner in = new Scanner(System.in);
	  int choice = in.nextInt();
	  
	  if(choice ==1) {
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
		  
		  
		  
	  }else if (choice ==2) {
		  
		  System.out.println("Decryption:");
		  in.nextLine();
		  System.out.println("Enter Message:");
		  String encypText =  in.nextLine();
		  System.out.println("Enter Key (0 - 25):");
		  int decypkey = in.nextInt();
		  
		  String decrypMsg = "";
		  
		  for(int i =0; i< encypText.length(); i++) {
			  if((int)encypText.charAt(i) == 32) {
				  decrypMsg += (char)32;
				  
			  }else if(((int)encypText.charAt(i) - decypkey) < 97 && ((int)encypText.charAt(i) - decypkey) > 90){
				  int temp = (encypText.charAt(i) - decypkey) + 26;
				  decrypMsg += (char)temp;	  
				  
			  }
			  else if(((int)encypText.charAt(i) - decypkey) < 65){
				  int temp = ((int)encypText.charAt(i) - decypkey) + 26;
				  decrypMsg += (char)temp;
				  
			  }else {
				  
				  decrypMsg +=  (char)((int)encypText.charAt(i) - decypkey) ;
			  }
			  
			  System.out.println(decrypMsg); 
			  
			  
		  }
		  
		  
		  
		  
	  }else {
		  System.out.println("Choose  1 or 2 ");
		  
	  }
	  
	 /* for(int i = 0; machinelist.length < i; i++) {
		  
		  System.out.println(machinelist[i]);
		  
	  }
	  int number = machineScan.nextInt();
	  System.out.println("You Selected Machine:" + machinelist[number-1].substring(3, machinelist[number-1].length()));
	  */
	  System.out.println("1) Printer" +" "+ "2) 3D Printer" +" "+ "3) Scanner" +" "+ "4) Cutter");
	  
  }
  
  
} 
			

			
		

	


