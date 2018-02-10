package chat.server.itf;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chat.client.itf.RemoteRefItf;

public interface RegisterItf extends Remote{

	public boolean register(String userName, RemoteRefItf ref)  throws RemoteException;
	public Map<String, RemoteRefItf> listUsers()  throws RemoteException;
}
