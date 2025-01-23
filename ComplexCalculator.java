/*
 * Complex Calculator Tool.
 * @Alvin Karanja
 */
public class ComplexCalculator{
    /*
     * No need for constructor as all class
     * methods are static.
     */
    public static ComplexNumber add(ComplexNumber...args){
        
        double realResult = 0;
        double imaginaryResult = 0;

        //Loop through arguments 
        for(int i = 0; i < args.length; i++){
            realResult += args[i].getReal();
            imaginaryResult += args[i].getComplex();
        }

        return new ComplexNumber(realResult, imaginaryResult);
    }

    public static ComplexNumber subtract(ComplexNumber...args){

        double realResult = 0;
        double imaginaryResult = 0;

        //Loop through arguments 
        for(int i = 0; i < args.length; i++){
            realResult -= args[i].getReal();
            imaginaryResult -= args[i].getComplex();
        }

        return new ComplexNumber(realResult, imaginaryResult);
    }
    /*
     * Uses Euler form 
     */
    public static ComplexNumber multiply(ComplexNumber...args){

        double realResult = 0;
        double imaginaryResult = 0;

        double eulerR = 1;
        double eulerTheta = 0;
        
        //Loop through arguments
        for(int i = 0; i < args.length; i++){
            eulerR *= args[i].getModulus();
            eulerTheta += args[i].getArgument();
        }

        realResult = eulerR * Math.cos(eulerTheta);
        imaginaryResult = eulerR * Math.sin(eulerTheta);
        
        return new ComplexNumber(realResult, imaginaryResult);

    }
    /*
     * Uses Euler form 
     */
    public static ComplexNumber divide(ComplexNumber...args){

        double realResult = 0;
        double imaginaryResult = 0;

        double eulerR = args[0].getModulus();
        double eulerTheta = args[0].getArgument();
        
        //Loop through arguments
        for(int i = 1; i < args.length; i++){
            eulerTheta -= args[i].getArgument();
        }

        for(int i = 1; i < args.length; i++){
            eulerR /= args[i].getModulus();
        }

        realResult = eulerR * Math.cos(eulerTheta);
        imaginaryResult = eulerR * Math.sin(eulerTheta);
        
        return new ComplexNumber(realResult, imaginaryResult);

    }
    /*
     * Uses Euler form
     */
    public static ComplexNumber pow(ComplexNumber number, int power){

        double realResult = 0;
        double imaginaryResult = 0;

        realResult = Math.pow(number.getModulus(), power) * 
                     Math.cos(number.getArgument() * power);

        imaginaryResult = Math.pow(number.getModulus(), power) * 
                          Math.sin(number.getArgument() * power);

        return new ComplexNumber(realResult, imaginaryResult);

    }
    /*
     * Uses Euler form
     */
    public static ComplexNumber[] root(ComplexNumber number, int root){

        ComplexNumber[] roots = new ComplexNumber[root];

        double eulerR = Math.pow(number.getModulus(), (1.0/root));
        double angle; 

        for(int k = 0; k < root; k++){
            angle = (number.getArgument() + (k * (2*Math.PI)))/root;

            roots[k] = new ComplexNumber(eulerR * Math.cos(angle), 
                                         eulerR * Math.sin(angle));
        }

        return roots;
    }
    /*
     * Uses Euler form
     */
    public static ComplexNumber[] sqrt(ComplexNumber number){
        return root(number, 2);
    }

    public static ComplexNumber rationalize(ComplexNumber numerator, ComplexNumber denominator){

        ComplexNumber solution = divide(multiply(numerator, denominator.getConjugate()),
                                 multiply(denominator, denominator.getConjugate()));
        return solution;
    }

}