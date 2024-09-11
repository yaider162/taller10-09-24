package uptc.views.appointments.byCity;



import uptc.interfaces.Interfaces;
import uptc.views.mainpage.MainPageFrame;
import uptc.views.registerAppointmentVet.RegisterAppointmentMainPage;
import uptc.views.wildCardClasses.CustomButton;
import uptc.views.wildCardClasses.Global;
import uptc.views.wildCardClasses.LabelHeader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HeaderByNextVaccineAppointments extends JPanel {
    private JPanel headerLabelsPanel;
    private JPanel titlePanel;
    private final JDialog parent;
    private final MainPageFrame mainPageFrame;
    private final Interfaces.Presenter presenter;
    public HeaderByNextVaccineAppointments(JDialog parent, MainPageFrame mainPageFrame, Interfaces.Presenter presenter){
        this.parent = parent;
        this.mainPageFrame = mainPageFrame;
        this.presenter = presenter;
        initComponents();
        createPanelHeaderLabels();
        createLabelAddVaccines();
        createTitlePanel();
        createLblTitle();
        createButtonExit();
    }
    private void initComponents(){
        this.setVisible(true);
        this.setBackground(Global.HEADER_BACKGROUND_COLOR);
        this.setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, getHeaderSize()));
    }
    private int getHeaderSize(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double height = screenSize.getHeight();
        return (int)(height * 0.18);
    }
    private void createPanelHeaderLabels(){
        headerLabelsPanel = new JPanel();
        FlowLayout layout = new FlowLayout(FlowLayout.RIGHT, 90, 45);
        headerLabelsPanel.setLayout(layout);
        headerLabelsPanel.setBackground(Global.HEADER_BACKGROUND_COLOR);
        this.add(headerLabelsPanel, BorderLayout.CENTER);
    }

    private void createLabelAddVaccines(){
        JLabel label =  new LabelHeader("Analisis Por Vehiculo");
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parent.dispose();
                createRegister();
            }
        });
        headerLabelsPanel.add(label);
    }
    private void createTitlePanel(){
        titlePanel = new JPanel();
        titlePanel.setBackground(Global.HEADER_BACKGROUND_COLOR);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        this.add(titlePanel, BorderLayout.WEST);
    }
    private void createLblTitle(){
        JLabel label = new JLabel("Analisis Por Vehiculo");
        titlePanel.add(Box.createHorizontalStrut(50));
        label.setFont(Global.FONT_TITLE_NORMAL);
        label.setForeground(Global.HEADER_TEXT_COLOR);
        titlePanel.add(label);
    }
    private void createButtonExit(){
        CustomButton button = new CustomButton("Salir");
        button.addActionListener(e -> System.exit(0));
        headerLabelsPanel.add(button);
    }

    private void createRegister(){
        RegisterAppointmentMainPage registerAppointmentMainPage = new RegisterAppointmentMainPage(mainPageFrame, presenter);
        registerAppointmentMainPage.setVisible(true);
    }

}
