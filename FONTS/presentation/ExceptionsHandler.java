package presentation;

import javax.swing.*;

public class ExceptionsHandler {
    public static void handleException(Exception e) {
        String error_msg = e.getMessage();
        if (error_msg == null || error_msg.isEmpty()) {
            error_msg = "Generic Error"; // if this occurs, means that the exception is not called right
        }
        show_warning(error_msg);
    }

    private static void show_warning(String msg) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
