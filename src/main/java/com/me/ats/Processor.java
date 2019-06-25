
package com.me.ats;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Component
public class Processor implements ItemProcessor<TransactionEntry, TransactionEntry> {

    @Override
    public TransactionEntry process(TransactionEntry transaction) throws Exception{

        final String transactionId = transaction.getTransactionId();
        final String fromAccountId = transaction.getFromAccountId();
        final String toAccountId = transaction.getToAccountId();
        final LocalDateTime createdAt = transaction.getCreateAt();
        final BigDecimal amount = transaction.getAmount();
        final String transactionType = transaction.getTransactionType();
        final String relatedTransaction = transaction.getRelatedTransaction();

        final TransactionEntry transformedTranEntry = new TransactionEntry(transactionId, fromAccountId, toAccountId, createdAt,
                amount, transactionType, relatedTransaction);
        return transformedTranEntry;
    }
}

