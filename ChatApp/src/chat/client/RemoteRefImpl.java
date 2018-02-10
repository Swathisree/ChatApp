package chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

import chat.client.itf.RemoteRefItf;

public class RemoteRefImpl implements RemoteRefItf {

	private String userRef;
	@Override
	public void setRef(String userRef) throws RemoteException {
		this.userRef = userRef;
	}
	@Override
	public boolean sendMsg(String msg, RemoteRefItf remoteRef) throws RemoteException {
		String read;
		String newMsg;
		
		boolean exit = false;
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("You have received a message");
		System.out.println("Press Enter to read");
		try {
			read = stdIn.readLine();
			if(!read.equals("")){
				System.out.println("Message removed\n");
				return false;
			}
			else
			{
				System.out.println(msg);
				do{
					newMsg = stdIn.readLine();
					if(newMsg.equals("")){
						exit = false;
					}
					remoteRef.receiveMsg(msg);
				}while(exit);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	@Override
	public boolean receiveMsg(String msg) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
