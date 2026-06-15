package com.wipro.finGenie.controller;
 
import java.util.List;
 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.wipro.finGenie.dto.AccountDTO;
import com.wipro.finGenie.dto.FraudAlertDTO;
import com.wipro.finGenie.dto.InvestmentDTO;
import com.wipro.finGenie.dto.LoanDTO;
import com.wipro.finGenie.dto.TransactionDTO;
import com.wipro.finGenie.dto.UserDTO;
import com.wipro.finGenie.service.AccountService;
import com.wipro.finGenie.service.FraudAlertService;
import com.wipro.finGenie.service.InvestmentService;
import com.wipro.finGenie.service.LoanService;
import com.wipro.finGenie.service.TransactionService;
import com.wipro.finGenie.service.UserService;
 
import lombok.RequiredArgsConstructor;
 
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
 
    private final UserService userService;
    private final AccountService accountService;
    private final TransactionService transactionService;
    private final LoanService loanService;
    private final InvestmentService investmentService;
    private final FraudAlertService fraudAlertService;
 
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
 
        return ResponseEntity.ok(
                userService.getAllUsers());
    }
 
    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
 
        return ResponseEntity.ok(
                accountService.getAllAccounts());
    }
 
    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionDTO>>
            getAllTransactions() {
 
        return ResponseEntity.ok(
                transactionService.getAllTransactions());
    }
 
    @GetMapping("/loans")
    public ResponseEntity<List<LoanDTO>> getAllLoans() {
 
        return ResponseEntity.ok(
                loanService.getAllLoans());
    }
 
    @GetMapping("/investments")
    public ResponseEntity<List<InvestmentDTO>>
            getAllInvestments() {
 
        return ResponseEntity.ok(
                investmentService.getAllInvestments());
    }
 
    @GetMapping("/fraud-alerts")
    public ResponseEntity<List<FraudAlertDTO>>
            getAllFraudAlerts() {
 
        return ResponseEntity.ok(
                fraudAlertService.getAllFraudAlerts());
    }
}
