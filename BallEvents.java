public class BallEvents {
  public static int[] MoveBall(int[] defaultConfigurations ,double relation, boolean direction, boolean vertical){ //Define the direction // remember to add a bollean to change in vertical
    int[] config = defaultConfigurations;

    int sum = 0;
    for (int i = 0; i <= relation; i++) sum = i;

    if(direction)config[0] += sum;
    else config[0] -= sum;

    sum = 0;
    for (int i = 0; i <= relation/relation; i++) sum = i;
    config[1] += vertical ? sum : -sum; 

    return config;
  }
}