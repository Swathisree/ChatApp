package chat.client.itf;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserItf extends Remote {
	
	public void setUserName(String userName)  throws RemoteException;
	public String getUserName()  throws RemoteException;
}
