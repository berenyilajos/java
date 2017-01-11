
package memoriajatek;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author lajos
 */
public class ImagePanel extends JPanel{

    protected BufferedImage image;
    protected String kep;
    protected String img;
    
     // Megjeleníti a képet, eredeti méretben, pozicionálás nélkül
    public ImagePanel(String file) {
        this.loadImageConstruct(file);
        this.setBounds(0,0,image.getWidth(),image.getHeight());
        this.setBorder(new LineBorder(Color.LIGHT_GRAY));
    }

    public String getImg() {
        return img;
    }

    public String getKep() {
        return kep;
    }
    
    private void hianyzoKep(String file, String message){
        JOptionPane.showMessageDialog(null, "Hiányzó kép: " + file + "\n" + message);
        System.exit(1);
    }
    
    private void loadImageConstruct(String file)
    {
        kep = file;
        changeImage(kep);
    }
    
    protected void loadImage(String file)
    {
       try
       {
            img = file;
            image = ImageIO.read(new File(file));
       } 
       catch (IOException ex) 
       {
           hianyzoKep(file, ex.getMessage());
       }
    }
    
    protected void changeImage(String file)
    {
        loadImage(file);
        this.validate();
        this.repaint();
    }
 
    
   // Megjeleníti a képet, eredeti méretben, az x,y pozícióban
    public ImagePanel(int x,int y,String file){
         loadImageConstruct(file);
         setBounds(x,y,image.getWidth(),image.getHeight());
         setBorder(new LineBorder(Color.LIGHT_GRAY));
    }
    
   // Megjeleníti a képet, a megadott pozícióban és méretben 
    public ImagePanel(int x,int y,int width, int height,String file) {
         loadImageConstruct(file);
         setBounds(x,y,width,height);
         setBorder(new LineBorder(Color.LIGHT_GRAY));
    }
     
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);  
    }
  
   
}