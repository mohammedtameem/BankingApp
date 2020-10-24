package com.infotech.banking.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.infotech.banking.domain.PrimaryTransaction;


public interface PrimaryTransactionDao extends CrudRepository<PrimaryTransaction, Long> {

    List<PrimaryTransaction> findAll();
}
