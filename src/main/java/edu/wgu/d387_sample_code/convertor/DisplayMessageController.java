package edu.wgu.d387_sample_code.convertor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DisplayMessageController {

    @GetMapping("/welcome")
    public ResponseEntity<String> getWelcomeMessage(@RequestParam("lang") String languageTag) {
        // Create instances for English and French welcome messages
        DisplayMessage englishMessageRunnable = new DisplayMessage(Locale.US);
        DisplayMessage frenchMessageRunnable = new DisplayMessage(Locale.CANADA_FRENCH);

        // Start threads for each language
        Thread englishThread = new Thread(englishMessageRunnable);
        Thread frenchThread = new Thread(frenchMessageRunnable);
        englishThread.start();
        frenchThread.start();

        // Wait for both threads to complete
        try {
            englishThread.join();
            frenchThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error in processing the request", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Combine the results of both threads
        String responseMessage = "English Welcome Message: " + englishMessageRunnable.getMessage() + "<br>" +
                "French Welcome Message: " + frenchMessageRunnable.getMessage();

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}





