package com.egwane.finances.domain;

import java.util.Date;

public class Transaction implements Comparable<Transaction> {

    private String payee;
    private Date date;
    private double amount = 0.0;
    private String category;
    private String memo;

    public Transaction() {
    }

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
    public Date getDate() {
	return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(Date date) {
	this.date = date;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
	return amount;
    }

    /**
     * @param amount
     *            the amount to set
     */
    public void setAmount(double amount) {
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
	 * 
	 */
    public int compareTo(Transaction other) {
	return this.getDate().compareTo(other.getDate());
    }
}
