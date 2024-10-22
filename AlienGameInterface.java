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

    int aliens[][] = {{40,50},{140,50},{240,50},{340,50},
                      {40,100},{140,100},{240,100},{340,100},
                      {40,150},{140,150},{240,150},{340,150},
                      {40,200},{140,200},{240,200},{340,200},
                      {40,250},{140,250},{240,250},{340,250}};

    int constMove = 1;

    int aliensQuaintity = 20;
    int loops = 0;

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
        while(lives>0){
          for(int i = 0; i<aliens.length; i++){ //teste aim of alien
            if((aliens[i][0]+10 > hero && aliens[i][0]-10 < hero) && !(aliens[i][0]+10 > alienBlastLocation && aliens[i][0]-10 < alienBlastLocation) && aliens[i][0] != 999){
              System.out.println("alien go blast");
              for(int j = 0; j<aliensBlasts.length; j++){
                if(aliensBlasts[j][0] == -1) {
                  aliensBlasts[j][0] = aliens[i][0]+2;
                  for(int k = i+16; k>0; k-=4){
                    System.out.println(k);
                    try{
                      if(aliens[k][1] != 999){
                        aliensBlasts[j][1] = aliens[k][1];
                        break;
                      }
                    }
                    catch(Exception e){
                      if(k < aliens.length) aliensBlasts[j][1] = aliens[k-4][1];
                    }
                  }

                  alienBlastLocation = hero;
                  break;
                }
              }
            }
          }
          System.out.println("a");

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
          System.out.println("b");

          for(int j = 0; j<aliensBlasts.length; j++){ //test if blast reach the hero
            if((hero+20 > aliensBlasts[j][0] && hero-10 < aliensBlasts[j][0]) && (375+5 > aliensBlasts[j][1] && 375 < aliensBlasts[j][1]+5)){
              System.out.println("colide");
              aliensBlasts[j][0] = -1; 
              aliensBlasts[j][1] = -1; 
              alienBlastLocation = 0;
              lives--;
            }
            if(340+5 > aliensBlasts[j][1] && 340 < aliensBlasts[j][1]+5){
              for(int i = 0; i<barriers.length; i++){
                if((barriers[i]+10 > aliensBlasts[j][0] && barriers[i]-10 < aliensBlasts[j][0])){
                  System.out.println("colide");
                  aliensBlasts[j][0] = -1; 
                  aliensBlasts[j][1] = -1; 
                  barriers[i] = 999;
                  alienBlastLocation = 0;
                }
              }
            }
          }
          System.out.println("c");

          repaint();
          try {
            LockSupport.parkNanos(2_000_000);
          } catch (Exception e) {
            e.printStackTrace();
          }
          loops++;
          if (loops%110 == 0) constMove = -constMove;
          if(loops > 2200000) loops = 1;
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
          g.fillRect(aliens[i][0], aliens[i][1] , 10, 5); g.fillRect(aliens[i][0], aliens[i][1], 15, 5);
          aliens[i][0]+=constMove; 
        }
      }

      g.setColor(Color.BLUE);  g.fillRect(hero, 370, 10, 20); g.fillRect(hero-10, 380, 30, 10);
    }
  }
}
