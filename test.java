import java.util.Scanner;
import java.util.ArrayList;;
public class test{
    static ArrayList<String> sepeStrings = new ArrayList<String>();
    static ArrayList<String> positiveNo = new ArrayList<>();
    static ArrayList<String> negativeNo = new ArrayList<>();
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String nowhitespace = s.replaceAll("\\s+", "");

        String[] terms = nowhitespace.split("[+-]");

        for(String i : terms){
            if(i!= ""){
                sepeStrings.add(i);
            }
        }

        System.out.println("Separated strings");
        for(String i: sepeStrings){
            System.out.println(i);
        }

        sort(nowhitespace.length(), nowhitespace);

        System.out.println("Positive");
        for(String i: positiveNo){
            System.out.println(i);
        }
        System.out.println("Negative");
        for(String i: negativeNo){
            System.out.println(i);
        }
 
        sc.close();

    }

    /*
     * Arithmetic string validation,
     * discerns if a strings components
     * are positive or negative storing
     * results in respective ArrayLists.
     */
    public static void sort(int length, String nowhitespace){
        int k = 0;
        int negativecounter = 0;
        int i = 0;

        /*
         * Throws null pointer exception if no
         * valid entry (ie. just symbols '+-')
         */
        if(sepeStrings.isEmpty()){
            System.out.println("\u001B[31m" + "Input Anomaly: No valid arguments"
                                + "\u001B[0m");

        } else {

            if( Character.compare(nowhitespace.charAt(i), '-') != 0 &&
                Character.compare(nowhitespace.charAt(i), '+') != 0){
                positiveNo.add(sepeStrings.get(k));
                k++;
            }

            while(i<length){
                
                if( Character.compare(nowhitespace.charAt(i), '-') == 0 ||
                    Character.compare(nowhitespace.charAt(i), '+') == 0){

                    if(Character.compare(nowhitespace.charAt(i), '-') == 0){
                        negativecounter++;

                        if(negativecounter > 1){
                            negativecounter = 0;
                        }

                    }

                    try{

                        if( Character.compare(nowhitespace.charAt(i+1), '-') != 0 &&
                            Character.compare(nowhitespace.charAt(i+1), '+') != 0 ){

                            if(negativecounter == 0){

                                positiveNo.add(sepeStrings.get(k));
                                k++;

                            } else {

                                negativeNo.add(sepeStrings.get(k));
                                k++;
                                negativecounter = 0;

                            }

                        }
    
                    } catch (StringIndexOutOfBoundsException s){
                        System.out.println("\u001B[33m" + "Input Anomaly: Trailing characters ignored" 
                                            + "\u001B[0m");
                    }
                    
                }

                i++;
                
            }
        }
    }

}