
import java.util.Scanner;

public class Sophisticatedsalary {

    public static void main(String[] args) {


        Scanner keyboard = new Scanner(System.in);


        String input = keyboard.nextLine();

        //This line split the string by space into an array call parts
        String[] parts= input.split(" ");


        int t = 0;
        int d = 0;
        int T = 0;
        int D = 0;



        for (int i = 0; i <= 3; i = i + 1){
            String s = parts[i];                             //the first/second/third/last element of array
            char ini = s.charAt(0);                          //first character of each element
            String initial = String.valueOf(ini);            //convert that character to string


            if (initial.equals("t")){                        //check if the string is same as int name
                t = Integer.parseInt(parts[i].substring(2)); //convert string into int
            }
            if (initial.equals("d")){
                d = Integer.parseInt(parts[i].substring(2));
            }
            if (initial.equals("T")){
                T = Integer.parseInt(parts[i].substring(2));
            }
            if (initial.equals("D")){
                D = Integer.parseInt(parts[i].substring(2));
            }


        }



        //initial money zero
        int money = 0;

        if (t >= T) {
            money= T * d;
        }
        else{
            money = t * d + (T-t) * D;
        }

        //number of * need to be printed
        int length = String.valueOf(money).length();
        String repeated = new String(new char[length+8]).replace("\0", "*");



        System.out.println(repeated);

        System.out.println(money+" Dollars");

        System.out.println(repeated);



    }

}
