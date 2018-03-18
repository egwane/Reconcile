package com.egwane.finances.domain;

import java.util.ArrayList;
import java.util.List;

public class MonthlyTransactions {
    YearMonthDate yearMonthDate = null;
    List<Transaction> transactions = new ArrayList<Transaction>();

    /**
     * @return the yearMonthDate
     */
    public YearMonthDate getYearMonthDate() {
	return yearMonthDate;
    }

    /**
     * @param yearMonthDate
     *            the yearMonthDate to set
     */
    public void setYearMonthDate(YearMonthDate yearMonthDate) {
	this.yearMonthDate = yearMonthDate;
    }

    /**
     * @return the transactions
     */
    public List<Transaction> getTransactions() {
	return transactions;
    }

    /**
     * @param transactions
     *            the transactions to set
     */
    public void setTransactions(List<Transaction> transactions) {
	this.transactions = transactions;
    }
}
