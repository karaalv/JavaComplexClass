/*
 * Complex Number Data Type
 * @Alvin Karanja
 */
public class ComplexNumber{
    /*
     * Define Real and Imaginary 
     * parts.
     */
    private double real;
    private double imaginary;
    private double modulus;
    private double argument;
    /*
     * Constructor
     */
    public ComplexNumber(double real, double imaginary){

        this.real = real;
        this.imaginary = imaginary;

        // Define Modulus
        this.modulus = Math.sqrt(
            Math.pow(real, 2) + Math.pow(imaginary, 2)
        );

        // Define Argument
        double angle = Math.atan(Math.abs(imaginary/real));
        int func;

        if(real >= 0 && imaginary >= 0){
            func = 0;
        } else if (real < 0 && imaginary > 0){
            func = 1;
        } else if (real > 0 && imaginary < 0){
            func = 2;
        } else {
            func = 3;
        }

        switch(func){
            case 0:     this.argument = angle;
                        break;
            case 1:     this.argument = Math.PI - angle;
                        break;
            case 2:     this.argument = angle * -1;
                        break;
            default:    this.argument = (Math.PI - angle) * -1;
        }
        
    }

    public double getReal(){
        return real;
    }

    public double getComplex(){
        return imaginary;
    }

    public double getModulus(){
        return modulus;
    }

    public double getArgument(){
        return argument;
    }

    public ComplexNumber getConjugate(){
        return new ComplexNumber(real, -imaginary);
    }

    public boolean equals(ComplexNumber number){
        if(real == number.getReal() & imaginary == number.getComplex()){
            return true;
        } else {
            return false;
        }
    }

    public String toString(){
        return String.format("%.3f", real) + " + " + 
               String.format("%.3f", imaginary) + "i\n";
    }

    public String completeString(){
        return "Number: " + toString() + "Modulus: " + String.format("%.3f", modulus) +
               "\n" + "Argument: " + String.format("%.3f", argument) + " ~ " + 
               String.format("%.3f", Math.toDegrees(argument)) + "\u00B0\n";
    }

    public String trueValues(){
        return  "Number: " +real+ " + " +imaginary+ "\n" +
                "Modulus: " +modulus+ "\n" +
                "Argument: " +argument+ " ~ " +Math.toDegrees(argument)+ "\u00B0\n";
    }

}