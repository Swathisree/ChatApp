package chat.server;

import chat.client.itf.RemoteRefItf;
import chat.server.itf.ReceiveUser;

public class ReceiveUserImpl implements ReceiveUser {

	@Override
	public RemoteRefItf getUser(String userName) {
		for (String user : RegisterImpl. userInfo.keySet()) {
			//System.out.println("user\t"+user.getUserName());
			if(userName.equals(user))
				return RegisterImpl.userInfo.get(user);
		}
		return null;
	}

}
