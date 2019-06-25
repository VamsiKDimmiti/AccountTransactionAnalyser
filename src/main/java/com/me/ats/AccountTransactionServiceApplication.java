package com.me.ats;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class AccountTransactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountTransactionServiceApplication.class, args);
	}


	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	@Qualifier("job")
	Job job;

	public BatchStatus job() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
		Map<String, JobParameter> maps = new HashMap<String, JobParameter>();
		maps.put("time", new JobParameter(System.currentTimeMillis()));
		JobParameters jobParameters = new JobParameters(maps);
		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		System.out.println("JobExecusion status: "+jobExecution.getStatus());
		System.out.println("Batch is running...");
		while(jobExecution.isRunning()){
			System.out.println("...");
		}
		return jobExecution.getStatus();
	}

}
