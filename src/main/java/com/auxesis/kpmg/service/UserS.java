package com.auxesis.kpmg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auxesis.kpmg.repository.UsersI;

@Service
public class UserS {

	@Autowired
	public UsersI users;

}
