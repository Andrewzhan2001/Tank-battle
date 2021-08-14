import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//AOP (aspect orient programming) 在执行到一个地方的时候加入代码
public class Main {
  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("app.xml");
    Tank t = (Tank)context.getBean("tank");
    t.move();
  }
}
