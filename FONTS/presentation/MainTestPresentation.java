package presentation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainTestPresentation {
    public static  void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    PresentationController presentationControllerTest = new PresentationController();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (FontFormatException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}