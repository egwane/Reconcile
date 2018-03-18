package com.egwane.finances.domain.builders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.egwane.context.AppContext;
import com.egwane.finances.domain.Transaction;

@SuppressWarnings("unchecked")
public class TransactionBuilder implements Builder<Transaction> {
    private static final Logger logger = Logger.getLogger(TransactionBuilder.class);
    private static final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    private static ApplicationContext context = AppContext.getApplicationContext();
    private static Map<String, String> categories = (Map<String, String>) context.getBean("categories");

    private String payee;
    private Date date;
    private double amount = 0.0;
    private String category;
    private String memo;

    /**
     * Empty constructor
     */
    private TransactionBuilder() {
    }

    public static TransactionBuilder transactionBuilder() {
	return new TransactionBuilder();
    }

    public TransactionBuilder withPayee(String payee) {
	this.payee = payee;
	return this;
    }

    public TransactionBuilder withDate(String date) throws ParseException {
	this.date = formatter.parse(date);
	return this;
    }

    public TransactionBuilder withAmount(String amount) throws ParseException {
	this.amount = Double.parseDouble(amount);
	return this;
    }

    public TransactionBuilder withCategory(String category) {
	this.category = category;
	return this;
    }

    public TransactionBuilder withMemo(String memo) {
	this.memo = memo;
	return this;
    }

    public Transaction build() {
	Transaction transaction = new Transaction();
	transaction.setPayee(payee);
	transaction.setDate(date);
	transaction.setAmount(amount * -1);
	transaction.setCategory(buildCategory());
	transaction.setMemo(memo);

	return transaction;
    }

    /**
     * @return
     */
    private String buildCategory() {
	String newCategory = categories.get(category);
	if (newCategory != null) {
	    return newCategory;
	} else {
	    logger.info("iReconcile category '" + category + "' does not match any defined category.");
	    return category;
	}
    }
}
