package com.infotech.banking.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.infotech.banking.dao.RoleDao;
import com.infotech.banking.domain.PrimaryAccount;
import com.infotech.banking.domain.SavingsAccount;
import com.infotech.banking.domain.User;
import com.infotech.banking.domain.security.UserRole;
import com.infotech.banking.service.AccountService;
import com.infotech.banking.service.EmailService;
import com.infotech.banking.userserviceimpl.UserServiceImpl;

@Controller
public class HomeController {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "redirect:/index";
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		User user = new User();

		model.addAttribute("user", user);

		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupPost(@ModelAttribute("user") User user, Model model) {

		System.out.println(user.getFirstName());

		if (userService.checkUserExists(user.getUsername(), user.getEmail())) {

			if (userService.checkEmailExists(user.getEmail())) {
				model.addAttribute("emailExists", true);
			}

			if (userService.checkUsernameExists(user.getUsername())) {
				model.addAttribute("usernameExists", true);
			}

			return "signup";
			
		} else {
			
			Set<UserRole> userRoles=new HashSet<>();
			userRoles.add(new UserRole(user,roleDao.findByName("ROLE_USER")));
			
			User localUser=userService.createUser(user, userRoles);
			
			//For Sending An Email
			
		// 	emailService.sendEmail(localUser.getEmail(), "Account Created Successfully With id "+localUser.getSavingsAccount().getAccountNumber());
			
			return "redirect:/";
		}

	}
	
	@RequestMapping("/userFront")
	public String userFront(Principal principal,Model model)
	{
		User user=userService.findByUsername(principal.getName());
		PrimaryAccount 	primaryAccount=user.getPrimaryAccount();
		SavingsAccount savingsAccount=user.getSavingsAccount();
		
		model.addAttribute("username",user.getFirstName().toUpperCase()+" "+user.getLastName().toUpperCase());
		model.addAttribute("primaryAccount",primaryAccount);
		model.addAttribute("savingsAccount", savingsAccount);
		
		return "userFront";
	}
	
	
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
	
	// for maintaining session in Redis cluster
	
	/*
	 * @RequestMapping("/userFront") public String userFront(Principal
	 * principal,Model model,HttpSession session,HttpServletRequest request)
	 *  { User
	 * user=userService.findByUsername(principal.getName()); PrimaryAccount
	 * primaryAccount=user.getPrimaryAccount(); SavingsAccount
	 * savingsAccount=user.getSavingsAccount();
	 * 
	 *
	     request.getSession().setAttribute("sessionId",session.getId());
	     request.getSession().setAttribute("username",user.getName());
	     
	     
			
		
	 * 
	 * model.addAttribute("username",user.getFirstName().toUpperCase()+" "+user.
	 * getLastName().toUpperCase());
	 * model.addAttribute("primaryAccount",primaryAccount);
	 * model.addAttribute("savingsAccount", savingsAccount);
	 * 
	 * return "userFront"; }
	 */

}
