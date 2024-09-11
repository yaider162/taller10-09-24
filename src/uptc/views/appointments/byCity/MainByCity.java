package uptc.views.appointments.byCity;



import uptc.interfaces.Interfaces;
import uptc.views.mainpage.MainPageFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainByCity extends JDialog {
    private final MainPageFrame mainPageFrame;
    private final Interfaces.Presenter presenter;

    public MainByCity(MainPageFrame mainPageFrame, Interfaces.Presenter presenter){
        super(mainPageFrame, true);
        this.mainPageFrame = mainPageFrame;
        this.presenter = presenter;
        initComponents();
        createHeader();
        createWork();
        createFooter();
    }
    private void initComponents() {
        this.setSize(obtainSize());
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        fadeIn();
    }
    private Dimension obtainSize(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        return new Dimension((int)width, (int)height);
    }
    private void createFooter() {
        FooterByCity footerAllAppointments = new FooterByCity(this, mainPageFrame, presenter);
        add(footerAllAppointments, BorderLayout.SOUTH);
    }
    private void createWork() {
        WorkByCity workAllApointments = new WorkByCity(presenter);
        add(workAllApointments, BorderLayout.CENTER);
    }
    private void createHeader() {
        HeaderByCity headerAllAppointments = new HeaderByCity(this, mainPageFrame, presenter);
        add(headerAllAppointments, BorderLayout.NORTH);
    }
    private void fadeIn() {
        Timer timer = new Timer(10, new ActionListener() {
            private float opacity = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                opacity += 0.15f;
                setOpacity(Math.min(opacity, 1)); // Set the new opacity
                if (opacity >= 1) {
                    ((Timer)e.getSource()).stop(); // Stop the timer when the window is fully opaque
                }
            }
        });
        timer.start();
    }

}
