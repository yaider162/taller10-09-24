package uptc.views.appointments.byDate;

import uptc.interfaces.Interfaces;
import uptc.views.mainpage.MainPageFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainByDateAppointments extends JDialog {
    private final MainPageFrame mainPageFrame;
    private final Interfaces.Presenter presenter;
    public MainByDateAppointments(MainPageFrame mainPageFrame, Interfaces.Presenter presenter){
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
        FooterByDateAppointments footerAllAppointments = new FooterByDateAppointments(this, mainPageFrame, presenter);
        add(footerAllAppointments, BorderLayout.SOUTH);
    }
    private void createWork() {
        WorkByDateAppointments workAllApointments = new WorkByDateAppointments(presenter);
        add(workAllApointments, BorderLayout.CENTER);
    }
    private void createHeader() {
        HeaderByDateAppointments headerAllAppointments = new HeaderByDateAppointments(this, mainPageFrame, presenter);
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
