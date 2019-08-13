package com.infotech.dao;

import com.infotech.dao.exception.InsufficientAccountBalanceException;

public interface BankDao {
	void deposit(int fromAccount,int toAccount,Double amount) throws InsufficientAccountBalanceException;
	void withdraw(int fromAccount,int toAccount,Double amount) throws InsufficientAccountBalanceException;
}
