package chat.server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chat.client.itf.RemoteRefItf;
import chat.server.itf.RegisterItf;

public class RegisterImpl implements RegisterItf{
	
	public static Map<String, RemoteRefItf> userInfo = new HashMap<String,RemoteRefItf>();
	
	public boolean register(String userName, RemoteRefItf ref) throws RemoteException {
		if(!userInfo.containsKey(userName)){
			userInfo.put(userName,ref);
			System.out.println(userInfo.values());
			return true;
		}
		else
			return false;
	}

	@Override
	public Map<String, RemoteRefItf> listUsers() throws RemoteException {
		return  userInfo;
		
	}
}
