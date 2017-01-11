
package memoriajatek;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author lajos
 */
public class ImagePanelFrame extends JFrame implements ActionListener {

  private Container cp = getContentPane();
  private Kartyak kartyak;
  private JPanel gui;
  private ImagePanel mainPanel;
  private JButton ng, mn, mcs;
  private int darab;
  private static boolean run = false;
  private static ShowClock showClock = new ShowClock();
  private ShowTime showTime;
  private MouseAdapter mouseAdapter = new MouseAdapter() {
    @Override
    public void mouseEntered(MouseEvent e) {
      setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
      setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
  };
  WindowAdapter windowAdapter = new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        int close = JOptionPane.showConfirmDialog(rootPane,
                "Tényleg bezárod a programot?",
                "Program bezárása",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (close != JOptionPane.OK_OPTION) {
          return;
        }
        System.exit(0);
      }
    };

  public ImagePanelFrame() {
    darab = 4;
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    gui = new JPanel(new GridBagLayout());
    gui.setBorder(new EmptyBorder(5, 5, 5, 5));

    setTitle("Memória játék");
    mainPanel = new ImagePanel("images/zoo3.png");
    mainPanel.setLayout(null);
    mainPanel.setPreferredSize(new Dimension(800, 600));
    cp.add(gui);
    gui.add(mainPanel);

    mainPanel.add(showClock);

    showTime = new ShowTime();
    mainPanel.add(showTime);

    kartyak = new Kartyak(darab);

    mainPanel.add(kartyak);

    mn = new JButton(new ImageIcon("images/novel.png"));
    mn.setBounds(50, 520, 190, 30);
    mn.addActionListener(this);
    mn.addMouseListener(mouseAdapter);

    mcs = new JButton(new ImageIcon("images/csokkent.png"));
    mcs.setBounds(280, 520, 240, 30);
    mcs.addActionListener(this);
    mcs.addMouseListener(mouseAdapter);

    ng = new JButton(new ImageIcon("images/newgame.png"));
    ng.setBounds(560, 520, 140, 30);
    ng.addActionListener(this);
    ng.addMouseListener(mouseAdapter);

    mainPanel.add(mn);
    mainPanel.add(mcs);
    mainPanel.add(ng);

    this.pack();

    setLocationRelativeTo(this);
    addWindowListener(windowAdapter);
    setVisible(true);
    setResizable(false);
  }

  public static void kesz() {
    showClock.kesz();
    run = false;
  }

  public static boolean getRun() {
    return run;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton btn = (JButton) e.getSource();
    if (btn.equals(mn)) {
      if (darab == 6) {
        return;
      }
      showClock.setClock("");
      showClock.clockStop();
      darab += 2;
      kartyak.initializeBackground(darab);
      this.repaint();
      run = false;
    }
    if (btn.equals(mcs)) {
      if (darab == 2) {
        return;
      }
      showClock.setClock("");
      showClock.clockStop();
      darab -= 2;
      kartyak.initializeBackground(darab);
      this.repaint();
      run = false;
    } else if (btn.equals(ng)) {
      showClock.setClock("");
      showClock.clockStop();
      kartyak.initializeBackgroundAndBeginGame(darab);
      run = true;
      showClock.setBeginDate();
    }
  }

}
