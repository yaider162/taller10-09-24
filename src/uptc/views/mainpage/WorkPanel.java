package uptc.views.mainpage;

import co.edu.uptc.interfaces.VetInterface;
import co.edu.uptc.views.registerAppointmentVet.RegisterAppointmentMainPage;
import co.edu.uptc.views.wildCardClasses.CustomButton;
import co.edu.uptc.views.wildCardClasses.Global;

import javax.swing.*;
import java.awt.*;

public class WorkPanel extends JPanel {
    private final MainPageFrame mainPageFrame;
    private final VetInterface.Presenter presenterVet;

    public WorkPanel(MainPageFrame mainPageFrame, VetInterface.Presenter presenterVet){
        this.mainPageFrame = mainPageFrame;
        this.presenterVet = presenterVet;
        initWorkPanel();
        createLabelAndButton();
    }

    private void initWorkPanel() {
        setBackground(Global.WORK_BACKGROUND_COLOR);
        setForeground(Global.WORK_TEXT_COLOR);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    private void createLabelAndButton(){
        add(Box.createVerticalGlue());
        JLabel label = new JLabel("Registrar Cita");
        label.setFont(Global.FONT_TITLE_BIG);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);
        add(Box.createVerticalStrut(50));
        CustomButton button = new CustomButton("Registrar");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(e -> registerAppointment());
        add(button);
        add(Box.createVerticalGlue());
    }
    private void registerAppointment(){
        RegisterAppointmentMainPage registerAppointmentMainPage = new RegisterAppointmentMainPage(mainPageFrame, presenterVet);
        registerAppointmentMainPage.setVisible(true);
    }
}
