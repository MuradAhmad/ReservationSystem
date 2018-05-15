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
		
		int i =1;
		try {
			while(true)
			{
				message = input.readLine();
				System.out.println("Client ("+ id +" ):" + message);
				System.out.println("Server :");
				
/*		
								// Print Machines
				 String filterrequest = "show";
					if (filterrequest.equalsIgnoreCase(message)) {
						getMachine();
						break;						
					}
					*/
					message = scanner.nextLine();
					output.println(message);
				
				
				
				
			}
			
			
		}catch(IOException e) {
			System.out.println("Error:" + e );
		
	}
	
	
	
	
}
}
  
  
  public static void getMachine() {
	  System.out.println("Please select Machine");
	  for(int i = 0; machinelist.length < i; i++) {
		  
		  System.out.println(machinelist[i]);
		  
	  }
	  int number = machineScan.nextInt();
	  System.out.println("You Selected Machine:" + machinelist[number-1].substring(3, machinelist[number-1].length()));
	  
	  
  }
  
  
} 
			

			
		

	


