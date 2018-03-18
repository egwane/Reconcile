package com.egwane.finances.domain.builders;

import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.egwane.finances.domain.IReconcileTransaction;

public class IReconcileTransactionBuilder implements Builder<IReconcileTransaction> {
    private static final Logger logger = Logger.getLogger(IReconcileTransactionBuilder.class);

    private String payee;
    private String date;
    private String amount;
    private String category;
    private String memo;
    private String checkNumber;
    private int reconciled;

    private boolean malformedObject = false;

    /**
     * Empty constructor
     */
    private IReconcileTransactionBuilder() {
    }

    public static IReconcileTransactionBuilder iReconcileTransactionBuilder() {
	return new IReconcileTransactionBuilder();
    }

    public IReconcileTransactionBuilder withCSV(String csv) {
	// logger.debug("Begin withCSV(), csv = " + csv);

	StringTokenizer stringTokenizer = new StringTokenizer(csv, ",");
	if (stringTokenizer.countTokens() != 7) {
	    logger.error("Malformed line = " + csv);
	    this.malformedObject = true;
	    return this;
	}
	this.payee = extractNextToken(stringTokenizer);
	this.date = extractNextToken(stringTokenizer);
	this.amount = extractNextToken(stringTokenizer);
	this.category = extractNextToken(stringTokenizer);
	this.memo = extractNextToken(stringTokenizer);
	this.checkNumber = extractNextToken(stringTokenizer);
	this.reconciled = Integer.parseInt(extractNextToken(stringTokenizer));

	// logger.debug("End withCSV()");
	return this;
    }

    /**
     * @param stringTokenizer
     * @return
     */
    private String extractNextToken(StringTokenizer stringTokenizer) {
	String nextToken = stringTokenizer.nextToken();
	if (!nextToken.isEmpty())
	    nextToken = nextToken.trim();
	return nextToken;
    }

    public IReconcileTransaction build() {
	if (this.malformedObject)
	    return null;

	IReconcileTransaction transaction = new IReconcileTransaction();
	transaction.setPayee(payee);
	transaction.setDate(date);
	transaction.setAmount(amount);
	transaction.setCategory(category);
	transaction.setMemo(memo);
	transaction.setCheckNumber(checkNumber);
	transaction.setReconciled(reconciled);

	return transaction;
    }
}
