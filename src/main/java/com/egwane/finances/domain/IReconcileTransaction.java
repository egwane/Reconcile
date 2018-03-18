package com.egwane.finances.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

public class IReconcileTransaction {
    private String payee;
    private String date;
    private String amount;
    private String category;
    private String memo;
    private String checkNumber;
    private int reconciled;

    /**
     * @return the payee
     */
    public String getPayee() {
	return payee;
    }

    /**
     * @param payee
     *            the payee to set
     */
    public void setPayee(String payee) {
	this.payee = payee;
    }

    /**
     * @return the date
     */
    public String getDate() {
	return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(String date) {
	this.date = date;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
	return amount;
    }

    /**
     * @param amount
     *            the amount to set
     */
    public void setAmount(String amount) {
	this.amount = amount;
    }

    /**
     * @return the category
     */
    public String getCategory() {
	return category;
    }

    /**
     * @param category
     *            the category to set
     */
    public void setCategory(String category) {
	this.category = category;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
	return memo;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public void setMemo(String memo) {
	this.memo = memo;
    }

    /**
     * @return the checkNumber
     */
    public String getCheckNumber() {
	return checkNumber;
    }

    /**
     * @param checkNumber
     *            the checkNumber to set
     */
    public void setCheckNumber(String checkNumber) {
	this.checkNumber = checkNumber;
    }

    /**
     * @return the reconciled
     */
    public int getReconciled() {
	return reconciled;
    }

    /**
     * @param reconciled
     *            the reconciled to set
     */
    public void setReconciled(int reconciled) {
	this.reconciled = reconciled;
    }

    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
}
