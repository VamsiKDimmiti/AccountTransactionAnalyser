package com.me.ats;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<TransactionEntry> {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void write(List<? extends TransactionEntry> transactionEntry) throws Exception{
        System.out.println("Data saved for transactions:"+transactionEntry);
        transactionRepository.saveAll(transactionEntry);
    }
}
