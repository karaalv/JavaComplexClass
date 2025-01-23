/*
 * Complex Number Solver
 * @Alvin Karanja
 */
import java.util.Scanner;
import java.util.regex.*;
import java.util.ArrayList;

public class ComplexSolver{
    /*
     * Global Variables
     */

    /*
     * Main method for running solver.
     */
    public static void main(String[] args){
        /*
         * Continuously take input
         * until user quits.
         */
        while(true){
            // Define input for complex number.
            String complexNumberString = userInput();

            if(isValid(complexNumberString)){
                System.out.println("Valid");
                ComplexNumber complexNumber = generateComplexNumber(complexNumberString);
                System.out.println(complexNumber.completeString());
            } else {
                System.out.println( "\u001B[31m" +"ERROR: Invalid Input"+
                                    "\u001B[0m");
            }
        }
        
    }
    /*
     * Receive user input.
     */
    private static String userInput(){
        // Receive string input.
        System.out.print("Enter: ");
        Scanner input = new Scanner(System.in);
        String stringInput = input.nextLine();
        // Terminate program.
        if(stringInput.equals("quit()")){
            System.out.println( "\u001B[35m" +"Exiting Program..."+
                                "\u001B[0m");
            input.close();
            System.exit(0);
        }
        // Return input.
        return stringInput;
    }
    /*
     * Checks if input contains 
     * valid characters.
     */
    private static boolean isValid(String userInput){
        String nospace = userInput.replaceAll("\\s+", "");
        Pattern pattern = Pattern.compile("[^ij0-9^+-.]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(nospace);

        if(matcher.find()){
            return false;
        } else {
            return true;
        }
    }
    /*
     * Generate complex number from 
     * user input.
     * 
     * If the user defines multiple
     * addition/subtraction terms 
     * they will be simplified.
     */
    private static ComplexNumber generateComplexNumber(String userInput){
        /*
         * Use ArrayLists to store Separated 
         * Positive and negative terms.
         * 
         * Concatenate result, print complex 
         * number.
         */
        ArrayList<String> separatedTerms = new ArrayList<>();
        ArrayList<String> positiveTerms = new ArrayList<>();
        ArrayList<String> negativeTerms = new ArrayList<>();

        int negativeCounter = 0;
        int termIndex = 0;
        int characterIndex = 0;

        // Remove whitespace from user input. 
        String noWhiteSpace = userInput.replaceAll("\\s+", "");
        // Separate terms from string. 
        String [] terms = noWhiteSpace.split("[+-]");
        for(String i : terms){
            if(i != ""){
                separatedTerms.add(i);
            }
        }

        // Error handle 
        if(separatedTerms.isEmpty()){
            System.out.println( "\u001B[31m" +"Input Anomaly: No Arguments"+
                                "\u001B[0m");
            return null;
        } else {
            // If terms are separated, commence organization

            /*
             * Edge case:
             * In the event the first term has
             * no symbol, it is positive.
             */
            if( Character.compare(noWhiteSpace.charAt(characterIndex), '+') != 0 &&
                Character.compare(noWhiteSpace.charAt(characterIndex), '-') != 0){
                    positiveTerms.add(separatedTerms.get(termIndex));
                    termIndex++;                
            }

            /*
             * Iterate through all of the
             * characters in the users string,
             * use the detection of +/- to 
             * determine if current term in
             * termIndex is positive or negative.
             * 
             * Use negativeCounter for negative 
             * symbols becoming positive (--)
             * -> (+).
             * 
             * Add term to respective array when
             * next character is not +/-.
             * 
             * Use catch to ignore trailing symbols
             * (a-b-).
             */
            while(characterIndex < noWhiteSpace.length()){

                if( Character.compare(noWhiteSpace.charAt(characterIndex), '+') == 0 ||
                    Character.compare(noWhiteSpace.charAt(characterIndex), '-') == 0){

                    // Check if negative.
                    if(Character.compare(noWhiteSpace.charAt(characterIndex), '-') == 0){
                        negativeCounter++;

                        // Switch to positive.
                        if(negativeCounter > 1){
                            negativeCounter = 0;
                        }
                    }

                    // Catch statement.
                    try{

                        // If next character not symbol, 'current' term is ...
                        if( Character.compare(noWhiteSpace.charAt(characterIndex + 1), '+') != 0 &&
                            Character.compare(noWhiteSpace.charAt(characterIndex + 1), '-') != 0){

                            if(negativeCounter == 0){
                                positiveTerms.add(separatedTerms.get(termIndex));
                                termIndex++;
                            } else {
                                negativeTerms.add(separatedTerms.get(termIndex));
                                termIndex++;
                                negativeCounter = 0;
                            }

                        }

                    } catch (StringIndexOutOfBoundsException s){
                        System.out.println("\u001B[33m" +"Input Anomaly: Trailing characters ignored"+ 
                                            "\u001B[0m");
                    }

                }

                characterIndex++;
            }

        }

        /*
         * At this point the ArrayLists have 
         * positive and negative terms respectfully.
         * 
         * Separate the terms into Real and Imaginary 
         * ArrayLists that are positive and negative.
         */
        ArrayList<String> positiveRealTerms = new ArrayList<>();
        ArrayList<String> negativeRealTerms = new ArrayList<>();
        //
        ArrayList<Double> positiveRealTermsD = new ArrayList<>();
        ArrayList<Double> negativeRealTermsD = new ArrayList<>();

        ArrayList<String> positiveImaginaryTerms = new ArrayList<>();
        ArrayList<String> negativeImaginaryTerms = new ArrayList<>();
        //
        ArrayList<Double> positiveImaginaryTermsD = new ArrayList<>();
        ArrayList<Double> negativeImaginaryTermsD = new ArrayList<>();


        // For positive terms.
        if(positiveTerms.isEmpty()){
            positiveImaginaryTerms.add("0i");
            positiveRealTerms.add("0");
        } else {
            for(String i : positiveTerms){
                if( i.contains("i") || i.contains("I") ||
                    i.contains("j") || i.contains("J")){

                    // Edge case: 'i'
                    if( i.compareTo("i") == 0 || i.compareTo("I") == 0 ||
                        i.compareTo("j") == 0 || i.compareTo("J") == 0){
                        positiveImaginaryTerms.add("1");
                    } else {
                        positiveImaginaryTerms.add(i);
                    }
                } else {
                    positiveRealTerms.add(i);
                }
            }
        }

        // For negative terms.
        if(negativeTerms.isEmpty()){
            negativeImaginaryTerms.add("0i");
            negativeRealTerms.add("0");
        } else {
            for(String i : negativeTerms){
                if( i.contains("i") || i.contains("I") ||
                    i.contains("j") || i.contains("J")){
                    
                    // Edge case: 'i'
                    if( i.compareTo("i") == 0 || i.compareTo("I") == 0 ||
                        i.compareTo("j") == 0 || i.compareTo("J") == 0){
                        negativeImaginaryTerms.add("1");
                    } else {
                        negativeImaginaryTerms.add(i);
                    }
                } else {
                    negativeRealTerms.add(i);
                }
            }
        }
        
        /*
         * Strip letters from imaginary terms.
         * Add  number to respective ArrayList.
         */
        for(String i : positiveImaginaryTerms){
            String number = i.replaceAll("[iIjJ]", "");
            Double numberD = Double.parseDouble(number);
            positiveImaginaryTermsD.add(numberD);
        }
        //
        for(String i : negativeImaginaryTerms){
            String number = i.replaceAll("[iIjJ]", "");
            Double numberD = -1 * Double.parseDouble(number);
            negativeImaginaryTermsD.add(numberD);
        }
        // Real numbers.
        for(String i : positiveRealTerms){
            Double numberD = Double.parseDouble(i);
            positiveRealTermsD.add(numberD);
        }
        //
        for(String i : negativeRealTerms){
            Double numberD = -1 * Double.parseDouble(i);
            negativeRealTermsD.add(numberD);
        }

        /*
         * Sum results for Real and Imaginary 
         * parts, return complex number.
         */
        Double realComponent = 0.0;
        Double imaginaryComponent = 0.0;

        for(Double i : positiveImaginaryTermsD){
            imaginaryComponent += i;
        }
        //
        for(Double i : negativeImaginaryTermsD){
            imaginaryComponent += i;
        }

        for(Double i : positiveRealTermsD){
            realComponent += i;
        }
        //
        for(Double i : negativeRealTermsD){
            realComponent += i;
        }

        // Clear out ArrayLists 
        separatedTerms.clear();
        positiveTerms.clear();
        negativeTerms.clear();

        positiveRealTerms.clear();
        negativeRealTerms.clear();
        positiveRealTermsD.clear();
        negativeRealTermsD.clear();

        positiveImaginaryTerms.clear();
        negativeImaginaryTerms.clear();
        positiveImaginaryTermsD.clear();
        negativeImaginaryTermsD.clear();

        return new ComplexNumber(realComponent, imaginaryComponent);

    }


}