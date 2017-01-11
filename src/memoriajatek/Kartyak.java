
package memoriajatek;

import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Lajos
 */
public class Kartyak extends JPanel{
    private int kepszam = 27;
    private String[] kepek;
    public Kartyak(int darab) {
        setOpaque(false);
        kepekFeltolt();
        initializeBackground(darab);
    }
    
    public void initializeBackground(int db){
        removeAll();
        setBounds(160 + ((6 - db) * 30), 20 + ((6 - db) * 20), db * 80 + ((6 - db) * 20), db * 80 + ((6 - db) * 20));
        
        setVisible(false);
        setLayout(new GridLayout(db, db, 24 / db, 24 / db));
        for (int i = 0; i < (db * db); i++) {
            add(new Kartya("images/00.jpg"));
        }
        setVisible(true);
    }
    
    public void initializeBackgroundAndBeginGame(int db){
        removeAll();
        setBounds(160 + ((6 - db) * 30), 20 + ((6 - db) * 20), db * 80 + ((6 - db) * 20), db * 80 + ((6 - db) * 20));
        setVisible(false);
        setLayout(new GridLayout(db, db, 24 / db, 24 / db));
        String[] kepek2 = getKepek(db * db);
        for (int i = 0; i < (db * db); i++) {
            add(new Kartya("images/" + kepek2[i]));
        }
        setVisible(true);
        Kartya.reset(db * db);
        this.repaint();
        JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Új játék!\nÓRA INDUL!");
    }
    
    void kepekFeltolt(){
        kepek = new String[kepszam];
        for (int i = 0; i < kepek.length; i++) {
            kepek[i] = getSzam(i) + ".jpg";
//            System.out.println(kepek[i]);
        }
//        System.out.println("*****************************-");
    }
    
    String getSzam(int szam){
        if (szam < 0)
            return "-" + getSzam(- szam);
        if (szam < 10)
            return "0" + szam;
        else
            return "" + szam;
    }
    
    String[] getPictures(int db){
        if (db > kepszam)
            return null;
        String[] pictures = new String[db];
        for (int i = 0; i < pictures.length; i++) {
            boolean van;
            do {
                String p = kepek[(int)(Math.random() * kepszam)];
                van = false;
                for (int j = 0; j < i; j++) {
                    if (pictures[j].equals(p))
                        van = true;
                }
                if (!van)
                    pictures[i] = p;
//                System.out.println(p);
            }
            while(van);
        }
        return pictures;
    }
    
    String[] getKepek(int db){
        String[] pictures = getPictures(db / 2);
        String kepek2[] = new String[db];
        for (int i = 0; i < pictures.length; i++) {
            boolean van;
            for (int j = 0; j < 2; j++) {
                do{
                    van = false;
                    int index = (int) (Math.random() * db);
                    if (kepek2[index] != null)
                        van = true;
                    if (!van)
                        kepek2[index] = pictures[i];
                }
                while(van);
            }
        }
        return kepek2;
    }
    
    
}
