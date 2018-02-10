package chat.client;

import chat.client.itf.UserItf;

public class UserImpl implements UserItf {

	private String userName;

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String getUserName() {
		return userName;
	}

}
