import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagLayout;
//import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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

    public IPanel() {
      setLayout(new GridBagLayout());
      repaint();
    }

    public BasicStroke racket(){
      return new BasicStroke(100);
    }

    @Override
    public Dimension getPreferredSize() { 
      return new Dimension(1000, 1000); //initialized size
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(Color.GREEN);
  
      g.fillRect(10, 10, 10, 10);

      g.setColor(Color.BLACK);
      g.drawArc(10, 10, 10*2, 10*2, 0, 90);
    }
  
  }
}
