package com.egwane.finances.domain.builders;

import java.util.ArrayList;
import java.util.List;

import com.egwane.finances.domain.MonthlyTransactions;
import com.egwane.finances.domain.Transaction;
import com.egwane.finances.domain.YearMonthDate;

public class MonthlyTransactionsBuilder implements Builder<MonthlyTransactions> {
    YearMonthDate yearMonthDate = null;
    List<Transaction> transactions = new ArrayList<Transaction>();

    private MonthlyTransactionsBuilder() {
    }

    public static MonthlyTransactionsBuilder monthlyTransactionsBuilder() {
	return new MonthlyTransactionsBuilder();
    }

    public MonthlyTransactionsBuilder withYearMonthDate(YearMonthDate yearMonthDate) {
	this.yearMonthDate = yearMonthDate;
	return this;
    }

    public MonthlyTransactionsBuilder withTransactions(List<Transaction> transactions) {
	this.transactions = transactions;
	return this;
    }

    public MonthlyTransactions build() throws Exception {
	MonthlyTransactions monthlyTransactions = new MonthlyTransactions();
	monthlyTransactions.setTransactions(transactions);
	monthlyTransactions.setYearMonthDate(yearMonthDate);
	return monthlyTransactions;
    }
}
