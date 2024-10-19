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

    int blasts[][] = {{-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}};
    int aliensBlasts[][] = {{-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}, {-1,-1}};

    int alienBlastLocation = 0;

    int barriers[] = {40,140,240,340,440};

    int aliens[][] = {{100,50},{200,50},{300,50},{400,50},
                      {100,100},{200,100},{300,100},{400,100},
                      {100,150},{200,150},{300,150},{400,150},
                      {100,200},{200,200},{300,200},{400,200},
                      {100,250},{200,250},{300,250},{400,250}};

    int aliensQuaintity = 20;

    int lives = 3;
    int points = 0;

    int velocity = 0;

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

      Atacks();
    }


    private void Atacks(){
      Runnable runnable = () -> { 
        while(true){
          for(int i = 0; i<aliens.length; i++){ //teste aim of alien
            if((aliens[i][0]+10 > hero && aliens[i][0]-10 < hero) && !(aliens[i][0]+10 > alienBlastLocation && aliens[i][0]-10 < alienBlastLocation) && aliens[i][0] != 999){
              System.out.println("alien go blast");
              for(int j = 0; j<aliensBlasts.length; j++){
                if(aliensBlasts[j][0] == -1) {
                  aliensBlasts[j][0] = aliens[i][0]+2;
                  for(int k = i+16; k>0; k-=4){
                    System.out.println(k);
                    if(aliens[k][1] != 999){
                      aliensBlasts[j][1] = aliens[k][1];
                      break;
                    }
                  }

                  alienBlastLocation = hero;
                  break;
                }
              }
            }
          }

          for(int i = 0; i<blasts.length; i++){ //move blasts
            if(blasts[i][0] != -1){
              blasts[i][1]--;
              if(blasts[i][1] <= 1){
                blasts[i][0] = -1;
                blasts[i][1] = -1;
              } 
            } 
          }

          for(int i = 0; i<aliensBlasts.length; i++){ //move aliensBlasts
            if(aliensBlasts[i][0] != -1){
              aliensBlasts[i][1]++;
              if(aliensBlasts[i][1] >= 400){
                aliensBlasts[i][0] = -1;
                aliensBlasts[i][1] = -1;
                alienBlastLocation = 0;
              } 
            } 
          }

          for(int i = 0; i<aliens.length; i++){ //test if blast reach the alien
            for(int j = 0; j<blasts.length; j++){
              if((aliens[i][0]+10 > blasts[j][0] && aliens[i][0]-10 < blasts[j][0]) && (aliens[i][1]+5 > blasts[j][1] && aliens[i][1] < blasts[j][1]+5)){
                System.out.println("colide");
                blasts[j][0] = -1; aliens[i][0] = 999;
                blasts[j][1] = -1; aliens[i][1] = 999;
                points+=100;
              }
            }
          }

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

      g.setColor(Color.cyan); 
      for(int i = 0; i<blasts.length; i++){
        if(blasts[i][0] != -1){
          g.fillRect(blasts[i][0]+4, blasts[i][1], 3, 6);
        } 
      }

      Color lightPurple = new Color(250, 100, 250); g.setColor(lightPurple); 
      for(int i = 0; i<aliensBlasts.length; i++){
        if(aliensBlasts[i][0] != -1){
          g.fillRect(aliensBlasts[i][0]+4, aliensBlasts[i][1], 3, 6);
        } 
      }

      g.setColor(Color.WHITE); 
      for(int i = 0; i<barriers.length; i++){
        g.fillRect(barriers[i], 350, 20, 10); g.fillRect(barriers[i]-5, 345, 30, 10);
      }

      Color purple = new Color(200, 50, 200); g.setColor(purple); 
      for(int i = 0; i<aliens.length; i++){
        if(aliens[i][0] != 999) {
          //g.fillRect(aliens[i][0], aliens[i][1] , 10, 5); g.fillRect(aliens[i][0], aliens[i][1], 15, 5);
          g.drawString(String.valueOf(i), aliens[i][0], aliens[i][1]);
        }
      }

      g.setColor(Color.BLUE);  g.fillRect(hero, 370, 10, 20); g.fillRect(hero-10, 380, 30, 10);
    }
  }
}
