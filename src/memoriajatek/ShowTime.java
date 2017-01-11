
package memoriajatek;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author Lajos
 */
public class ShowTime extends JLabel{
    private Timer time;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public ShowTime() {
        setBounds(600, 5, 180, 15);
        setFont(new Font("Georgia", Font.BOLD, 15));
        setHorizontalAlignment(SwingConstants.CENTER);
        setForeground(Color.WHITE);
        time = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar cal = Calendar.getInstance();
                setText(dateFormat.format(cal.getTime()));
            }
        });
        time.start();
    }
    
        
}
