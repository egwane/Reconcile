package com.egwane.finances.domain.builders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.egwane.context.AppContext;
import com.egwane.finances.domain.IReconcileTransaction;
import com.egwane.finances.domain.Transaction;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

@SuppressWarnings("unchecked")
public class TransactionsBuilder implements Builder<List<Transaction>> {
    static final Logger logger = Logger.getLogger(TransactionsBuilder.class);
    private static ApplicationContext context = AppContext.getApplicationContext();
    private static Set<String> ignoredTransactions = (Set<String>) context.getBean("ignoredTransactions");

    private List<IReconcileTransaction> iReconcileTransactions;

    /**
     * Empty constructor
     */
    private TransactionsBuilder() {
    }

    public static TransactionsBuilder transactionsBuilder() {
	return new TransactionsBuilder();
    }

    public TransactionsBuilder withIReconcileTransactions(List<IReconcileTransaction> iReconcileTransactions) {
	// TODO modifier pour passer la liste des fichiers de l'utilisateur
	this.iReconcileTransactions = iReconcileTransactions;
	return this;
    }

    public List<Transaction> build() throws Exception {
	// TODO convertir la liste de fichiers (avec leurs transactions) en une
	// liste de transactions
	// TODO faire la conversion de devise durant cette phase

	Collection<IReconcileTransaction> transactionsToProcess = filter(this.iReconcileTransactions);

	List<Transaction> transactions = new ArrayList<Transaction>();
	for (IReconcileTransaction iReconcileTransaction : transactionsToProcess) {
	    transactions.add(TransactionBuilder.transactionBuilder().withAmount(iReconcileTransaction.getAmount())
		    .withCategory(iReconcileTransaction.getCategory()).withDate(iReconcileTransaction.getDate())
		    .withMemo(iReconcileTransaction.getMemo()).withPayee(iReconcileTransaction.getPayee()).build());
	}

	logger.info("Number of transactions to process : " + transactionsToProcess.size());
	// logger.debug("Transactions to process : " +
	// transactionsToProcess.toString());

	return transactions;
    }

    /**
     * 
     * @param iReconcileTransactions
     * @return the iReconcileTransactions to process
     */
    private static Collection<IReconcileTransaction> filter(List<IReconcileTransaction> iReconcileTransactions) {

	Predicate<IReconcileTransaction> transactionsToProcess = new Predicate<IReconcileTransaction>() {
	    public boolean apply(IReconcileTransaction iReconcileTransaction) {
		return iReconcileTransaction != null && 0 == iReconcileTransaction.getReconciled()
			&& !ignoredTransactions.contains(iReconcileTransaction.getPayee());
	    }
	};

	return Collections2.filter(iReconcileTransactions, transactionsToProcess);
    }
}
