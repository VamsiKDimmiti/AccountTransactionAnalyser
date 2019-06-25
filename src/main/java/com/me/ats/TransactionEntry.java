package com.me.ats;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class TransactionEntry {
    @Id
    @NotNull
    private String transactionId;
    @NotNull
    private String fromAccountId;
    @NotNull
    private String toAccountId;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createAt;
    @NotNull
    private BigDecimal amount;
    private String transactionType;
    private String relatedTransaction;

    public TransactionEntry(){}
    public TransactionEntry(String transactionId, String fromAccountId, String toAccountId, LocalDateTime createAt, BigDecimal amount, String transactionType, String relatedTransaction) {
        this.transactionId = transactionId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.createAt = createAt;
        this.amount = amount;
        this.transactionType = transactionType;
        this.relatedTransaction = relatedTransaction;
    }



    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getRelatedTransaction() {
        return relatedTransaction;
    }

    public void setRelatedTransaction(String relatedTransaction) {
        this.relatedTransaction = relatedTransaction;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TransactionEntry{");

        sb.append("transactionId='").append(transactionId).append('\'');
        sb.append(", fromAccountId='").append(fromAccountId).append('\'');
        sb.append(", toAccountId='" ).append(toAccountId).append('\'' );
        sb.append(", createAt=").append(createAt);
        sb.append(", amount=").append(amount);
        sb.append(", transactionType='").append(transactionType).append( '\'');
        sb.append(", relatedTransaction='").append(relatedTransaction).append( '\'');
        sb.append('}');
        return sb.toString();
    }




}
