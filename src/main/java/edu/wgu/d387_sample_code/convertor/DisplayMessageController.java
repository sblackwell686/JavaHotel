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
        // Executor service for multithreading
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // Create tasks for English and French welcome messages
        DisplayMessage englishMessageRunnable = new DisplayMessage(Locale.US);
        DisplayMessage frenchMessageRunnable = new DisplayMessage(Locale.CANADA_FRENCH);

        // Submit tasks to the executor service
        Future<String> englishFuture = executorService.submit(englishMessageRunnable::fetchWelcomeMessage);
        Future<String> frenchFuture = executorService.submit(frenchMessageRunnable::fetchWelcomeMessage);

        String englishMessage;
        String frenchMessage;

        try {
            // Retrieve the messages once the tasks are done
            englishMessage = englishFuture.get();
            frenchMessage = frenchFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error in processing the request", HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            executorService.shutdown();
        }
        // Combine the results of both threads
        String responseMessage = englishMessage + " " + frenchMessage;

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);

    }
}











