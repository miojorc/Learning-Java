public class Maths {
  public static String WriteSquare(double H, double W, boolean print){
    String square = "";
    
    W*=2.5;
    for (int i = 1; i<W+2; i++){
      
      square+="_";
    }
    
    square+="\n";
    for (int i = 0; i<H; i++){
      square+="|";
      if(i == H-1){
        for (int ii = 1; ii<W; ii++){
          square+="_";
        }
        square+="|";
      }else{
        for (int ii = 1; ii<W; ii++){
          square+=" ";
        }
        square+="|\n";
      }
    }
    if(print){
      square+="\n";
      System.out.print(square);
    }

    return square;
  } 

  public static int[][] SquaresFabricator(int[] sides, int C){ //for the interface
    int[][] config = new int[sides.length][3]; //3 = X, Y, (WIDTH and HEIGHT)
    
    int Hdistance = 0;
    int Vdistance = 0;

    for(int i = 0; i<sides.length; i++){
      config[i][0] = Hdistance;
      config[i][1] = Vdistance;
      config[i][2] = sides[i]*C;

      if(i%4 == 1) {
        Hdistance += sides[i]*C;
        if(i!=1){
          //Vdistance += sides[i-1]*C;
        }
      }
      else if(i%4 == 2) {
        Vdistance += sides[i]*C;
        Hdistance -= sides[i-1]*C;
      }
      else if(i%4 == 3) {
        Hdistance -= (sides[i]*C+sides[i-1]*C);
        Vdistance -= sides[i-1]*C;
      }
      else if(i!=0){
        Vdistance -= (sides[i]+sides[i-1])*C;
      }else{
        Vdistance -= sides[i]*C;
      }
    }
    
    return config;
  }

  public static int[] FibonacciSequece(int limiteLoop, boolean print){
    int preNumber1 = 1;
    int preNumber2 = 0;
    int fibonachi = 1;

    int[] fibonacciA = new int[limiteLoop];

    for(int i = 0; i < limiteLoop; i++){
      if(print) {
        WriteSquare(fibonachi,fibonachi,true);
      }
      fibonacciA[i] = fibonachi;
      fibonachi = preNumber1+preNumber2;

      preNumber2 = preNumber1;
      preNumber1 = fibonachi;
    }

    return fibonacciA;
  }
}
