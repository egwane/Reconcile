package com.egwane.finances.domain.builders;

import java.io.File;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

import com.egwane.finances.domain.MonthlyTransactions;
import com.egwane.finances.domain.UserTransactions;

public class WritableWorkbookBuilder implements Builder<WritableWorkbook> {
    private String fileName;
    private UserTransactions userTransactions;
    private int amountColumn;

    private WritableWorkbookBuilder() {

    }

    public static WritableWorkbookBuilder writableWorkbookBuilder() {
	return new WritableWorkbookBuilder();
    }

    public WritableWorkbookBuilder withFileName(String fileName) {
	this.fileName = fileName;
	return this;
    }

    public WritableWorkbookBuilder withUserTransactions(UserTransactions userTransactions) {
	this.userTransactions = userTransactions;
	return this;
    }

    public WritableWorkbookBuilder withAmountColumn(int amountColumn) {
	this.amountColumn = amountColumn;
	return this;
    }

    public WritableWorkbook build() throws Exception {
	if (userTransactions.getMonthlyTransactionsCollection().isEmpty())
	    return null;

	WritableWorkbook writableWorkbook = Workbook.createWorkbook(new File(this.fileName));
	int sheetIndex = 0;
	for (MonthlyTransactions monthlyTransactions : userTransactions.getMonthlyTransactionsCollection()) {
	    WritableSheetBuilder.writableSheetBuilder().withWritableWorkbook(writableWorkbook).withMonthlyTransactions(
		    monthlyTransactions).withAmountColumn(amountColumn).withSheetIndex(sheetIndex++).build();
	}

	writableWorkbook.write();
	writableWorkbook.close();

	return writableWorkbook;
    }
}
