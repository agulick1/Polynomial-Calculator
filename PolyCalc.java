import java.util.*;
/**
 * Write a description of class PolyCalc here.
 *
 * @author Adam Gulick
 * @version 1.0
 */
public class PolyCalc
{
    public static void main (String[] args){
        System.out.println("/*** Welcome to Adam Gulick's polynomial calculator! ***/");
        System.out.println("Instructions: ");
        System.out.println(" - Polynomials are specified using space-separated pairs of coefficient and exponent.");
        System.out.println("E.g., \"2.5x^2 - 1\" should be input as \"2.5 2 -1 0\"");
        System.out.println("- \"quit\" can be used at any time to exit the program.");

        System.out.println();
        System.out.println("--------Computation #1--------");

        Scanner keybScanner = new Scanner(System.in); // scanner for System.in input
        int step = 1;
        Poly p1 = new Poly (0, 0);
        Poly p2 = new Poly (0, 0);
        String operation = "";
        int computationTracker = 1;

        for(step = 0; step < 10; step++){
            if(step == 1){
                System.out.println("Please enter your first Polynomial");
                p1 = new Poly (0, 0);
                String line = keybScanner.nextLine(); // gets a line of input, e.g., "2.3 4"
                if(line.toLowerCase().equals("quit")){
                    System.out.println("Thank you and have a nice day!");
                    System.exit(0);
                }
                Scanner lineScanner = new Scanner(line); // scanner to scan inside line

                while(lineScanner.hasNext()){
                    try{
                        double nextCoef = lineScanner.nextDouble(); // gets next coefficient 2.3 
                        int nextExp = lineScanner.nextInt(); // gets next exp
                        p1 = p1.add(new Poly(nextCoef, nextExp));//adds next Poly to p1
                    } 
                    catch(Exception err){
                        System.out.println("Please enter a valid Polynomial");
                        step--;
                        p1 = null;
                        break;
                    }
                }
                if (p1 != null)
                    System.out.println("You entered: " + p1);
            }
            if(step == 2){
                System.out.println("Please enter your operation (+, -, *, /):");

                String line = keybScanner.nextLine(); // gets a line of input, e.g., "2.3 4"
                if(line.toLowerCase().equals("quit")){
                    System.out.println("Thank you and have a nice day!");
                    System.exit(0);
                }
                Scanner lineScanner = new Scanner(line); // scanner to scan inside line
                if(line.equals("+"))
                    operation = "plus";
                else if(line.equals("-"))
                    operation = "minus";
                else if(line.equals("*"))
                    operation = "times";
                else if(line.equals("/"))
                    operation = "division";
                else{
                    System.out.println("Please enter a valid operation");
                    step--;
                }
            }
            if(step == 3){
                System.out.println("Please enter your second Polynomial");
                p2 = new Poly (0, 0);
                String line = keybScanner.nextLine(); // gets a line of input, e.g., "2.3 4"
                if(line.toLowerCase().equals("quit")){
                    System.out.println("Thank you and have a nice day!");
                    System.exit(0);
                }
                Scanner lineScanner = new Scanner(line); // scanner to scan inside line

                while(lineScanner.hasNext()){
                    try{
                        double nextCoef = lineScanner.nextDouble(); // gets next coefficient 2.3 
                        int nextExp = lineScanner.nextInt(); // gets next exp
                        p2 = p2.add(new Poly(nextCoef, nextExp));//adds next Poly to p1
                    } 
                    catch(Exception err){
                        System.out.println("Please enter a valid Polynomial");
                        step--;
                        p2 = null;
                        break;
                    }
                }
                if (p2 != null)
                    System.out.println("You entered: " + p2);
            }
            if(step == 4){
                System.out.println();
                if(operation.equals("plus"))
                    System.out.println(p1 + " + " + p2 + " = " + p1.add(p2));
                if(operation.equals("minus"))
                    System.out.println(p1 + " - " + p2 + " = " + p1.subtract(p2));
                if(operation.equals("times"))
                    System.out.println(p1 + " * " + p2 + " = " + p1.multiply(p2));
                if(operation.equals("division"))
                    if(p1.divide(p2) == null)
                        System.out.println(p1 + " / " + p2 + " = indivisible");
                    else
                        System.out.println(p1 + " / " + p2 + " = " + p1.divide(p2));
                System.out.println();
                computationTracker++;
                System.out.println("--------Computation #" + computationTracker + "--------");
                step = 0;
            }
        }
    }
}
