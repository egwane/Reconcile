package com.egwane.finances.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TextFileScanner {
    private Scanner scanner;

    public TextFileScanner(String file) throws FileNotFoundException {
	this.scanner = new Scanner(new File(file), "UTF-16");
	scanner.useDelimiter(Pattern.compile(System.getProperty("line.separator")));
    }

    public String nextLine() {
	return scanner.nextLine();
    }

    public boolean hasNextLine() {
	return scanner.hasNextLine();
    }

    public void close() {
	scanner.close();
    }
}
