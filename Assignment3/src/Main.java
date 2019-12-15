import java.util.*;

public class Main {

    public static void main(String[] args) {

        //get the input
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();

        //the list of all valid character
        List<String> valid = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "@", "&", "(", ")");

        int counter = 0;   //counter for number of character passed
        int counter1 = 0;  //counter for invalid characters
        int counter2 = 0;  //counter for number of (
        int counter3 = 0;  //counter for number of )
        boolean checked = false;     //mark whether we have checked

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            String s = Character.toString(c);
            counter += 1;

            //check if the character is in valid list
            if (!valid.contains(s)) {
                checked = true;   //already checked
                counter1 += 1;
                System.out.println("INVALID CHARACTERS");
                break;
            }
            //record number of ( and )
            if (s.equals("(")) {
                counter2 += 1;
            }
            if (s.equals(")")) {
                counter3 += 1;
            }
        }
        //if it reach the end of string and number of ( and ) doesn't match
        if (counter == input.length() && (counter2 != counter3)) {

            System.out.println("INVALID EXPRESSION");
            checked = true;

        }

        //if it doesn't have invalid character and the number of ( and ) match
        if (!checked) {
            //try executing the calculate function
            try {
                System.out.println(calculate(input));
            }
            //catch the exception: everything else than invalid character is invalid expression
            catch (Exception e) {
                System.out.println("INVALID EXPRESSION");
            }
        }
    }

    //the function that check if every character in a string is integer
    //if not return the index of that character
    private static int notNum(String astring){
        for (int i = 0; i < astring.length(); i++) {
            if (!Character.isDigit(astring.charAt(i))) {
                return i;
            }
        }
        return 0;
    }

    //the simplest calculation:
    // when the input string has only int1 and @/& and int2
    //eg. 34@5, 54&1  (only one @ or only one &)
    private static String simpleCalculation(String expression) {
        if (expression.contains("@")) {
            int index = expression.indexOf("@");

            //separate the first int and second int by using substring and index
            int first_int = Integer.parseInt(expression.substring(0, index));
            int second_int = Integer.parseInt(expression.substring(index + 1));

            //return the smaller int and convert it back to string
            String min = Integer.toString(Math.min(first_int, second_int));
            return min;
        }
        else if (expression.contains("&")) {
            int index = expression.indexOf("&");

            //separate the first int and second int by using substring and index
            int first_int = Integer.parseInt(expression.substring(0, index));
            int second_int = Integer.parseInt(expression.substring(index + 1));

            //return the larger int and convert it back to string
            String max = Integer.toString(Math.max(first_int, second_int));
            return max;
        }
        return expression;
    }


    //recusion function for calculate
    public static String calculate(String input) {

        //base case: input is a num
        if ((!input.contains("(")) && (!input.contains(")")) && (!input.contains("&")) && (!input.contains("@"))){
            return input;
        }
        else {
            //case of input doesn't have ( or )
            if ((!input.contains("(")) && (!input.contains(")"))) {
                int occurrence1 = 0;           //get the occurrence of @
                int occurrence2 = 0;            //get the occurrence of &

                //check how many times does @/& appear
                for (int i = 0; i < input.length(); i++) {
                    char c = input.charAt(i);
                    String s = Character.toString(c);
                    if (s.equals("@")) {
                        occurrence1 += 1;
                    }
                    if (s.equals("&")) {
                        occurrence2 += 1;
                    }
                }
                //in the case there is only one @
                if (occurrence1 == 1 && occurrence2 == 0) {
                    return simpleCalculation(input);
                }
                //in the case there is only one &
                if (occurrence2 == 1 && occurrence1 == 0) {
                    return simpleCalculation(input);
                }
                //when more than one @/&
                else {
                    //get first @/& and second @/&
                    int index1 = notNum(input);
                    int index2 = notNum(input.substring(index1+1));

                    //get substring which has only one @ or & by index
                    String substring = input.substring(0,index2+index1+1);

                    //call simpleCalculation function and combine the result with the old leftover string
                    String newString = simpleCalculation(substring) + input.substring(index2+index1+1);
                    return calculate(newString);
                }
            }
            else {
                int index3 = input.lastIndexOf("(");                //get most inner (
                int index4 = input.indexOf(")",index3+1);        //get first ) after that (

                // get the inner expression without ( and )
                String inner_string = input.substring(index3+1,index4);
                String result_of_inner_string = calculate(inner_string);

                String newInput = input.substring(0, index3) + result_of_inner_string + input.substring(index4+1);
                return calculate(newInput);
            }
        }
    }
}

