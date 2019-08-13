package com.infotech.service;

import com.infotech.dao.exception.InsufficientAccountBalanceException;

public interface BankService {
	void transferFund(int fromAccount,int toAccount,Double amount)throws InsufficientAccountBalanceException;
}
