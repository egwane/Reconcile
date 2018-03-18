/**
 * 
 */
package com.egwane.finances.files;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.egwane.finances.domain.IReconcileTransaction;
import com.egwane.finances.domain.builders.IReconcileTransactionBuilder;
import com.external.tools.StopWatch;

/**
 * @author Gwénaël
 * 
 */
public class CsvFile {

    static final Logger logger = Logger.getLogger(CsvFile.class);

    private String file;

    /**
     * Default constructor
     * 
     * @param file
     */
    public CsvFile(String file) {
	this.file = file;
    }

    /**
     * 
     * @return List of IReconcileTransaction objects
     * @throws FileNotFoundException
     * @throws IOException
     */
    public List<IReconcileTransaction> parseCsvFile() throws FileNotFoundException, IOException {
	logger.info("Begin parseCsvFile()");
	StopWatch s = new StopWatch();
	s.start();

	TextFileScanner scanner = createScanner();
	List<IReconcileTransaction> iReconcileTransactions = new ArrayList<IReconcileTransaction>();
	scanner.nextLine(); // Skip the header
	while (scanner.hasNextLine()) {
	    String csvLine = removeUnusedCharacters(scanner.nextLine());
	    if (csvLine.isEmpty())
		continue;
	    // logger.debug("line = [" + line + "]");
	    IReconcileTransaction iReconcileTransaction = buildIReconcileTransaction(csvLine);
	    if (null != iReconcileTransaction)
		iReconcileTransactions.add(iReconcileTransaction);
	}
	scanner.close();

	s.stop();
	logger.info("End parseCsvFile() - elapsed time : " + s.getElapsedTime() + " milliseconds.");
	return iReconcileTransactions;
    }

    /**
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    protected TextFileScanner createScanner() throws FileNotFoundException {
	return new TextFileScanner(this.file);
    }

    /**
     * @param csvLine
     * @return
     */
    private static IReconcileTransaction buildIReconcileTransaction(String csvLine) {
	return IReconcileTransactionBuilder.iReconcileTransactionBuilder().withCSV(csvLine).build();
    }

    private String removeUnusedCharacters(String string) {
	StringBuilder sb = new StringBuilder();
	for (final char character : string.toCharArray()) {
	    if (character != 0 && character != '\"') {
		sb.append(character);
	    }
	}
	return sb.toString();
    }
}
