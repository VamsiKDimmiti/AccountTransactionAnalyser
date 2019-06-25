package com.me.ats;

import org.hibernate.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class TransactionEntryDaoImpl implements  TransactionEntryDao{

    private static Logger log = LoggerFactory.getLogger(TransactionEntryDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TransactionEntry> getAllTransactions(String accountId, String fromDate) throws ATSException{
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            LocalDateTime fromDateTime = LocalDateTime.parse(fromDate, formatter);
            final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            final CriteriaQuery<TransactionEntry> cq = cb.createQuery(TransactionEntry.class);
            final Root<TransactionEntry> accntrans = cq.from(TransactionEntry.class);
            cq.select(accntrans);
            cq.where(
                    cb.and(
                            cb.equal(accntrans.get(AccountTransactionConstants.ACCOUNT_ID),accountId),
                            cb.between(accntrans.get(AccountTransactionConstants.TIME_PERIOD),fromDateTime,LocalDateTime.now())
                    )
            );
            return entityManager.createQuery(cq).getResultList();
        }catch(NoResultException ne){
            return null;
        }catch(DataAccessException | DataException de){
            log.error("Error in retrieving the data from Accounts Transactions table: "+de );
            throw new ATSException("Error in retrieving the data from Accounts Transactions table: "+de.getMessage());
        }catch(Exception e){
            log.error("Error in retrieving the data from Accounts Transactions table: "+e );
            throw new ATSException("Error in retrieving the data from Accounts Transactions table: "+e.getMessage());
        }
    }
}
