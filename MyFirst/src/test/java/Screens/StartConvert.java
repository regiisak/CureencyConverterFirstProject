package Screens;

import Currency.Coins;
import Calculations.CoinFactory;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class StartConvert {
    public static ArrayList<Double> results = new ArrayList<>();

    public static void startConvert() {

       int firstScreenOption = firstScreen();
       double secondScreenOption = secondScreen();
       double resultOfCalc;

       System.out.println("Your result is: ");
       if (firstScreenOption == 1)
           resultOfCalc = CoinFactory.getCoinsInstances(Coins.ILS).calculate(secondScreenOption);
       else
           resultOfCalc = CoinFactory.getCoinsInstances(Coins.USD).calculate(secondScreenOption);

       thirdScreen(resultOfCalc, firstScreenOption);
    }

   private static int firstScreen() {
       /*
       * First screen (Welcome screen) - user needs to choose if to convert from USD to ILS or ILS to USD.
       * If user chooses an invalid choice - show error message.
       */

       int option = 1;

       try{
           if (results.size() == 0)
               System.out.println("Welcome to currency converter");
           else
               System.out.println("Currency converter");

           System.out.println("Please choose an option (1/2):");
           System.out.println("1. Dollars to Shekels");
           System.out.println("2. Shekels to Dollars");
           Scanner changeChoice = new Scanner(System.in);
           option = changeChoice.nextInt();

           if(option != 1 && option != 2)
               throw new Exception();

           return option;
       }
       catch (Exception e)
       {
           System.err.println("Invalid Choice, please try again");
           firstScreen();
       }
       return option;
    }

    private static double secondScreen() {
        /*
        * Second screen (Choice screen) - user types an amount to convert.
        */

        double amountToConvert = 0;

        try {
            System.out.println("Please enter an amount to convert");
            Scanner changeChoice2 = new Scanner(System.in);
            amountToConvert = changeChoice2.nextDouble();
            return amountToConvert;
        }
        catch (Exception e) {
            System.err.println("Invalid Choice, please try again");
            secondScreen();
        }
        return amountToConvert;
    }

    private static  void thirdScreen(double result, int option){
        /*
        * Third screen (Result screen):
        * Show user the result saved in all the list and ask user to if to start over (Y/N).
        * Y - Will start cycle again; N - Will show end screen.
        */

        if(option == 1)
           System.out.println(result + " ₪");
        else
            System.out.println(result + " $");

        results.add(result);
        System.out.println("would you like to start over ");
        System.out.println("Y/N");
        Scanner s3 = new Scanner(System.in);
        String startOver = s3.next();

        if (startOver.equalsIgnoreCase("Y"))
            startConvert();
        else {
            System.out.println("THE END");
            System.out.println();
            fourthScreen();
        }
    }

   private static void fourthScreen(){
       /*
        * Fourth Screen (End screen):
        * Show thanks message
        * Print all previous results
        * Write all results to our file
        */


        if(results.size()==1)
            System.out.println("Thanks for using our currency converter");
        else
        { System.out.println("Thanks for using our currency converter");
        System.out.println("Your previous choices are");
        for (int i = 0; i < results.size()-1; i++) {
            System.out.println(results.get(i));
        }
        String filePath = "src/test/results"; // THE FILE WHERE WE WRITE THE RESULTS TO !!!!!!!!!!!!!!!!!!!!!!!!!!
        try {
           BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
           for (double b : results) {
               writer.write(String.valueOf(b));
               writer.write("\n");
           }
           writer.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

}}
