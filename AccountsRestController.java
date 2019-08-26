package com.bpg.BankOfUsers.controller;

import com.bpg.BankOfUsers.controller.errors.InvalidOperationException;
import com.bpg.BankOfUsers.entity.Account;
import com.bpg.BankOfUsers.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/api/Accounts")
public class AccountsRestController {

    @Autowired
    private AccountService accountService;


    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAccounts() {
        List<Account> Accounts = accountService.retrieveAccounts();
        return Accounts;
    }

    @GetMapping("/{AccountId}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccount(@PathVariable(name = "AccountId") Long AccountId) {
        return accountService.getAccount(AccountId);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAccount(Account Account) {
        accountService.saveAccount(Account);
        System.out.println("Account Saved Successfully");
    }

    @DeleteMapping("/{AccountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public void deleteAccount(@PathVariable(name = "AccountId") Long AccountId) {
        Account acc = accountService.getAccount(AccountId);
        if (acc != null) {
            accountService.deleteAccount(AccountId);
        } else {
            throw new InvalidOperationException("Operation Failed");
        }
        System.out.println("Account Deleted Successfully");
    }

    @PutMapping("/{AccountId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateAccount(@RequestBody Account account,
                              @PathVariable(name = "AccountId") Long AccountId) {
        Account acc = accountService.getAccount(AccountId);
        if (acc != null) {
            accountService.updateAccount(account);
        } else {
            throw new InvalidOperationException("Operation Failed");
        }

        System.out.println("updateAccount  Successfully");


    }

    @PostMapping("/{AccountId}/creditAmount")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void postCreditToAccount(@PathVariable(name = "AccountId") Long AccountId, @RequestParam(name = "Amount") BigDecimal amount) {
        boolean result = accountService.postAmount(AccountId, amount);

        System.out.println("Status of creditAmount update :" + result);
        if (!result)
            throw new InvalidOperationException("Operation Failed");
    }

    @PostMapping("/{AccountId}/debitAmount")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void postDebitToAccount(@PathVariable(name = "AccountId") Long AccountId, @RequestParam(name = "Amount") BigDecimal amount) {
        boolean result = accountService.postAmount(AccountId, amount.negate());

        System.out.println("Status of debitAmount update :" + result);
        if (!result)
            throw new InvalidOperationException("Operation Failed");
    }


    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void postTransferAmountAcrossAccounts(@RequestParam(name = "fromAccountId") Long fromAccountId,
                                                 @RequestParam(name = "toAccountId") Long toAccountId,
                                                 @RequestParam(name = "Amount") BigDecimal amount) {
        boolean result = accountService.transfer(fromAccountId, toAccountId, amount);

        System.out.println("Status of Amount Transfer :" + result);
        if (!result)
            throw new InvalidOperationException("Operation Failed");
    }


}