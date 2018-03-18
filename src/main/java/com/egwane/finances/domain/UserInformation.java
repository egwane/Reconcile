package com.egwane.finances.domain;

public class UserInformation {

    private String userName;
    private String inputPath;
    private String archivesPath;
    private String outputFile;
    private int amountColumn;

    /**
     * @return the user
     */
    public String getUserName() {
	return userName;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUserName(String userName) {
	this.userName = userName;
    }

    /**
     * @return the inputPath
     */
    public String getInputPath() {
	return inputPath;
    }

    /**
     * @param inputPath
     *            the inputPath to set
     */
    public void setInputPath(String inputPath) {
	this.inputPath = inputPath;
    }

    public void setArchivesPath(String archivesPath) {
	this.archivesPath = archivesPath;
    }

    public String getArchivesPath() {
	return archivesPath;
    }

    /**
     * @return the outputFile
     */
    public String getOutputFile() {
	return outputFile;
    }

    /**
     * @param outputFile
     *            the outputFile to set
     */
    public void setOutputFile(String outputFile) {
	this.outputFile = outputFile;
    }

    /**
     * @return the amountColumn
     */
    public int getAmountColumn() {
	return amountColumn;
    }

    /**
     * @param amountColumn
     *            the amountColumn to set
     */
    public void setAmountColumn(int amountColumn) {
	this.amountColumn = amountColumn;
    }
}
