package com.wipro.finGenie.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.finGenie.dto.FraudAlertDTO;
import com.wipro.finGenie.entity.FraudAlert;
import com.wipro.finGenie.entity.Transaction;
import com.wipro.finGenie.repository.FraudAlertRepository;
import com.wipro.finGenie.repository.TransactionRepository;
import com.wipro.finGenie.service.FraudAlertService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FraudServiceImpl implements FraudAlertService {
 
    private final FraudAlertRepository fraudAlertRepository;
    private final TransactionRepository transactionRepository;
 
    @Override
    public FraudAlertDTO saveFraudAlert(FraudAlertDTO dto) {
 
        Transaction transaction =
                transactionRepository.findById(dto.getTransactionId())
                        .orElseThrow(() ->
                                new RuntimeException("Transaction not found"));
 
        FraudAlert alert = FraudAlert.builder()
                .riskScore(dto.getRiskScore())
                .remarks(dto.getRemarks())
                .status(dto.getStatus())
                .transaction(transaction)
                .build();
 
        FraudAlert saved = fraudAlertRepository.save(alert);
 
        dto.setFraudId(saved.getFraudId());
 
        return dto;
    }
 
    @Override
    public List<FraudAlertDTO> getAllFraudAlerts() {
 
        return fraudAlertRepository.findAll()
                .stream()
                .map(alert -> {
                    FraudAlertDTO dto = new FraudAlertDTO();
 
                    dto.setFraudId(alert.getFraudId());
                    dto.setRiskScore(alert.getRiskScore());
                    dto.setRemarks(alert.getRemarks());
                    dto.setStatus(alert.getStatus());
 
                    if (alert.getTransaction() != null) {
                        dto.setTransactionId(
                                alert.getTransaction().getTransactionId());
                    }
 
                    return dto;
                })
                .toList();
    }
}