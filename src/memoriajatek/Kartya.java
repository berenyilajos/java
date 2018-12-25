package memoriajatek;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;

/**
 *
 * @author lajos
 */
public class Kartya extends ImagePanel implements MouseListener {

  private static final long serialVersionUID = 1L;
  private static String hatter = "images/hatter1.jpg";
  private static int klikk = 0, hattalVan;
  private static Kartya elozokartya = null;
  private Timer lefele;

  // Megjeleníti a képet, eredeti méretben, pozicionálás nélkül
  public Kartya(String file) {
    super(file);
    lefordit();
    addMouseListener(this);
    lefele = new Timer(1000, (ActionEvent e) -> {
      lefordit();
      lefeleStop();
//      System.out.println(hattalVan); // ellenőrzés
    });
  }

  private void lefeleStop() {
    lefele.stop();
  }

  public static String getHatter() {
    return hatter;
  }

  public static void reset(int hattal) {
    elozokartya = null;
    klikk = 0;
    hattalVan = hattal;
  }

  public static int getKlikk() {
    return klikk;
  }

  public void lefordit() {
    changeImage(hatter);
  }

  public void felfordit() {
    changeImage(kep);
  }

  // Megjeleníti a képet, eredeti méretben, az x,y pozícióban
  public Kartya(int x, int y, String file) {
    super(x, y, file);
  }

  // Megjeleníti a képet, a megadott pozícióban és méretben 
  public Kartya(int x, int y, int width, int height, String file) {
    super(x, y, width, height, file);
  }

  @Override
  public void mouseClicked(MouseEvent me) {

  }

  @Override
  public void mousePressed(MouseEvent me) {
    if (!ImagePanelFrame.getRun() || !img.equals(hatter) || (klikk == 1 && this.equals(elozokartya)) || (klikk == 0 && elozokartya != null)) {
      return;
    }
//        JOptionPane.showMessageDialog(null, klikk + " : " + kep);
    felfordit();
    this.update(this.getGraphics());
    klikk = (klikk + 1) % 2;
    if (klikk == 1) {
      elozokartya = this;
    } else {
      if (elozokartya.kep.equals(kep)) {
        hattalVan -= 2;
        if (hattalVan == 0) {
          ImagePanelFrame.kesz();
        }
      } else {
        // Előző verzió:
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//                lefordit();
//                elozopanel.lefordit();
        // Timeres verzió:
        lefele.start();
        elozokartya.lefele.start();
      }
      elozokartya = null;
    }
  }

  @Override
  public void mouseReleased(MouseEvent me) {

  }

  @Override
  public void mouseEntered(MouseEvent me) {
//    if (!ImagePanelFrame.getRun() || !img.equals(hatter) || (klikk == 1 && this.equals(elozokartya)) || (klikk == 0 && elozokartya != null)) {
//      return;
//    }
//    setCursor(new Cursor(Cursor.HAND_CURSOR));
  }

  @Override
  public void mouseExited(MouseEvent me) {
//    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
  }

}
