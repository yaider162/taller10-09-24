package uptc.views.appointments.allAppointments;

import interfaces.Interfaces;
import lombok.Getter;
import uptc.views.appointments.Table;
import uptc.views.wildCardClasses.Global;

import javax.swing.*;
import java.awt.*;

@Getter
public class WorkAll extends JPanel  {
    private Table tableAllAppointments;
    private final Interfaces.Presenter presenter;

    public WorkAll(Interfaces.Presenter presenter){
        this.presenter = presenter;
        initWorkPanel();
        setData();
    }
    private void initWorkPanel() {
        tableAllAppointments = new Table();
        setBackground(Global.WORK_BACKGROUND_COLOR);
        setForeground(Global.WORK_TEXT_COLOR);
        setLayout(new BorderLayout());
        createTitle();
        createTable();
    }
    private void createTable() {
        JScrollPane scrollPane = new JScrollPane(tableAllAppointments.getTable());
        scrollPane.setPreferredSize(obtainSize());
        add(scrollPane);
    }
    private Dimension obtainSize(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth() * 0.95;
        double height = screenSize.getHeight() * 0.6;
        return new Dimension((int)width, (int)height);
    }
    private void createTitle(){
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Global.WORK_BACKGROUND_COLOR);
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel data = new JLabel("Datos");
        data.setFont(Global.FONT_TITLE_BIG);
        data.setForeground(Global.WORK_TEXT_COLOR);
        add(titlePanel, BorderLayout.NORTH);
        titlePanel.add(data);
    }
    private void setData(){
        Object[][] visits = presenter.obtainVisits();
        for (Object[] datum : visits) {
            tableAllAppointments.putData(datum);
        }
    }
}
