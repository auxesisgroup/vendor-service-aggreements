package com.auxesis.kpmg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auxesis.kpmg.repository.ContractI;

@Service
public class ContractS {

	@Autowired
	public ContractI contract;
}
