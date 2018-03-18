package com.egwane.finances.domain.builders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.egwane.finances.domain.IReconcileTransaction;
import com.egwane.finances.domain.Transaction;
import com.egwane.finances.domain.UserTransactions;
import com.egwane.finances.domain.YearMonthDate;

public class UserTransactionsBuilder {
    private String userName;
    private List<IReconcileTransaction> iReconcileTransactions;

    /**
     * Empty constructor
     */
    private UserTransactionsBuilder() {
    }

    public static UserTransactionsBuilder userTransactionsBuilder() {
	return new UserTransactionsBuilder();
    }

    public UserTransactionsBuilder withUserName(String userName) {
	this.userName = userName;
	return this;
    }

    public UserTransactionsBuilder withIReconcileTransactions(List<IReconcileTransaction> iReconcileTransactions) {
	this.iReconcileTransactions = iReconcileTransactions;
	return this;
    }

    public UserTransactions build() throws Exception {
	UserTransactions userTransactions = new UserTransactions();
	userTransactions.setUserName(userName);
	List<Transaction> transactionsToProcess = TransactionsBuilder.transactionsBuilder().withIReconcileTransactions(
		iReconcileTransactions).build();
	Collections.sort(transactionsToProcess);

	List<Transaction> transactionsForTheCurrentMonth = new ArrayList<Transaction>();
	YearMonthDate previousYearMonth = null;
	YearMonthDate currentYearMonth = null;

	for (Transaction transaction : transactionsToProcess) {
	    currentYearMonth = YearMonthDateBuilder.yearMonthDateBuilder().withDate(transaction.getDate()).build();
	    if (isDifferentYearMonth(previousYearMonth, currentYearMonth)) {
		if (previousYearMonth != null) {
		    addMonthlyTransactions(userTransactions, transactionsForTheCurrentMonth, previousYearMonth);
		}
		transactionsForTheCurrentMonth = new ArrayList<Transaction>();
	    }
	    transactionsForTheCurrentMonth.add(transaction);
	    previousYearMonth = currentYearMonth;
	}

	// The list of transactionsForTheCurrentMonth for the last period if
	// exists.
	if (transactionsForTheCurrentMonth.size() > 0) {
	    addMonthlyTransactions(userTransactions, transactionsForTheCurrentMonth, currentYearMonth);
	}

	return userTransactions;
    }

    /**
     * @param userTransactions
     * @param transactionsForTheCurrentMonth
     * @param previousYearMonth
     * @throws Exception
     */
    private void addMonthlyTransactions(UserTransactions userTransactions,
	    List<Transaction> transactionsForTheCurrentMonth, YearMonthDate previousYearMonth) throws Exception {

	userTransactions.getMonthlyTransactionsCollection().add(
		MonthlyTransactionsBuilder.monthlyTransactionsBuilder()
			.withTransactions(transactionsForTheCurrentMonth).withYearMonthDate(previousYearMonth).build());
    }

    /**
     * @param previousYearMonth
     * @param currentYearMonth
     * @return
     */
    private boolean isDifferentYearMonth(YearMonthDate previousYearMonth, YearMonthDate currentYearMonth) {
	return !currentYearMonth.equals(previousYearMonth);
    }
}