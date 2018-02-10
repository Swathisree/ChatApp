package chat.client.itf;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteRefItf extends Remote{
	
	public void setRef(String userRef) throws RemoteException;
	public boolean sendMsg(String msg, RemoteRefItf remoteRef) throws RemoteException;
	public boolean receiveMsg(String msg) throws RemoteException;
}
