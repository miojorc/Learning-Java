import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

public class Inicializer {
  JFrame Menu = new JFrame("Menu");
  public Inicializer(){
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
          ex.printStackTrace();
        }

        Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Menu.setLayout(new GridBagLayout());
        Menu.add(new ChangeCodes());
        
        Menu.pack();
        Menu.setLocationRelativeTo(null);
        Menu.setVisible(true);
      }
    });
  }

  public class ChangeCodes extends JPanel {
    public ChangeCodes() {
      setLayout(new GridBagLayout());
      JButton BracketGame = new JButton("Play BracketGame");
      add(BracketGame);
      BracketGame.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent arg0) {
          new BracketGameInterface();
        }
      });
      JButton Fibonacci = new JButton("open Fibonacci generator");
      add(Fibonacci);
      Fibonacci.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent arg0) {
          new FibonacciInterface();
        }
      });
    }



    @Override
    public Dimension getPreferredSize() { 
      return new Dimension(500, 400); //initialized size
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
    }
  }

  public static void main(String[] args){
    new Inicializer();
  }
}