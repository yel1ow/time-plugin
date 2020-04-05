package org.example.time_plugin;

import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyToolWindow {
  private JLabel iconLabel;
  private JLabel currentTime;
  private JButton hideToolWindowButton;
  private JPanel myToolWindowContent;

  public MyToolWindow(ToolWindow toolWindow) {
    hideToolWindowButton.addActionListener(e -> toolWindow.hide(null));
    this.currentDateTime();
  }

  public void currentDateTime() {
    iconLabel.setIcon(new ImageIcon(getClass().getResource("/toolWindow/watch.jpg")));

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    executor.scheduleAtFixedRate(
            () -> currentTime.setText(localTimeToStringConverter(LocalTime.now())),
            0,
            1,
            TimeUnit.SECONDS
    );
  }

  public JPanel getContent() {
    return myToolWindowContent;
  }

  private String localTimeToStringConverter(LocalTime localTime) {
    int hour = localTime.getHour();
    String strHour = hour < 10 ? "0" + hour : String.valueOf(hour);

    int min = localTime.getMinute();
    String strMin = min < 10 ? "0" + min : String.valueOf(min);

    int sec = localTime.getSecond();
    String strSec = sec < 10 ? "0" + sec : String.valueOf(sec);

    return strHour + ":" + strMin + ":" + strSec;
  }
}
