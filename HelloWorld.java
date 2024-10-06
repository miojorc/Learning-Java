import java.util.Scanner;

public class HelloWorld {
  public static void main(String[] args){
    int digitedNumber = 5;
    new Interface();
    
    try (Scanner scan = new Scanner(System.in)) {
      System.out.print("Digite a quantidade de números de fibonacci: ");
      digitedNumber = scan.nextInt();
    } catch (Exception e) {
      System.err.println("não é um numero, nós vamos considerar que vc digitou 5");
    } finally {
      Maths.FibonacciSequece(digitedNumber, true);
    }
  }
}