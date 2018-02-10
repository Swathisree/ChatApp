package chat.server.itf;

import java.rmi.Remote;
import java.rmi.RemoteException;

import chat.client.itf.RemoteRefItf;

public interface ReceiveUser extends Remote{
	public RemoteRefItf getUser(String userName) throws RemoteException;
}
