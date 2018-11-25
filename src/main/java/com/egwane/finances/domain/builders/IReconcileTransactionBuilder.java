package com.egwane.finances.domain.builders;

import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.egwane.finances.domain.IReconcileTransaction;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

public class IReconcileTransactionBuilder implements Builder<IReconcileTransaction> {
    private static final Logger logger          = Logger.getLogger(IReconcileTransactionBuilder.class);

    private String              payee;
    private String              date;
    private String              amount;
    private String              category;
    private String              memo;
    private String              checkNumber;
    private int                 reconciled;

    private boolean             malformedObject = false;

    /**
     * Empty constructor
     */
    private IReconcileTransactionBuilder() {
    }

    public static IReconcileTransactionBuilder iReconcileTransactionBuilder() {
        return new IReconcileTransactionBuilder();
    }

    public IReconcileTransactionBuilder withCSV(String csv) {
        logger.debug("Begin withCSV(), csv = " + csv);

        Splitter splitter = Splitter.on(';').trimResults();
        String[] tokens = Iterables.toArray(splitter.split(csv), String.class);

        if (tokens.length != 7) {
            logger.error("Malformed line = " + csv + ", count = " + tokens.length);
            this.malformedObject = true;
            return this;
        }

        this.payee = tokens[0];
        this.date = tokens[1];
        this.amount = tokens[2];
        this.category = tokens[3];
        this.memo = tokens[4];
        this.checkNumber = tokens[5];
        this.reconciled = Integer.parseInt(tokens[6]);

        logger.debug("End withCSV()");
        return this;
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
