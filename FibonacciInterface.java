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

public class FibonacciInterface {
  public FibonacciInterface(){
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

      int B = 20;

      int W = 275;

      int H = 445;

      int[][] config = new int[B][3];

      int C = 1;

      config = Maths.SquaresFabricator(Maths.FibonacciSequece(B,false), C);
      int angle = 0;

      int c1 = 0;
      int c2 = 0;

      if (drawBox) {
        g.setColor(Color.RED);
        for (int i = 0; i<B; i++){ //concertar "centro"
          if(i%4 == 1) {
            g.setColor(Color.RED);
          }
          else if(i%4 == 2) {
            g.setColor(Color.BLUE);
          }
          else if(i%4 == 3){
            g.setColor(Color.YELLOW);
          }else{
            g.setColor(Color.GREEN);
          }
          g.fillRect(config[i][0]+W, config[i][1]+H, config[i][2], config[i][2]);
        }
        g.setColor(Color.BLACK);
        for (int i = 0; i<B; i++){
          if(i%4 == 1) {
            angle = 90;
          }
          else if(i%4 == 2) {
            if(i!=0) c1 = -config[i][2];
            angle = 0;
          }
          else if(i%4 == 3){
            if(i!=0) c1 = -config[i][2];
            if(i!=0) c2 = -config[i][2];
            angle = 270;
          }else{
            c2 = -config[i][2];
            angle = 180;
          }
          g.setColor(Color.BLACK);
          g.drawArc(config[i][0]+W+c1, config[i][1]+H+c2, config[i][2]*2, config[i][2]*2, angle, 90);
          c1 = 0;
          c2 = 0;
        }
      }
    }
  
  }
}
