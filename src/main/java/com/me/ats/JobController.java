package com.me.ats;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class JobController implements TransactionController {

    @Autowired
    private TransactionTechnicalService transactionTechnicalService;

    @RequestMapping("/checkBalance")
    public String checkBalance(@RequestParam(value = "accountId") String accountId,
                               @RequestParam(value = "fromDate") String fromDate,
                               @RequestParam(value = "toDate") String toDate) throws ATSException{
        try{
            TransactionBalance result = transactionTechnicalService.getTransactionBalance(accountId, fromDate, toDate);
            if(result== null){
                return "Unable to get the balance for the given account";
            }else{
                return "Relative balance for the period is: $"+result.getBalance()
                        +"\n"+"Number of transactions included is: "+result.getTransactionCount();
            }

        }catch(Exception e){
            return "Excpetion occured while getting the balance";
        }

    }
}
