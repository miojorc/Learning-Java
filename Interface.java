import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Interface {
  JFrame frame = new JFrame("BracketGame");

  public Interface(){
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

    boolean direction = true;
    int relation = 10;

    boolean verticality = true;

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
        }
      });
      Pong();
    }


    private void Pong(){
      Runnable runnable = () -> { 
        while(true){
          ball = BallEvents.MoveBall(ball, relation, direction, verticality);

          if((racketR < ball[1] && racketR+130 > ball[1]) && (440 <= ball[0])){
            direction = false;
            relation = ball[1]%10;
            if(relation < 1) relation = 2;
          }else if((racketB < ball[1] && racketB+130 > ball[1]) && (60 >= ball[0])){
            direction = true;
            relation = ball[1]%10;
            if(relation < 1) relation = 2;
          }

          if(ball[1] > 390) verticality = !verticality;
          if(ball[1] < 1) verticality =!verticality;

          System.out.println(racketR);
          System.out.println(racketB);

          repaint();
          try {
            Thread.sleep(20);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
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

      g.setColor(Color.BLUE); g.fillRect(50, racketB, 10, 130);
      g.setColor(Color.BLACK); g.fillRect(ball[0], ball[1], 10, 10);
      g.setColor(Color.RED);  g.fillRect(450, racketR, 10, 130);
    }
  }
}
