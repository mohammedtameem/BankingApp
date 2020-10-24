package com.infotech.banking.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.infotech.banking.domain.PrimaryAccount;
import com.infotech.banking.domain.PrimaryTransaction;
import com.infotech.banking.domain.SavingsAccount;
import com.infotech.banking.domain.SavingsTransaction;
import com.infotech.banking.domain.User;
import com.infotech.banking.service.AccountService;
import com.infotech.banking.service.TransactionService;
import com.infotech.banking.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private TransactionService transactionService;
	
	
	@RequestMapping("/primaryAccount")
	public String primaryAccount(Model model,Principal principal) 
	{
		List<PrimaryTransaction> primaryTransactionList = transactionService.findPrimaryTransactionList(principal.getName());
        User user=userService.findByUsername(principal.getName());
		PrimaryAccount primaryAccount=user.getPrimaryAccount();
		model.addAttribute("primaryAccount",primaryAccount);
		model.addAttribute("primaryTransactionList", primaryTransactionList);
		
		return "primaryAccount";
	}
	
	@RequestMapping("/savingsAccount")
	public String savingsAccount(Model model,Principal principal)   {
		
		List<SavingsTransaction> savingsTransactionList = transactionService.findSavingsTransactionList(principal.getName());
		
		User user=userService.findByUsername(principal.getName());
		SavingsAccount savingsAccount=user.getSavingsAccount();
		model.addAttribute("savingsAccount", savingsAccount);
		 model.addAttribute("savingsTransactionList", savingsTransactionList);
		return "savingsAccount";
	}
	
	
	// common method to send the data 
	
	@ModelAttribute
	public void commonData(final Model model,Principal principal)
	{
		User user=userService.findByUsername(principal.getName());
		model.addAttribute("username", user.getFirstName().toUpperCase()+" "+user.getLastName().toUpperCase());
	}
	
	
	
	@RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public String deposit(Model model) {
        model.addAttribute("accountType", "");
        model.addAttribute("amount", "");

        return "deposit";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String depositPOST(@ModelAttribute("amount") String amount, @ModelAttribute("accountType") String accountType, Principal principal) {
        accountService.deposit(accountType, Double.parseDouble(amount), principal);

        return "redirect:/userFront";
    }
    
    // Code for withdraw
    
    @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
    public String withdraw(Model model) {
        model.addAttribute("accountType", "");
        model.addAttribute("amount", "");

        return "withdraw";
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public String withdrawPOST(@ModelAttribute("amount") String amount, @ModelAttribute("accountType") String accountType, Principal principal) {
        
       	accountService.withdraw(accountType, Double.parseDouble(amount), principal);
       	return "redirect:/userFront";

    }
       
        
}
        

	
