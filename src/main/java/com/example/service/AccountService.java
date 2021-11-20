package com.example.service;

import com.example.AppException;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.request.AccountInsertRequest;
import com.example.request.AccountUpdateRequest;
import com.example.response.AccountListResponse;
import com.example.response.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class AccountService extends ServiceBase {

    @Autowired
    AccountRepository accountRepository;

    public AccountListResponse list() {
        List<Account> accountList = accountRepository.list();

        return AccountListResponse.builder()
                                  .returnCode(0)
                                  .recordCount(CollectionUtils.isEmpty(accountList) ? 0 : accountList.size())
                                  .messageText(CollectionUtils.isEmpty(accountList) ? "empty." : "count=" + accountList.size())
                                  .accountList(accountList)
                                  .build();
    }

    public AccountResponse get(String emailAddress) {
        Account account = accountRepository.get(emailAddress);

        return AccountResponse.builder()
                              .returnCode(0)
                              .recordCount(ObjectUtils.isEmpty(account) ? 0 : 1)
                              .messageText(ObjectUtils.isEmpty(account) ? "empty" : "login")
                              .account(account)
                              .build();
    }

    @Transactional(rollbackFor = AppException.class)
    public AccountResponse insert(AccountInsertRequest accountInsertRequest)
    throws AppException {
        try {
            if (accountRepository.exists(accountInsertRequest.getEmailAddress())) {
                throw createAppException("duplicate.", HttpStatus.BAD_REQUEST);
            }

            Account account = Account.builder()
                                     .emailAddress(accountInsertRequest.getEmailAddress())
                                     .nickName(accountInsertRequest.getNickName())
                                     .deleteFlag(false)
                                     .createdBy(AccountService.class.getName())
                                     .updatedBy(AccountService.class.getName())
                                     .build();

            if (accountRepository.insert(account) != 1) {
                throw createAppException("insert error.", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return AccountResponse.builder()
                                  .returnCode(0)
                                  .recordCount(1)
                                  .messageText("insert")
                                  .account(account)
                                  .build();

        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw createAppException(e.getClass().getName(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(rollbackFor = AppException.class)
    public AccountResponse update(AccountUpdateRequest accountUpdateRequest)
    throws AppException {
        try {
            if (!accountRepository.exists(accountUpdateRequest.getEmailAddress())) {
                throw createAppException("target nothing.", HttpStatus.BAD_REQUEST);
            }

            Account account = Account.builder()
                                     .accountNumber(accountUpdateRequest.getAccountNumber())
                                     .emailAddress(accountUpdateRequest.getEmailAddress())
                                     .nickName(accountUpdateRequest.getNickName())
                                     .updatedBy(AccountService.class.getName())
                                     .build();

            if (accountRepository.update(account) != 1) {
                throw createAppException("update error.", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return AccountResponse.builder()
                                  .returnCode(0)
                                  .recordCount(1)
                                  .messageText("update")
                                  .account(account)
                                  .build();

        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw createAppException(e.getClass().getName(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
