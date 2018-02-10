package chat.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import chat.server.itf.ReceiveUser;
import chat.server.itf.RegisterItf;


public class ChatServer {
	public static void  main(String [] args) {
		  try {

			RegisterImpl reg = new RegisterImpl();
			ReceiveUserImpl rec = new ReceiveUserImpl();
		    RegisterItf regStub = (RegisterItf) UnicastRemoteObject.exportObject(reg, 0);
		    ReceiveUser recStub = (ReceiveUser) UnicastRemoteObject.exportObject(rec,0);
		    Registry registry= LocateRegistry.getRegistry(); 
		    registry.bind("RegisterService", regStub);
		    registry.bind("ReceiveService", recStub);
		    System.out.println ("Server ready");
		  } catch (Exception e) {
			  System.err.println("Error on server :" + e) ;
			  e.printStackTrace();
		  }
	  }
}
