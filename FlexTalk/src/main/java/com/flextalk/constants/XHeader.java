package com.flextalk.constants;

public class XHeader {
	
	public static final String AccessToken = "AccessToken";
	public static final String RefreshToken = "RefreshToken";
	public static final String X_USER_ID = "X-USER-ID";
	public static final String X_ROOM_ID = "X-ROOM-ID";

	private XHeader() {
		new AssertionError();
	}
}
