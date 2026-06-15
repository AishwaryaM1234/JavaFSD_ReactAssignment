package com.wipro.finGenie.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.finGenie.dto.TransactionDTO;
import com.wipro.finGenie.entity.Account;
import com.wipro.finGenie.entity.Transaction;
import com.wipro.finGenie.repository.AccountRepository;
import com.wipro.finGenie.repository.TransactionRepository;
import com.wipro.finGenie.service.TransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
 
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
 
    @Override
    public TransactionDTO createTransaction(TransactionDTO dto) {
 
        Account account = accountRepository.findById(dto.getAccountId())
                .orElseThrow(() ->
                        new RuntimeException("Account not found"));
 
        Transaction transaction = Transaction.builder()
                .amount(dto.getAmount())
                .transactionType(dto.getTransactionType())
                .description(dto.getDescription())
                .transactionDate(LocalDateTime.now())
                .account(account)
                .build();
 
        Transaction saved = transactionRepository.save(transaction);
 
        dto.setTransactionId(saved.getTransactionId());
 
        return dto;
    }
 
    @Override
    public TransactionDTO getTransactionById(Long id) {
 
        Transaction transaction =
                transactionRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Transaction not found"));
 
        TransactionDTO dto = new TransactionDTO();
 
        dto.setTransactionId(transaction.getTransactionId());
        dto.setAmount(transaction.getAmount());
        dto.setTransactionType(transaction.getTransactionType());
        dto.setDescription(transaction.getDescription());
 
        if (transaction.getAccount() != null) {
            dto.setAccountId(
                    transaction.getAccount().getAccountId());
        }
 
        return dto;
    }
 
    @Override
    public List<TransactionDTO> getAllTransactions() {
 
        return transactionRepository.findAll()
                .stream()
                .map(transaction -> {
 
                    TransactionDTO dto = new TransactionDTO();
 
                    dto.setTransactionId(
                            transaction.getTransactionId());
 
                    dto.setAmount(
                            transaction.getAmount());
 
                    dto.setTransactionType(
                            transaction.getTransactionType());
 
                    dto.setDescription(
                            transaction.getDescription());
 
                    if (transaction.getAccount() != null) {
                        dto.setAccountId(
                                transaction.getAccount()
                                        .getAccountId());
                    }
 
                    return dto;
                })
                .toList();
    }
}