
package memoriajatek;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author Lajos
 */
public class ShowClock extends JLabel{
    private static Date beginDate;
    private Timer clock;
    public ShowClock() {
        setBounds(350, 0, 100, 21);
        setFont(new Font("Georgia", Font.BOLD, 21));
        setHorizontalAlignment(SwingConstants.CENTER);
        setForeground(new Color(170, 90, 0));
        clock = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setClock(lastSeconds());
            }
        });
    }
    
    public void clockStop(){
        clock.stop();
    }
    
    public void setClock(String seconds){
        setText(seconds);
    }
    
    public void setClock(){
        setText(lastSeconds());
    }
    
    private String lastSeconds(){
        Calendar currentDate = Calendar.getInstance();
        Date endDate = currentDate.getTime();
        long last = (endDate.getTime() - beginDate.getTime()) / 1000;
        return "" + last;
    }
    
    public void setBeginDate(){
        Calendar currentDate = Calendar.getInstance();
        beginDate = currentDate.getTime();
        clock.start();
        setClock("0");
    }
    
    public void kesz(){
        clock.stop();
        String lastseconds = lastSeconds();
        setClock(lastseconds);//csak hogy véletlenül se legyen eltérés a kettő közt
        JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), "Kész! Sikerült megoldanod!\n"
        + lastseconds + " másodpercig tartott.");
    }
    
}
