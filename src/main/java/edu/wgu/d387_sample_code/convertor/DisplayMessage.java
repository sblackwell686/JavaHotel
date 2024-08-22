package edu.wgu.d387_sample_code.convertor;

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayMessage implements Runnable {

    private Locale locale;
    private String message;

    public DisplayMessage(Locale locale) {
        this.locale = locale;
    }

    public String fetchWelcomeMessage() {
        try {
            ResourceBundle resource = ResourceBundle.getBundle("translation", locale);
            return resource.getString("welcomeMessage");
        } catch (Exception e) {
            return "Error: Could not fetch welcome message for locale: " + locale;
        }
    }

    @Override
    public void run() {
        message = fetchWelcomeMessage();
    }

    public String getMessage() {
        return message;
    }
}

