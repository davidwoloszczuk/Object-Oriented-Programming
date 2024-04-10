 /** David Woloszczuk-Mrugala
 *   CPSC 24500
 *   02/11/23
 *   This is my attempt to make a Tax Calculator
 */

package a2;

import java.util.Scanner;
 
 public class Assignment2 {
     public static void main(String[] args) {
         Scanner scan = new Scanner(System.in);
 
         System.out.print("Enter employee name: ");
         String name = scan.nextLine();
 
         System.out.print("Enter income: ");
         double income = scan.nextDouble();
 
         if (income < 0) {
             System.out.println("Invalid input, income should be zero or more");
         } else {
             double incomeTax = calculateIncomeTax(income);
 
             System.out.println("Name: " + name);
             System.out.println("Income: J$" + income);
             System.out.println("Income Tax: J$" + incomeTax);
         }
     }
 
     public static double calculateIncomeTax(double income) {
         double incomeTax = 0;
 
         if (income <= 4000) {
             incomeTax = 0;
         } else if (income <= 5500) {
             incomeTax = (income - 4000) * 0.1;
         } else if (income <= 33500) {
             incomeTax = 1500 * 0.1 + (income - 5500) * 0.2;
         } else {
             incomeTax = 1500 * 0.1 + 28000 * 0.2 + (income - 33500) * 0.4;
         }
 
         return incomeTax;
     }
 }