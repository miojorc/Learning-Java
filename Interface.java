import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
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

    private boolean drawBox;

    public IPanel() {
      setLayout(new GridBagLayout());
      JButton draw = new JButton("Draw Fibonacci");
      add(draw);
      draw.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          drawBox = !drawBox;
          repaint();
        }
      });
    }

    @Override
    public Dimension getPreferredSize() { 
      return new Dimension(1000, 1000); //initialized size
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      int[][] config = new int[30][3];
      config = Maths.SquaresFabricator(Maths.FibonacciSequece(30,false), 1);
      if (drawBox) {
        g.setColor(Color.RED);
        for (int i = 0; i<30; i++){
          if(i%3 == 1) g.setColor(Color.RED);
          else if(i%3 == 2) g.setColor(Color.BLUE);
          else g.setColor(Color.GREEN);
          g.fillRect(config[i][0], config[i][1], config[i][2], config[i][2]);
        }
      }
    }
  
  }
}
