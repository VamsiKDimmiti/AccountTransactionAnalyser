package com.me.ats;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TransactionTechnicalServiceImpl implements TransactionTechnicalService {

    private static Logger log = LoggerFactory.getLogger(TransactionTechnicalServiceImpl.class);

    @Autowired
    private TransactionEntryDao transactionEntryDao;

    public TransactionBalance getTransactionBalance(String accountId, String fromDate, String toDate) throws ATSException{
        try{

            List<TransactionEntry> allTransactionEntryList = transactionEntryDao.getAllTransactions(accountId, fromDate);
            if( null != allTransactionEntryList && allTransactionEntryList.size()>0) {
                Map<String, TransactionEntry> selectedTransactionEntryMap = new HashMap<String, TransactionEntry>();
                for (TransactionEntry te : allTransactionEntryList) {
                    if (!te.getTransactionType().equals(AccountTransactionConstants.REVERSAL)) {
                        selectedTransactionEntryMap.put(te.getTransactionId(), te);
                    } else {
                        selectedTransactionEntryMap.remove(te.getRelatedTransaction());
                    }
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                LocalDateTime toDateTime = LocalDateTime.parse(toDate, formatter);
                System.out.println("toDateTime: "+toDateTime);
                List<TransactionEntry> selectedTransactionEntryList = selectedTransactionEntryMap.values().stream().collect(Collectors.
                        toCollection(ArrayList::new));
                List<TransactionEntry> requiredTransactionList = selectedTransactionEntryList.stream()
                        .filter(p -> p.getCreateAt().isBefore(toDateTime))
                        .collect(Collectors.toCollection(() -> new ArrayList<TransactionEntry>()));
                Function<TransactionEntry, BigDecimal> amountMapper = transactionEntry -> transactionEntry.getAmount();
                BigDecimal balance = requiredTransactionList.stream().map(amountMapper).reduce(BigDecimal.ZERO, BigDecimal::add);
                return new TransactionBalance(balance.toString(),requiredTransactionList.size());
            }else{
                return null;
            }
        }catch(ATSException atse){
            throw new ATSException("Error in getting the transaction balance");
        }
    }
}
