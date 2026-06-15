package com.wipro.finGenie.util;
 
public class LoanPredictionEngine {
 
    private static LoanPredictionEngine instance;
 
    private LoanPredictionEngine() {
    }
 
    public static synchronized
    LoanPredictionEngine getInstance() {
 
        if(instance == null) {
            instance =
                    new LoanPredictionEngine();
        }
 
        return instance;
    }
 
    public String predictLoan(
            double salary,
            int creditScore) {
 
        if(salary > 50000 &&
                creditScore > 700) {
 
            return "APPROVED";
        }
 
        return "REJECTED";
    }
}