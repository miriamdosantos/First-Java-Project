/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1programing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author miriam
 */
public class CA1programing {

    //CA1 - File Access & String Manipulation
// CCT 2020
    public static void main(String[] args) {
        

// Instantiate variables to default values

		// Instantiate Objects to default values
		Scanner ObjScanner = null;
		BufferedWriter objWriter = null;

		// Instantiate String to default values
		String firstName = null;
		String firstInitial = null;
		String surName = null;
		String title = null;
		String status = null;
		String fullName = null;
		String genderThirdLine = null;

		// Instantiate Primitives variables to default values
		int age = 0;
		int count = 0;

		// Star try-catch Exception
		try {

			// Instantiate a BufferedReader object
			// objReader = new BufferedReader(new FileReader("...PATH.../people.txt"));
			
			// Using Scanner Object
			ObjScanner  = new Scanner(new File("people.txt"));
			// Instantiate a BufferedWriter
			// This file will create at the path of this java project
			objWriter = new BufferedWriter(new FileWriter("status.txt"));
			// Scanner will start as long as it is a line to read
			while (ObjScanner.hasNextLine()) {
				try {
					// Start loop every tree lines
					// Loop start with fullName while the line is not null
					// first Line
					fullName = ObjScanner.nextLine();
					// validating fullName with regex nl
					// One o more letters or number : \\w+
					// One o more spaces and then one or more letters or numbers : ( +\\w+)*$
					if(!fullName.matches("^\\w+( +\\w+)*$")) {
						
						// in the case of validation failed
						System.err.println("ERROR, It must containe at least one space between firstname and surname");
						System.exit(0);
					} 
					// validating fullName with regex
					// One or more upper case or lower case letter: [A-Z][a-z]+
					// One space or more: [^\s]([ ]{1,})
					else if(fullName.matches("^[A-Z][a-z]+[^\\s]( [ ]{1,})[A-Z][a-z]+$")){
						// in the case of validation failed
						System.err.println("ERROR, It must containe firstname and surname");
						System.exit(0);
					} 
					else {
					
						// Correct validation
						// Manipulating of String
						// firt setp: Store in a int data type the number where the space is located.
						int indexSpace = fullName.indexOf(" ");
						// extract with substring the firstName
						firstName = fullName.substring(0, indexSpace);
						// extract the firstInitial from firstName
						firstInitial = firstName.substring(0, 1);
						System.out.println("firstname is: " + firstName);
						// extract with substring the surName
						surName = fullName.substring(indexSpace+1);
						System.out.println("surname  is: " + surName);
					}

				} catch (Exception e) {
					System.err.println("Error ,fullname must NOT be empty" + e);
				}

				try {
					// second Line
					age = Integer.parseInt(ObjScanner.nextLine());

					// validate that age is between 0 and 100
					if (age >= 0 && age <= 100) {
						System.out.println("age is: " + age);
						// if age is less 18
						if (age <= 18) {
							status = "School";
						}
						// if age is between 18 and 25
						if (age > 18 && age <= 25) {
							status = "College";
						}
						// if age is between 26 and 66
						if (age > 26 && age <= 66) {
							status = "Worker";
						}
						// if age is more than 66
						if (age > 66) {
							status = "Retired";
						}
						// In the case that age is not between 0 and 100
					} else {
						System.err.println("ERROR, it must have an age between 0 and 100");
						System.exit(0);
					}
				} catch (Exception e) {
					System.err.println("Error, age must be a number and must NOT be empty, Description: " + e);
					System.exit(0);
				}

				// Third Line
				try {
					genderThirdLine = ObjScanner.nextLine();
					// String match a regex one letter M or F or T
					if (genderThirdLine.matches("[MFT]")) {
						// Assignment the first position to a char variable called gender
						char gender = genderThirdLine.charAt(0);
						// Evaluate with switch the char gender
						switch (gender) {
						case 'M': {
							title = "Mr.";
							break;
						}
						case 'F': {
							title = "Ms.";
							break;
						}
						case 'T': {
							title = "Mx.";
							break;
						}
						}
						// Otherwise print an error
					} else {
						System.err.println("Error, it must be a letter that represent a gender: M or F or T ");
						System.exit(0);
					}
				} catch (Exception e) {
					System.err.println("Error, gender must be a letter and must NOT be empty, Description: " + e);
					System.exit(0);
				}

				// Writes the string into the file status
				System.out.println("Writing this Person into a file: status.txt\n");
				objWriter.write(title + surName + ", " + firstInitial + "\n" + status + "\n");

				// count how many people are processed
				count++;
			}

			// Closes the writer
			objWriter.close();

			// Catch Exception
		} catch (Exception e) {
			System.err.println("Error, the file must be exist and must NOT be empty" + e);
		} // Finish try-catch Exception

		System.out.println("\nTotal people processed: " + count + " people" + "\nSUCCESSFUL");
    }
}

