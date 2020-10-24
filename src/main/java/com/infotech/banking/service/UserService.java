package com.infotech.banking.service;
import java.util.Set;

import com.infotech.banking.domain.User;
import com.infotech.banking.domain.security.UserRole;

public interface UserService {
	
	User findByUsername(String username);

    User findByEmail(String email);

    boolean checkUserExists(String username, String email);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);
    
    void save(User user); 
    
    public User createUser(User user, Set<UserRole> userRoles);
    
    }
