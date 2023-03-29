import view.MainFrame;
import javax.swing.*;

public class main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                    createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            new MainFrame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}