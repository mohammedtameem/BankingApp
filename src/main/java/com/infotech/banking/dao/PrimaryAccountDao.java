package com.infotech.banking.dao;

import org.springframework.data.repository.CrudRepository;
import com.infotech.banking.domain.PrimaryAccount;

public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount, Long>
{
	PrimaryAccount findByAccountNumber (int accountNumber);
}
