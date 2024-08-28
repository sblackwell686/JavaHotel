package edu.wgu.d387_sample_code.convertor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DisplayMessageController {

    @GetMapping("/welcome")
    public ResponseEntity<String> getWelcomeMessage(@RequestParam("lang") String languageTag) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Locale locale = Locale.forLanguageTag(languageTag);
        DisplayMessage displayMessageRunnable = new DisplayMessage(locale);

        Future<String> messageFuture = executorService.submit(displayMessageRunnable::fetchWelcomeMessage);

        String message;
        try {
            message = messageFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error in processing the request", HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            executorService.shutdown();
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}











