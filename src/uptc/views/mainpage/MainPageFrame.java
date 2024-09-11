package uptc.views.mainpage;

import uptc.interfaces.Interfaces;

import javax.swing.*;
import java.awt.*;

public class MainPageFrame extends JFrame implements Interfaces.View {
    private Interfaces.Presenter presenter;

    public MainPageFrame(){
    }

    @Override
    public void start() {
        initComponents();
        createHeaderPanel();
        createWorkPanel();
        setVisible(true);
    }

    private void initComponents(){
        this.setTitle("Veterinaria");
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(getMainSize());
        this.setLocationRelativeTo(null);
    }
    private Dimension getMainSize(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight() * 0.68;
        return new Dimension((int)width, (int)height);
    }
    private void createWorkPanel(){
        JPanel workPanel = new WorkPanel(this, presenter);
        this.add(workPanel, BorderLayout.CENTER);
    }
    private void createHeaderPanel(){
        JPanel headerPanel = new HeaderPanel(this, presenter);
        this.add(headerPanel, BorderLayout.NORTH);
    }


    @Override
    public void setPresenter(Interfaces.Presenter presenter) {
        this.presenter = presenter;
    }
}
