package com.flextalk.logger;

import com.flextalk.util.FlexTalkUtil;

public interface Logger {

	public Logger info(String data);
	public Logger debug(String data);
	public Logger error(Exception e);

	default String now() {
		return FlexTalkUtil.getNOWString("yyyy-MM-dd HH:mm:ss");
	}
}
