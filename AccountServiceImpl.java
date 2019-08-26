package com.bpg.BankOfUsers.service.impl;

import com.bpg.BankOfUsers.entity.Account;
import com.bpg.BankOfUsers.repository.AccountRepository;
import com.bpg.BankOfUsers.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author JavaSolutionsGuide
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> retrieveAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts;
    }

    public Account getAccount(Long accountId) {
        Optional<Account> optEmp = accountRepository.findById(accountId);
        return optEmp.get();
    }

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }

    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public boolean postAmount(Long accountId, BigDecimal amount) {
        Optional<Account> optEmp = accountRepository.findById(accountId);
        if (optEmp.isPresent()) {
            Account account = optEmp.get();
            BigDecimal bal = account.getBalance().add(amount);
            if (bal.compareTo(BigDecimal.ZERO) >= 0) {
                account.setBalance(bal);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        boolean debit = postAmount(fromAccountId, amount.negate());
        if (debit)
            return postAmount(toAccountId, amount);

        return false;
    }
}