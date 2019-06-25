package com.me.ats;

import java.util.Map;

public interface TransactionTechnicalService {
    public TransactionBalance getTransactionBalance(String accountId,String fromDate, String toDate) throws ATSException;
}
