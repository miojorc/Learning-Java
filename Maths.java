public class Maths {

  public static int[] FibonacciSequece(int limiteLoop, boolean print){
    int preNumber1 = 1;
    int preNumber2 = 0;
    int fibonachi = 1;

    int[] fibonacciA = new int[limiteLoop];

    for(int i = 0; i < limiteLoop; i++){
      if(print) System.out.println(fibonachi);
      fibonacciA[i] = fibonachi;
      fibonachi = preNumber1+preNumber2;

      preNumber2 = preNumber1;
      preNumber1 = fibonachi;
    }

    return fibonacciA;
  }
}
