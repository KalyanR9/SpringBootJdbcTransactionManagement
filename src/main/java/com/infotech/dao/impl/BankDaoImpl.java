package com.infotech.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.infotech.dao.BankDao;
import com.infotech.dao.exception.InsufficientAccountBalanceException;
import com.infotech.dao.mapper.AccountRowMapper;
import com.infotech.model.Account;

@Repository
public class BankDaoImpl implements BankDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void withdraw(int fromAccount, int toAccount, Double amount) throws InsufficientAccountBalanceException {
		Account accountFromDb = getAccountFromDb(fromAccount);

		Double accountBalance = accountFromDb.getAccountBalance() - amount;
		if (accountFromDb.getAccountBalance() >= amount) {
			String SQL = "UPDATE icici_bank set account_balance=? WHERE account_no=?";
			int update = getJdbcTemplate().update(SQL, accountBalance,
					fromAccount);
			if (update > 0) {
				System.out.println("Amount Rs:" + amount
						+ " is tranferred from Account No:"
						+ fromAccount + " to Account No:"
						+ toAccount);
			}
		} else {
			throw new InsufficientAccountBalanceException(
					"Insufficient account balance");
		}
	}
	public void deposit(int fromAccount, int toAccount, Double amount) throws InsufficientAccountBalanceException {
		Account accountFromDb = getAccountFromDb(toAccount);
		Double accountBalance = accountFromDb.getAccountBalance()+amount;
		String SQL="UPDATE icici_bank set account_balance=? WHERE account_no=?";
		int update = getJdbcTemplate().update(SQL, accountBalance,toAccount);
		if(update>0){
			System.out.println("Amount Rs:"+amount+" is deposited in Account No:"+toAccount);
		}
		else{
			throw new InsufficientAccountBalanceException("unable to update account");
		}
	}
	
	private Account getAccountFromDb(int accountNumber) throws InsufficientAccountBalanceException{
		String SQL ="SELECT * FROM icici_bank WHERE account_no = ?";
		Account account= null;
		try {
			account = getJdbcTemplate().queryForObject(SQL,new AccountRowMapper(), accountNumber);
		} catch (DataAccessException e) {
			throw new InsufficientAccountBalanceException(accountNumber +" account no not found");
		}
		return account;
	}

}
