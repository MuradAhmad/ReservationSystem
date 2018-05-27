import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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




public class ReplicaServer {
	
	ServerSocket serverSocket;
	Socket acceptSocket;
	static Scanner machineScan = new Scanner(System.in);

	String message, reply;
	int port;
	ExecutorService pool = null;
	int clientCount =0;
	
	static String[] machinelist = {"1) Printer", "2) 3D Printer","3) Scanner","4) Cutter" }; 

	public static void main(String args[]) throws IOException {
		Server server = new Server(9999);
		server.startServer();

	}
	ReplicaServer(int port){
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

			
			
			}




  private static class ServerThread implements Runnable {
	
	
	ReplicaServer server = null;
	Socket clientSocket;
	PrintStream output;
	BufferedReader input;
	Scanner scanner = new Scanner(System.in);
	int id;
	String message;
	
	ServerThread(Socket client, int count, ReplicaServer server) throws IOException {
		
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
				
		
								// Print Machines
				 String filterrequest = "show";
					if (filterrequest.equalsIgnoreCase(message)) {
						//getMachine();
						 System.out.println("1) Printer" +" "+ "2) 3D Printer" +" "+ "3) Scanner" +" "+ "4) Cutter");
						
						
												
					}
					
					message = scanner.nextLine();
					output.println(message);
				
				if(message.equalsIgnoreCase("1")) {
					System.out.println("You Reserved Machine: Printer "  );
					
				}else
				if(message.equalsIgnoreCase("2")) {
					System.out.println("You Reserved Machine: 3D Printer "  );
					
				}else
				if(message.equalsIgnoreCase("3")) {
					System.out.println("You Reserved Machine: Scanner "  );
					
				}else
				if(message.equalsIgnoreCase("4")) {
					System.out.println("You Reserved Machine: Cutter "  );
					
				}else System.out.println("Select machine "  );
				
				
			}
			
			
		}catch(IOException e) {
			System.out.println("Error:" + e );
		
	}
	
	
	
	
}
}
  
  
  public static void getMachine() {
	  System.out.println("Please select Machine");

	  System.out.println("1) Printer" +" "+ "2) 3D Printer" +" "+ "3) Scanner" +" "+ "4) Cutter");
	  
  }
  
  

	
	
	
	
	

}
