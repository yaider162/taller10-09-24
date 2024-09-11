package uptc.views.appointments.byState;


import uptc.interfaces.Interfaces;
import uptc.views.appointments.allAppointments.MainAll;
import uptc.views.appointments.byCity.MainByNextVaccineAppointments;
import uptc.views.appointments.byCountry.MainByParentAppointments;
import uptc.views.mainpage.MainPageFrame;
import uptc.views.wildCardClasses.Global;
import uptc.views.wildCardClasses.LabelHeader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FooterByState extends JPanel {
    private final JDialog parent;
    private final MainPageFrame mainPageFrame;
    private final Interfaces.Presenter presenter;

    public FooterByState(JDialog parent, MainPageFrame mainPageFrame, Interfaces.Presenter presenter) {
        this.mainPageFrame = mainPageFrame;
        this.parent = parent;
        this.presenter = presenter;
        initPanel();
    }
    private void initPanel() {
        this.setBackground(Global.FOOTER_BACKGROUND_COLOR);
        this.setLayout(new BorderLayout());
        createWorkPanel();
    }
    private void createWorkPanel() {
        JPanel gridPanel = new JPanel(new GridLayout(1, 4, 40, 40));
        gridPanel.setBackground(Global.FOOTER_BACKGROUND_COLOR);
        int marginSize = 60;
        JPanel marginPanel = new JPanel(new BorderLayout());
        marginPanel.add(gridPanel, BorderLayout.CENTER);
        marginPanel.setBorder(BorderFactory.createEmptyBorder(marginSize, marginSize, marginSize, marginSize));
        marginPanel.setBackground(Global.FOOTER_BACKGROUND_COLOR);
        addContent(gridPanel);
        add(marginPanel);
    }
    private void addContent(JPanel gridPanel){
        createTitle1(gridPanel);
        createTitle2(gridPanel);
        createTitle3(gridPanel);
        createTitle4(gridPanel);
    }
    private void createTitle1(JPanel gridPanel){
        JLabel orderBy = new LabelHeader("Ordernar por: ");
        orderBy.setFont(Global.FONT_TITLE_NORMAL);
        orderBy.setForeground(Global.FOOTER_TEXT_COLOR);
        gridPanel.add(orderBy);
    }
    private void createTitle2(JPanel gridPanel){
        JLabel date = new LabelHeader("Registros Por Estado");
        date.setFont(Global.FONT_TEXTS);
        date.setForeground(Global.FOOTER_TEXT_COLOR);
        date.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parent.dispose();
                createAllAppointments();
            }
        });
        gridPanel.add(date);
    }
    private void createTitle3(JPanel gridPanel){
        JLabel petsNextTo = new LabelHeader("Registros de Ciudad");
        petsNextTo.setFont(Global.FONT_TEXTS);
        petsNextTo.setForeground(Global.FOOTER_TEXT_COLOR);
        petsNextTo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parent.dispose();
                createByParentAppointments();
            }
        });
        gridPanel.add(petsNextTo);
    }
    private void createTitle4(JPanel gridpanel){
        JLabel responsible = new LabelHeader("Registros Por Condado");
        responsible.setFont(Global.FONT_TEXTS);
        responsible.setForeground(Global.FOOTER_TEXT_COLOR);
        responsible.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parent.dispose();
            }
        });
        gridpanel.add(responsible);
    }
    private void createAllAppointments(){
        MainAll mainAll = new MainAll(mainPageFrame, presenter);
        mainAll.setVisible(true);
    }
    private void createNextVaccineAppointments(){
        MainByNextVaccineAppointments mainByNextVaccineAppointments = new MainByNextVaccineAppointments(mainPageFrame, presenter);
        mainByNextVaccineAppointments.setVisible(true);
    }
    private void createByParentAppointments(){
        MainByParentAppointments mainByParentAppointments = new MainByParentAppointments(mainPageFrame, presenter);
        mainByParentAppointments.setVisible(true);
    }


}
