package com.me.ats;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.validation.DataBinder;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BeanWrapperFieldSetMapperCustom<TransactionEntry> extends BeanWrapperFieldSetMapper<TransactionEntry> {

    protected void initBinder(DataBinder binder){
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("dd-MM-yyyy H:m");
        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException{
                if(StringUtils.isNotEmpty(text)){
                    setValue(LocalDateTime.parse(text,formatter));
                }else{
                    setValue(null);
                }
            }
            @Override
            public String getAsText() throws IllegalArgumentException{
                Object date = getValue();
                if(date != null){
                    return formatter.format((LocalDateTime) date);
                }else{
                    return "";
                }
            }
        });
    }
}