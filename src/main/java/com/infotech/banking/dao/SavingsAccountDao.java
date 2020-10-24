package com.infotech.banking.dao;

import org.springframework.data.repository.CrudRepository;

import com.infotech.banking.domain.SavingsAccount;

public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long>
{
	SavingsAccount findByAccountNumber(int accountNumber);
}
