package memoriajatek;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Lajos
 */
public class Kartyak extends JPanel {

  private final int KEPSZAM = 27;
  private ArrayList<String> kepek;

  public Kartyak(int darab) {
    setOpaque(false);
    kepekFeltolt();
    initializeBackground(darab);
  }

  public void initializeBackground(int db) {
    removeAll();
    setBounds(160 + ((6 - db) * 30), 20 + ((6 - db) * 20), db * 80 + ((6 - db) * 20), db * 80 + ((6 - db) * 20));

    setVisible(false);
    setLayout(new GridLayout(db, db, 24 / db, 24 / db));
    for (int i = 0; i < (db * db); i++) {
      add(new Kartya("images/00.jpg"));
    }
    setVisible(true);
  }

  public void initializeBackgroundAndBeginGame(int db) {
    removeAll();
    setBounds(160 + ((6 - db) * 30), 20 + ((6 - db) * 20), db * 80 + ((6 - db) * 20), db * 80 + ((6 - db) * 20));
    setVisible(false);
    setLayout(new GridLayout(db, db, 24 / db, 24 / db));
    getKepek(db * db).forEach((kep) -> {
      add(new Kartya("images/" + kep));
    });
    setVisible(true);
    Kartya.reset(db * db);
    this.repaint();
    JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Új játék!\nÓRA INDUL!");
  }

  void kepekFeltolt() {
    kepek = new ArrayList<>(KEPSZAM);
    for (int i = 0; i < KEPSZAM; i++) {
      kepek.add(getSzam(i) + ".jpg");
//            System.out.println(kepek.get(i));
    }
//        System.out.println("*****************************-");
  }

  String getSzam(int szam) {
    if (szam < 0) {
      return "-" + getSzam(-szam);
    }
    if (szam < 10) {
      return "0" + szam;
    } else {
      return "" + szam;
    }
  }

  ArrayList<String> getKepek(int db) {
    if (db > KEPSZAM * 2 || db < 4 || db % 4 != 0) {
      return new ArrayList<>();
    }
    Collections.shuffle(kepek);
    ArrayList<String> pictures = new ArrayList<>(db);
    for (int i = 0; i < db / 2; i++) {
      pictures.add(kepek.get(i));
//      pictures.add(kepek.get(i));
    }
    pictures.addAll(pictures);
    Collections.shuffle(pictures);
    return pictures;
  }

}
