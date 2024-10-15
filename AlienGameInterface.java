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


public class AlienGameInterface {
  JFrame frame = new JFrame("Aliens Invader");

  public AlienGameInterface(){
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
          ex.printStackTrace();
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.BLACK);
        frame.setLayout(new GridBagLayout());
        frame.add(new Controler());
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
      }
    });
  }

  public class Controler extends JPanel {
    int hero = 125;
    int aliens = 10;

    int blasts[][] = {{-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}};

    int lives = 3;
    int points = 0;

    int velocity = 0;

    AtacksEvents AE;

    public Controler() {
      setLayout(new GridBagLayout());
      frame.addKeyListener(new KeyAdapter(){
        public void keyPressed(KeyEvent e) {
          if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
            hero += 5;
          }
          
          if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
            hero -= 5;
          }
          
          if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
            for(int i = 0; i<blasts.length; i++){
              if(blasts[i][0] == -1) {
                blasts[i][0] = hero;
                blasts[i][1] = 365;
                break;
              }
            }
          }

          System.out.println(e.getKeyCode());
          repaint();
        }
      });

      AliensAtacks();
    }


    private void AliensAtacks(){
      Runnable runnable = () -> { 
        while(true){
          repaint();
          try {
            LockSupport.parkNanos(2_000_000);
          } catch (Exception e) {
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
      g.setColor(Color.BLACK); g.fillRect(0, 0, 500, 400);

      g.setColor(Color.RED);    g.drawString("L", 40, 20); 
      g.setColor(Color.WHITE);  g.drawString(String.valueOf(lives), 50, 20);
      g.setColor(Color.CYAN);   g.drawString("P", 440, 20); 
      g.setColor(Color.WHITE);  g.drawString(String.valueOf(points), 450, 20);

      g.setColor(Color.WHITE); 
      
      if(blasts[0][0] != -1){
        for(int i = 0; i<blasts.length; i++){
          if(blasts[i][0] != -1){
            blasts[i][1]--;
            g.fillRect(blasts[i][0]+2, blasts[i][1], 2, 4);
            if(blasts[i][1] <= 1){
              blasts[i][0] = -1;
              blasts[i][1] = -1;
            } 
            System.out.println(i);
          } 
        }
      }

      g.setColor(Color.BLUE);  g.fillRect(hero, 370, 5, 10); g.fillRect(hero-5, 375, 15, 5);
    }
  }
}
