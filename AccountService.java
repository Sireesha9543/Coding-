package com.bpg.BankOfUsers.service;

import com.bpg.BankOfUsers.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    List<Account> retrieveAccounts();

    Account getAccount(Long AccountId);

    void saveAccount(Account Account);

    void deleteAccount(Long AccountId);

    void updateAccount(Account Account);

    boolean postAmount(Long accountId, BigDecimal amount);

    boolean transfer(Long fromAccountId, Long toAccountId, BigDecimal amount);
}

