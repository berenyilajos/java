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
public class ShowTime extends JLabel {

  private Timer time;
  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private Calendar cal = Calendar.getInstance();
  // Kísérlet az állandó időlekérés elkerülésére:
  private long now = 0;
  private final int MASK = 1024 * 63;

  public ShowTime() {
    setBounds(600, 5, 180, 15);
    setFont(new Font("Georgia", Font.BOLD, 15));
    setHorizontalAlignment(SwingConstants.CENTER);
    setForeground(Color.WHITE);
    time = new Timer(1000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
//        cal = Calendar.getInstance();
//        setText(dateFormat.format(cal.getTime()));
        // Kísérlet az állandó időlekérés elkerülésére:
        if ((now & MASK) == 0) {
          cal = Calendar.getInstance();
          now = cal.getTimeInMillis();
        } else {
          now += 1000;
          cal.setTimeInMillis(now);
        }
        setText(dateFormat.format(cal.getTime()));
      }
    });
    time.start();
  }

}
