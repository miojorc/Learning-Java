import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.locks.LockSupport;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class BracketGameInterface {
  JFrame frame = new JFrame("BracketGame");

  public BracketGameInterface(){
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
          ex.printStackTrace();
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.add(new Controler());
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
      }
    });
  }

  public class Controler extends JPanel {
    int racketB = 125;
    int racketR = 125;

    int ball[] = {60,185};

    boolean horizontality = true;
    boolean verticality = true;
    int relation = 10;

    int bluePoints = 0;
    int redPoints = 0;

    int velocity = 0;

    boolean startMovement = false;

    public Controler() {
      setLayout(new GridBagLayout());
      frame.addKeyListener(new KeyAdapter(){
        public void keyPressed(KeyEvent e) {
          if(e.getKeyCode() == KeyEvent.VK_UP){
            racketR -= 5;
          }
          
          if(e.getKeyCode() == KeyEvent.VK_DOWN){
            racketR += 5;
          }
          
          if(e.getKeyCode() == KeyEvent.VK_W){
            racketB -= 5;
          }

          if(e.getKeyCode() == KeyEvent.VK_S){
            racketB += 5;
          }
          System.out.println(e.getKeyCode());
          startMovement = true;
        }
      });
      //frame.te
      Pong();
    }


    private void Pong(){
      Runnable runnable = () -> { 
        while(true){
          if(startMovement) ball = BallEvents.MoveBall(ball, relation, horizontality, verticality);

          if((racketR < ball[1] && racketR+130 > ball[1]) && (440 <= ball[0])){
            horizontality = false;
            relation = ball[1]%10;
            if(relation < 1) relation = 2;
          }else if((racketB < ball[1] && racketB+130 > ball[1]) && (60 >= ball[0])){
            horizontality = true;
            relation = ball[1]%10;
            if(relation < 1) relation = 2;
          }

          if(ball[1] > 390) verticality = !verticality;
          if(ball[1] < 1) verticality =!verticality;

          if(ball[0] > 460) {
            velocity=0;
            startMovement = false;
            bluePoints++;
            ball[1] = racketR+60;
            ball[0] = 440;
          }
          if(ball[0] < 40) {
            velocity=0;
            startMovement = false;
            redPoints++;
            ball[1] = racketB+60;
            ball[0] = 60;
          }

          System.out.println(racketR);
          System.out.println(racketB);

          repaint();
          try {
            LockSupport.parkNanos(2_000_000-velocity);
          } catch (Exception e) {
            e.printStackTrace();
          }
          velocity+=100;
        }
      };
      new Thread(runnable).start();
    }

    @Override
    public Dimension getPreferredSize() { 
      return new Dimension(500, 400); //initialized size
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      g.drawString(String.valueOf(bluePoints), 50, 20);
      g.drawString(String.valueOf(redPoints), 450, 20);

      g.setColor(Color.BLUE); g.fillRect(50, racketB, 10, 130);
      g.setColor(Color.BLACK); g.fillRect(ball[0], ball[1], 10, 10);
      g.setColor(Color.RED);  g.fillRect(450, racketR, 10, 130);
    }
  }
}
