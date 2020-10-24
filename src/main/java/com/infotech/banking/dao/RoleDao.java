package com.infotech.banking.dao;

import org.springframework.data.repository.CrudRepository;

import com.infotech.banking.domain.security.Role;

public interface RoleDao extends CrudRepository<Role, Integer>
{
	Role findByName(String name);

}
