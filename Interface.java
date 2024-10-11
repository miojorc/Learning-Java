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
  JFrame frame = new JFrame("Testing");

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

  public class Controler extends JPanel{
    int racketB = 20;
    int racketR = 20;

    boolean reset = true;

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

          repaint();
      }
      });
      if(reset){
        repaint();
        reset = false;
      }
    }

    @Override
    public Dimension getPreferredSize() { 
      return new Dimension(500, 500); //initialized size
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      g.setColor(Color.BLUE); g.fillRect(50, racketB, 10, 125);
      g.setColor(Color.RED);  g.fillRect(450, racketR, 10, 125);
    }
  }
}
