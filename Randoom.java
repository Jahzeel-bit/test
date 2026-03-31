import java.util.Scanner;
import java.util.Random;
class Randoom{
    public static void main(String[] args){
        Random rand = new Random();
        int length = 32;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number:" );
        int input = sc.nextInt();
        sc.close();
        for (int i=0; i<input; i++){
             int asciiVal = length+ rand.nextInt(95);
             char result = (char) asciiVal;   
       System.out.print(result);
        }
    }
}