package com.infotech.banking.service;

import java.security.Principal;

import com.infotech.banking.domain.PrimaryAccount;
import com.infotech.banking.domain.SavingsAccount;

public interface AccountService
{
	PrimaryAccount createPrimaryAccount();
    SavingsAccount createSavingsAccount();
    void deposit(String accountType, double amount, Principal principal);
    void withdraw(String accountType, double amount, Principal principal);
    

}
