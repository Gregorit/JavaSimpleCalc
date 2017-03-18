package packCalc;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

/** Calculator
 * @ --- Calculator for project on studies in Java ---
 * Compiled in Eclipse Neon.2 Release (4.6.2)
 * @author Grzegorz Urych
 * @version 1.0 (18.03.2017)
 */


public class calc
{
	/** Function calculates the result of the numbers and operators through the string input.
	 * @param inputString entrance string
	 */
	public static void calculations (String inputString)
	{
		if (StringUtils.isNumeric(inputString.substring(0, 1)) == true)
		{
			System.out.println("** Pierwszy znak jest cyfra. Obliczam. **");
			String[] operators = inputString.split("[0-9]{1,}");
			String[] numbers = inputString.split("[+-//*/]");
			
			List<String> operatorsList = new ArrayList<String>();
			List<Double> numberList = new ArrayList<>();
			
			int i = 0;
			while(i < numbers.length)
			{
				if (numbers[i].trim().isEmpty() == false)
				{
					numberList.add(Double.parseDouble(numbers[i].trim()));
				}
				i++;
			}
			i = 0;
			while (i < operators.length)
			{
				if (operators[i].trim().isEmpty() == false)
				{
					operatorsList.add(operators[i].trim());
				}
				i++;
			}
			
			i = 0;
			while (i < operatorsList.size())
			{
				if (operatorsList.get(i).equals("*") == true)
				{
					numberList.set(i, numberList.get(i) * numberList.get(i+1));
					numberList.remove(i+1);
					operatorsList.remove(i);
					i--;
				}
				else if (operatorsList.get(i).equals("/") == true)
				{
					numberList.set(i, numberList.get(i) / numberList.get(i+1));
					numberList.remove(i+1);
					operatorsList.remove(i);
					i--;
				}
				i++;
			}
			i = 0;
			while (i < operatorsList.size())
			{
				if (operatorsList.get(i).equals("+") == true)
				{
					numberList.set(i, numberList.get(i) + numberList.get(i+1));
					numberList.remove(i+1);
					operatorsList.remove(i);
					i--;
				}
				else if (operatorsList.get(i).equals("-") == true)
				{
					numberList.set(i, numberList.get(i) - numberList.get(i+1));
					numberList.remove(i+1);
					operatorsList.remove(i);
					i--;
				}
				i++;
			}
			
			double result = numberList.get(0);
			System.out.print("Wynik: ");
			System.out.println(inputString+" = "+result);
		}
		else
		{
			System.out.println("** Pierwszy znak nie jest cyfra. Ignoruje. **");	
		}
}
	
	
	/** Function manages the operation of entire program. It allows to choose the option for calculations or ending the program.
	 * @param args startup parameters
	 */
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		int choice = 99;
		while (choice != 0)
		{
			System.out.println("------------------------------");
			System.out.println("-- [Wybierz tryb obliczen:] --");
			System.out.println("------------------------------");
			System.out.println("--(1)Obliczenia ze stringa, --");
			System.out.println("--(2)Obliczenia z pliku,    --");
			System.out.println("------------------------------");
			System.out.println("--(0)Wyjscie z programu.    --");
			System.out.println("------------------------------");
			System.out.print("Wybierasz: ");
			choice = keyboard.nextInt();
			
			if (choice == 1) //obliczenia ze stringa
			{
				boolean chkEnd = false;
				while (chkEnd != true)
				{
					System.out.println("[Wpisanie \"koniec\" cofa do menu.");
					System.out.print("Wpisz dane do obliczen: ");
					String text = keyboard.next();
					if (text.equalsIgnoreCase("koniec"))
					{
						System.out.println("** Cofam do menu. **");
						chkEnd = true;
					}
					else
					{
						calculations(text);
					}
				}
			continue;
			}
			
			else if (choice == 2) //czytanie z pliku
			{
				boolean chkEnd = false;
				while (chkEnd != true)
				{
					System.out.println("Podaj nazwe pliku (z rozszerzeniem): ");
					String text = keyboard.next();
					BufferedReader file = null;
					try
					{            
						file = new BufferedReader(new FileReader(text));
						String line = file.readLine();
						int lineNumber = 0;
						while (line != null)
						{
							System.out.printf(">> Linia numer %d: ", lineNumber);
							System.out.println(line);
							calculations(line);
	                	line = file.readLine();
	                	lineNumber++;
						}
					}
					catch (IOException error)
					{
						System.out.println("Wyst¹pi³ b³¹d.");
					}
					int exitChoice = 99;
					System.out.print("Wyjsc do menu? ((1)Tak, (0)Nie): ");
					exitChoice = keyboard.nextInt();
					if (exitChoice == 1)
						chkEnd = true;
				}
				continue;
			}
		}
		keyboard.close();
		System.out.println("Program konczy dzialanie.");
	}
}