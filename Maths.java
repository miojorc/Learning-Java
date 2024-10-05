public class Maths {
  public static double MadeArea(double H, double W){
    double area = H*W;
    
    W*=2.5;
    for (int i = 1; i<W+2; i++){
      System.out.print('_');
    }
    System.out.println();
    for (int i = 0; i<H; i++){
      System.out.print("|");
      if(i == H-1){
        for (int ii = 1; ii<W; ii++){
          System.out.print("_");
        }
      }else{
        for (int ii = 1; ii<W; ii++){
          System.out.print(" ");
        }
      }
      System.out.println("|");
    }
    System.out.println();
    
    return area;
  } 

  public static int[] FibonacciSequece(int limiteLoop, boolean print){
    int preNumber1 = 1;
    int preNumber2 = 0;
    int fibonachi = 1;

    int[] fibonacciA = new int[limiteLoop];

    for(int i = 0; i < limiteLoop; i++){
      if(print) {
        System.out.println(fibonachi);
        MadeArea(fibonachi,fibonachi);
      }
      fibonacciA[i] = fibonachi;
      fibonachi = preNumber1+preNumber2;

      preNumber2 = preNumber1;
      preNumber1 = fibonachi;
    }

    return fibonacciA;
  }
}
/*
  _____
  |   |
  |___|
 */