package com.me.ats;

import java.util.List;

public interface TransactionEntryDao {
    public List<TransactionEntry> getAllTransactions(String accountId, String fromDate) throws ATSException;
}
