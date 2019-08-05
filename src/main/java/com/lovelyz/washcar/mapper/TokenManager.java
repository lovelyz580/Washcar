package com.lovelyz.washcar.mapper;

import com.lovelyz.token.TokenEntity;

public interface TokenManager {
	
	public TokenEntity createToken(String userid);

	public boolean checkToken(TokenEntity entity);

	public void delToken(String userid);
	
}
