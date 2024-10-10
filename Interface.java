import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Interface {
  int racketB = 20;
  int racketR = 20;
  Color c = Color.BLUE;

  boolean gameEnd = false;

  public Interface(){
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
          ex.printStackTrace();
        }

        JFrame frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.add(new IPanel());
        frame.addKeyListener(new Controler());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
      }
    });
  }

  public class Controler extends Frame implements KeyListener{
    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
      if (e.getKeyChar() == 'w'){
        c = Color.RED;
        racketR++;
      }
      if (e.getKeyChar() == 's'){
        c = Color.RED;
        racketR--;
      }
      new IPanel();
      System.err.println("a");
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
      System.err.println("b");
    }

    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
      System.err.println("c");
    }    
  }

  public class IPanel extends JPanel{
    public IPanel() {
      //setLayout(new GridBagLayout());
      repaint();
    }

    @Override
    public Dimension getPreferredSize() { 
      return new Dimension(500, 500); //initialized size
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(c);
  
      if(c == Color.BLUE) g.fillRect(50, racketB, 10, 125);
      else if (c == Color.RED) g.fillRect(450, racketR, 10, 125);
    }
  
  }
}
