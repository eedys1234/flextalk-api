package com.flextalk.user;

import com.flextalk.common.AbstractBaseEnumConverter;
import com.flextalk.user.User.UserType;

public class UserTypeConverter extends AbstractBaseEnumConverter<UserType, String>{

	@Override
	protected UserType[] getValueList() {
		return UserType.values();
	}
}
