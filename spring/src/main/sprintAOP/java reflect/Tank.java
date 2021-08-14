import java.util.Random;

public class Tank {
  public void move(){
    System.out.println("Tank moveing");
    Thread.sleep(new Random().nextInt(10000));
  }
}
