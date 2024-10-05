import java.util.Scanner;

public class HelloWorld {
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);

    System.out.print("Digite a quantidade de números de fibonacci: ");
    int digitedNumber = Integer.valueOf(scan.nextLine());

    Maths.FibonacciSequece(digitedNumber, true);
  }
}