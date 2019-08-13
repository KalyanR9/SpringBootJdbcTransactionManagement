package com.infotech.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.infotech.dao.BankDao;
import com.infotech.dao.exception.InsufficientAccountBalanceException;
import com.infotech.service.BankService;

@Service
public class BankServiceImpl implements BankService {
	@Autowired
	private BankDao bankDao;
	
	@Transactional
	@Override
	public void transferFund(int fromAccount, int toAccount,
			Double amount) throws InsufficientAccountBalanceException {
		bankDao.withdraw(fromAccount, toAccount, amount);
		bankDao.deposit(fromAccount, toAccount, amount);
	}
}
