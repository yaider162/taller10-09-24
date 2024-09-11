package uptc.views.appointments.byCountry;



import uptc.interfaces.Interfaces;
import uptc.views.mainpage.MainPageFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainByCountry extends JDialog {
    private final MainPageFrame mainPageFrame;
    private final Interfaces.Presenter presenterVet;

    public MainByCountry(MainPageFrame mainPageFrame, Interfaces.Presenter presenterVet){
        super(mainPageFrame, true);
        this.mainPageFrame = mainPageFrame;
        this.presenterVet = presenterVet;
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
        FooterByCountry footerAllAppointments = new FooterByCountry(this, mainPageFrame, presenterVet);
        add(footerAllAppointments, BorderLayout.SOUTH);
    }
    private void createWork() {
        WorkByCountry workAllApointments = new WorkByCountry(presenterVet);
        add(workAllApointments, BorderLayout.CENTER);
    }
    private void createHeader() {
        HeaderByCountry headerAllAppointments = new HeaderByCountry(this, mainPageFrame, presenterVet);
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
