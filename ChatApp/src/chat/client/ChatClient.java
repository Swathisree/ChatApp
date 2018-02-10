package chat.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

import chat.client.itf.RemoteRefItf;
import chat.server.itf.ReceiveUser;
import chat.server.itf.RegisterItf;

public class ChatClient {
	public static void main(String [] args) {
		try {
				if (args.length < 1) {
				   System.out.println("Usage: java ChatClient <rmiregistry host>");
				   return;
				}	
				String host = args[0];
				String user2;
				String userName;
				String mapVal;
				int input;
				boolean valid = false;
				boolean register;
				boolean notError = true;
				
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
				Registry registry = LocateRegistry.getRegistry(host);
				System.out.println("Welcome to the Chat Application!\n ");
				System.out.println("Enter your name\n");
				userName = stdIn.readLine();
				do{
					System.out.println("Press 1- To join the chat application: \n");
					System.out.println("Press 2- See the available users: \n");
					System.out.println("Press 3- To chat with user: \n");
					System.out.println("Press 4- To exit: \n");
					input = Integer.parseInt(stdIn.readLine());
					switch(input){
						case 1: 
								do{
									if(userName==null || userName.equals("")){
										System.out.println("Please Enter your name\n");
										userName = stdIn.readLine();
										valid = true;
									}
									else
										valid = false;
								}while(valid);
								
								RemoteRefImpl remoteRef = new RemoteRefImpl();
								remoteRef.setRef(userName);
								RemoteRefItf remoteRefStub = (RemoteRefItf) UnicastRemoteObject.exportObject(remoteRef, 0);
								RegisterItf reg = (RegisterItf) registry.lookup("RegisterService");
								register = reg.register(userName,remoteRefStub);
								if(!register){
									System.out.println("Sorry, Unable to register to the application");
									notError = false;
								}
								else
									System.out.println("Registered Successfully!");
								break;
						case 2: 
								
								RegisterItf reg1 = (RegisterItf) registry.lookup("RegisterService");
								Map<String, RemoteRefItf> list = reg1.listUsers();
								System.out.println(list.keySet());
								
								break;
						case 3: System.out.println("Enter the user name you want to chat with\n");
								user2 = stdIn.readLine();
								ReceiveUser recStub = (ReceiveUser) registry.lookup("ReceiveService");
								RemoteRefItf remoteRefRec =  recStub.getUser(user2);
								if(remoteRefRec!=null){
									System.out.println("User is available to chat\n");
									//System.out.println("RegistryImpl.userInfo.get(toClient)"+RegistryImpl.userInfo.get(user).getServName());
									System.out.println("Connecting with the user " + user2);
									RemoteRefImpl remoteRefSender = new RemoteRefImpl();
									remoteRefSender.setRef(userName);
									RemoteRefItf remoteRefSenderStub = (RemoteRefItf) UnicastRemoteObject.exportObject(remoteRefSender, 0);
									remoteRefRec.sendMsg("Hi", remoteRefSenderStub);
								}
								else{
									System.out.println("The User is not available to chat\n");
									notError = false;
								}
						}
				}while(notError);
		}
		catch (Exception e)  {
			System.err.println("Error on client: \t" + e);
			e.printStackTrace();
		}
		
	}
}
