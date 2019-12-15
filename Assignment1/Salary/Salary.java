import java.util.Scanner;

public class Salary {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        int t = keyboard.nextInt(); //normal working hrs
        int d = keyboard.nextInt(); //normal salary
        int D = keyboard.nextInt(); //beyond hrs salary
        int T = keyboard.nextInt(); //actual working hrs

        if(t >= T){
            System.out.println(T * d);
        }
        else{
            System.out.println(t * d + (T-t) * D);
        }
    }
}


//Test cases:
//t=40 d=10 D=20 T=45   500
//t=35 d=15 D=18 T=20   300
//t=35 d=15 D=20 T=5    75
