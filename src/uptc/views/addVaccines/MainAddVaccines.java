package uptc.views.addVaccines;



import uptc.interfaces.Interfaces;
import uptc.views.mainpage.MainPageFrame;
import uptc.views.wildCardClasses.CustomJComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainAddVaccines extends JDialog {
    private final MainPageFrame mainPageFrame;
    private final Interfaces.Presenter presenter;

    public MainAddVaccines(MainPageFrame mainPageFrame, Interfaces.Presenter presenter) {
        super(mainPageFrame, true);
        this.mainPageFrame = mainPageFrame;
        this.presenter = presenter;
        initComponents();
        createHeaderPanel();
        createWorkPanel();
    }
    private void initComponents(){
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
        double height = screenSize.getHeight() * 0.9;
        return new Dimension((int)width, (int)height);
    }
    private void createHeaderPanel(){
        HeaderPanelAddVaccines headerPanel = new HeaderPanelAddVaccines(this, mainPageFrame, presenter);
        this.add(headerPanel, BorderLayout.NORTH);
    }
    private void createWorkPanel(){
        WorkPanelAddVaccines workPanel = new WorkPanelAddVaccines(this, presenter);
//        workPanel.setDueTime(new CustomJComboBox(new String[]{"1 mes", "2 meses", "3 meses", "4 meses", "5 meses", "6 meses", "7 meses", "8 meses", "9 meses", "10 meses", "11 meses", "12 meses"}));
//        CustomJComboBox petType = new CustomJComboBox(presenter.obtainPetTypes());
//        workPanel.setPetType(petType);
        workPanel.build();
        this.add(workPanel, BorderLayout.CENTER);
    }
    private void fadeIn() {
        Timer timer = new Timer(10, new ActionListener() {
            private float opacity = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                opacity += 0.15f;
                setOpacity(Math.min(opacity, 1));
                if (opacity >= 1) {
                    ((Timer)e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

}
