import interfaces.Interfaces;
import uptc.presenter.Presenter;
import uptc.views.mainpage.MainPageFrame;

public class App {
    public static void main(String[] args) {
        MainPageFrame mainPageFrame = new MainPageFrame();
        Interfaces.Presenter presenter = new Presenter();
        mainPageFrame.setPresenter(presenter);
        mainPageFrame.start();
    }
}
