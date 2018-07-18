// Name: Derek Darko
// Email: ddarko365@gmail.com
// Date: 2 Feb 2017

import java.io.*;
import java.util.*;
public class PersonalityTest{
	public static void main(String[] args) throws FileNotFoundException{
		Scanner console = new Scanner(System.in);
		System.out.print("Input file name: ");
		//Gets the personality file from user 
		String fileName = console.nextLine();
		File file = new File(fileName);
		//Will not be able to continue until user names a file that exists 
		while(!file.exists()) {
			System.out.print("File not found. Try again: ");
			fileName = console.nextLine();
			file = new File(fileName);
		}
		System.out.print("Output file name: ");
		//Creates output file for name, count of As and Bs for each dimension, 
		//% Bs for each dimension, and personality type
		String out = console.next();
		File answers = new File(out);
		Scanner ptest = new Scanner(file);
		//Using PrintStream to write answers to output files
		PrintStream output = new PrintStream(answers);
		while (ptest.hasNextLine()) {
			String line = findAnswers(console, ptest, output);
			//Created two int arrays for a percentage of A answers and B answers
			int[] estj = new int[4];
			int[] infp = new int[4];
			//Created a total count for a maximum of 70 answers, as well as individual counts for A and B
			int[] totcount = new int[4];
			int[] aCount = new int[4];
			int[] bCount = new int[4];
			//Created to string arrays to convert the int values into either a ratio of A's to B's or with a percent symbol(%)
			String[] aTOb = new String[4];
			String[] percentage = new String[4];
			//Created four char variables to accept either case 
			char upperA = 'A';
			char lowerA = 'a';
			char upperB = 'B';
			char lowerB = 'b';
			points(line, estj, upperA, lowerA, aCount);
			points(line, infp, upperB, lowerB, bCount);
			totalB(infp, estj, totcount, percentage);
			ratio(aCount, bCount, aTOb);
			//Called personality method with string variable to make it easier personality type
			String personality = personality(totcount);
			//Converted arrays to strings so results can be seen in console
			output.println(Arrays.toString(aTOb));
			output.print(Arrays.toString(percentage) + " = " + personality); //prints results
			output.println();
		}
		System.out.println();
		//Created another Scanner object to write each line of output file
		Scanner scores = new Scanner(answers);
		while (scores.hasNext()) {
			String newline = scores.nextLine();
			System.out.println(newline);
		}
	}
	//Created findAnswers method to diiferentiate the name from the line of answers
	public static String findAnswers(Scanner console, Scanner ptest, PrintStream output) {
		String name = ptest.nextLine();
		output.println(name + ": ");
		String line = ptest.nextLine();
		return line;
	}
	//Created a points method to count each answers that matters for each side of a personality section
	public static void points(String line, int[] personality, char upper, char lower, int[] letterCount) {
		for (int i = 0; i < 7; i++) {
  			for (int j = 0; j < 10; j++) {
  				//Differentiated which points get allocated with a modulo 7
				if (line.charAt(10*i + j) == upper || line.charAt(10*i + j)  == lower) {
 					if ((10*i + j) % 7 == 0) {
 						personality[0]++;
					} else if ((10*i + j) % 7 == 1 || (10*i + j) % 7 == 2) {
						personality[1]++;
					} else if ((10*i + j) % 7 == 3 || (10*i + j) % 7 == 4) {
						personality[2]++;
					} else if ((10*i + j) % 7 == 5 || (10*i + j) % 7 == 6) {
						personality[3]++;
					}
				}
			}
		}
		//Creates a copy of the number of A or B to create a ratio of answers
		for (int i = 0; i < 4; i++) {
			letterCount[i] = personality[i];
		}
	}
	public static void totalB(int[] infp, int[] estj, int[] count, String[] percentage) {
		//Create a percentage of B's by dividing the number of B in each category by the 
		//total number of answers in each category
		for (int i = 0; i < 4; i++) {
			double math = (infp[i] * 100.0 / (infp[i] + estj[i]));
			int percent = (int) Math.round(math);
			count[i] = percent;
			percentage[i] = count[i] + "%";
		}
	}
	public static String personality(int[] count) {
		//Created two strings to choose a letter representing each category, as well as X if there is a 50% ratio 
		String option1 = "ESTJ";
		String option2 = "INFP";
		String nature = "";
		for (int i = 0; i < 4; i++) {
			if (count[i] > 50) {
				nature = nature + option2.charAt(i);
			} else if (count[i] < 50) {
				nature = nature + option1.charAt(i);
			} else nature = nature + 'X';
 		}
		return nature;
	}
	public static void ratio(int[] a, int[] b, String[] ratio) {
		//Created a separate method to create a variable showing number of A's and B's chosen
		for (int i = 0; i < 4; i++) {
			ratio[i] = a[i] + "A-" + b[i] + "B";
		}
	}
}