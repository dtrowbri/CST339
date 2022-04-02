package com.gcu.data;

public interface AuthenticationDataAccessInterface<T> {
	public boolean AuthenticateUser(T t);
}
