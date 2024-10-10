import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javafx.scene.input.KeyEvent;

public class Interface {
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
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
      }
    });
  }

  public class IPanel extends JPanel {
    int racketB = 20;
    int racketR = 20;
    Color c = Color.BLUE;

    public IPanel() {
      setLayout(new GridBagLayout());
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
