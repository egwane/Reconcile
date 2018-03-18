package com.egwane.finances.domain;

import java.util.ArrayList;
import java.util.List;

public class UserTransactions {

    private String userName;
    List<MonthlyTransactions> monthlyTransactionsCollection = new ArrayList<MonthlyTransactions>();

    public UserTransactions() {
    }

    /**
     * @return the userName
     */
    public String getUserName() {
	return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
	this.userName = userName;
    }

    /**
     * @return the monthlyTransactionsCollection
     */
    public List<MonthlyTransactions> getMonthlyTransactionsCollection() {
	return monthlyTransactionsCollection;
    }

    /**
     * @param monthlyTransactionsCollection
     *            the monthlyTransactionsCollection to set
     */
    public void setMonthlyTransactionsCollection(List<MonthlyTransactions> monthlyTransactionsCollection) {
	this.monthlyTransactionsCollection = monthlyTransactionsCollection;
    }
}
