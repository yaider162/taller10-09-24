package uptc.views.appointments.byDate;

import interfaces.Interfaces;
import uptc.views.appointments.Table;
import uptc.views.wildCardClasses.Global;
import com.toedter.calendar.JDateChooser;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class WorkByDateAppointments extends JPanel {
    private JDateChooser dateChooser;
    private Table tableByDateAppointments;
    private final Interfaces.Presenter presenter;

    public WorkByDateAppointments(Interfaces.Presenter presenter){
        this.presenter = presenter;
        initWorkPanel();
    }
    private void initWorkPanel() {
        tableByDateAppointments = new Table();
        setBackground(Global.WORK_BACKGROUND_COLOR);
        setForeground(Global.WORK_TEXT_COLOR);
        createTitle();
        createTable();
    }
    private void createTable() {
        JScrollPane scrollPane = new JScrollPane(tableByDateAppointments.getTable());
        scrollPane.setPreferredSize(obtainSizeForTable());
        add(scrollPane, BorderLayout.CENTER);
    }
    private void createTitle(){
        JPanel subHeaderPanel = new JPanel();
        subHeaderPanel.setBackground(Global.WORK_BACKGROUND_COLOR);
        subHeaderPanel.setLayout(new BoxLayout(subHeaderPanel, BoxLayout.X_AXIS));
        subHeaderPanel.setPreferredSize(obtainSizeForSubHeader());
        createTitle(subHeaderPanel);
        createDateChooser(subHeaderPanel);
        add(subHeaderPanel, BorderLayout.NORTH);
    }
    private void createDateChooser(JPanel panel){
        JPanel dateChooserPanel = new JPanel();
        dateChooserPanel.setBackground(Global.WORK_BACKGROUND_COLOR);
        dateChooserPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        dateChooser = new JDateChooser();
        dateChooser.setPreferredSize(new Dimension(obtainSizeForSubHeader().width / 3, obtainSizeForSubHeader().height / 2));
        dateChooser.getDateEditor().addPropertyChangeListener(
                e -> {
                    if ("date".equals(e.getPropertyName())) {
                        setData();
                    }
                });
        dateChooserPanel.add(dateChooser);
        panel.add(dateChooserPanel);

    }
    private void createTitle(JPanel panel){
        JLabel title = new JLabel("Citas por Fecha: ");
        title.setFont(Global.FONT_TITLE_BIG);
        title.setForeground(Global.WORK_TEXT_COLOR);
        title.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(title);
    }
    private Dimension obtainSizeForTable(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth() * 0.95;
        double height = screenSize.getHeight() * 0.5;
        return new Dimension((int)width, (int)height);
    }
    private Dimension obtainSizeForSubHeader(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth() * 0.95;
        double height = screenSize.getHeight() * 0.1;
        return new Dimension((int)width, (int)height);
    }

    public void setData() {
        presenter.getDataAndSetData();
        Object[][] data = presenter.obtainVisitsByDate(dateChooser.getDate());
        for (Object[] datum : data) {
            tableByDateAppointments.putData(datum);
        }
    }
}
