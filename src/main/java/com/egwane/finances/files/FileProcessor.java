package com.egwane.finances.files;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.egwane.context.AppContext;
import com.egwane.finances.domain.IReconcileTransaction;
import com.egwane.finances.domain.UserInformation;
import com.egwane.finances.domain.UserTransactions;
import com.egwane.finances.domain.builders.UserTransactionsBuilder;
import com.egwane.finances.domain.builders.WritableWorkbookBuilder;
import com.external.tools.StopWatch;

/**
 * Main program
 * 
 */
public class FileProcessor {
    private static final String APP_NAME = "Reconcile";
    private static final Logger logger = Logger.getLogger(FileProcessor.class);
    private static final String IRECONCILE_FILE_EXTENSION = ".csv";

    private static enum ReturnCode {
	SUCESS, ERROR
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
	ReturnCode returnCode = ReturnCode.SUCESS;
	String errorMessage = new String();

	logger.info("Begin main()");
	StopWatch s = new StopWatch();
	s.start();

	setupSpringContext();
	ApplicationContext context = AppContext.getApplicationContext();
	logger.debug("After Spring setup - elapsed time : " + s.getElapsedTime() + " milliseconds.");

	try {
	    ArrayList<UserInformation> usersInformation = (ArrayList<UserInformation>) context
		    .getBean("usersInformation");
	    for (Object userInformation : usersInformation) {
		processInputDirectory((UserInformation) userInformation);
	    }
	} catch (Exception e) {
	    logger.error("Error occured", e);
	    returnCode = ReturnCode.ERROR;
	    errorMessage = e.getMessage();
	} finally {
	    s.stop();
	    logger.info("End main() - elapsed time : " + s.getElapsedTime() + " milliseconds.");

	    displayResultMessage(returnCode, errorMessage);
	}
    }

    /**
     * 
     */
    private static void setupSpringContext() {
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
	AppContext.setApplicationContext(applicationContext);
    }

    private static void processInputDirectory(UserInformation userInformation) throws Exception {
	logger.debug("Begin processInputDirectory() for " + userInformation.getUserName());

	List<IReconcileTransaction> iReconcileTransactions = new ArrayList<IReconcileTransaction>();
	File folder = new File(userInformation.getInputPath());
	File[] filesToProcess = folder.listFiles(buildFileFilter());

	if (null == filesToProcess || filesToProcess.length == 0) {
	    logger.info("No file to process in directory for " + userInformation.getUserName());
	    return;
	}

	logger.info("Processing " + filesToProcess.length + " file(s) for " + userInformation.getUserName());
	for (File file : filesToProcess) {
	    iReconcileTransactions.addAll(processInputFile(file.getAbsoluteFile()));
	}

	logger.info("Number of iReconcileTransactions imported : " + iReconcileTransactions.size());

	UserTransactions userTransactions = UserTransactionsBuilder.userTransactionsBuilder().withUserName(
		userInformation.getUserName()).withIReconcileTransactions(iReconcileTransactions).build();

	WritableWorkbookBuilder.writableWorkbookBuilder().withFileName(userInformation.getOutputFile())
		.withAmountColumn(userInformation.getAmountColumn()).withUserTransactions(userTransactions).build();

	moveToArchive(filesToProcess, userInformation.getArchivesPath());
	logger.debug("End processInputDirectory() for " + userInformation.getUserName());
    }

    /**
     * @return
     */
    private static FileFilter buildFileFilter() {
	FileFilter filter = new FileFilter() {
	    public boolean accept(File file) {
		return file.isFile() && file.getName().endsWith(IRECONCILE_FILE_EXTENSION);
	    }
	};
	return filter;
    }

    private static List<IReconcileTransaction> processInputFile(File absoluteFile) throws FileNotFoundException,
	    IOException {
	logger.debug("Begin processInputFile() : " + absoluteFile.getName());

	CsvFile csvFile = new CsvFile(absoluteFile.getPath());
	List<IReconcileTransaction> iReconcileTransactions = csvFile.parseCsvFile();

	logger.debug("End processInputFile()");
	return iReconcileTransactions;
    }

    /**
     * 
     * @param filesToProcess
     * @param archivesPath
     */
    private static void moveToArchive(File[] filesToProcess, String archivesPath) {
	for (File file : filesToProcess) {
	    File renamedFile = new File(archivesPath + System.getProperty("file.separator") + file.getName());
	    if (renamedFile.exists())
		renamedFile.delete();
	    file.renameTo(renamedFile);
	}
    }

    /**
     * @param returnCode
     * @param errorMessage
     * 
     */
    private static void displayResultMessage(ReturnCode returnCode, String errorMessage) {
	if (returnCode == ReturnCode.SUCESS)
	    JOptionPane.showMessageDialog(null, "Les transactions ont ete converties avec succes.", APP_NAME,
		    JOptionPane.INFORMATION_MESSAGE);
	else
	    JOptionPane.showMessageDialog(null, "Une erreur est survenue: \n " + errorMessage, APP_NAME,
		    JOptionPane.ERROR_MESSAGE);
    }
}
