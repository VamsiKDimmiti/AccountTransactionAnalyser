package com.me.ats;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class TransactionJobConfig {

    @Bean
public Job job(JobBuilderFactory jobBuilderFactory,
               StepBuilderFactory stepBuilderFactory,
                   ItemReader<TransactionEntry> itemReader,
                   ItemProcessor<TransactionEntry, TransactionEntry> itemProcessor,
                   ItemWriter<TransactionEntry> itemWriter) {

        Step step = stepBuilderFactory.get("csvFileToDatabaseStep")
                .<TransactionEntry, TransactionEntry>chunk(2)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

        return jobBuilderFactory.get("csvFileToDatabaseJob")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public FlatFileItemReader<TransactionEntry> itemReader(@Value("${inputFile}") Resource resource){
        FlatFileItemReader<TransactionEntry> reader = new FlatFileItemReader<TransactionEntry>();
        reader.setResource(resource);
        reader.setName("JobItem-Reader");
        reader.setLineMapper(lineMapper());
        return reader;
    }

    @Bean
    public LineMapper<TransactionEntry> lineMapper(){
        DefaultLineMapper defaultLineMapper = new DefaultLineMapper<TransactionEntry>();
        DelimitedLineTokenizer delimitedLineTokenizer =  new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(",");
        delimitedLineTokenizer.setStrict(false);
        delimitedLineTokenizer.setNames(new String[] {"transactionId","fromAccountId","toAccountId","createAt",
                "amount","transactionType","relatedTransaction"});
        BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapperCustom<TransactionEntry>();
        fieldSetMapper.setTargetType(TransactionEntry.class);
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }




}
