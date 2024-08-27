package edu.wgu.d387_sample_code.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TimeConvertController {

    private final edu.wgu.d387_sample_code.convertor.TimeConvert timeConvert;

    @Autowired
    public TimeConvertController(edu.wgu.d387_sample_code.convertor.TimeConvert timeConvert) {
        this.timeConvert = timeConvert;
    }

    @GetMapping("/presentation")
    public ResponseEntity<String> timePresentation() {
        String times = "Join us for an online live presentation held at: " + timeConvert.getTime();
        return new ResponseEntity<>(times, HttpStatus.OK);
    }

    @GetMapping("/presentation/custom")
    public ResponseEntity<String> timePresentationCustom(@RequestParam String pattern) {
        String times = "Join us for an online live presentation held at: " + timeConvert.getTimeWithFormat(pattern);
        return new ResponseEntity<>(times, HttpStatus.OK);
    }

    @GetMapping("/presentation/customZone")
    public ResponseEntity<String> timePresentationInCustomZone(@RequestParam String zoneId, @RequestParam String pattern) {
        String times = "Join us for an online live presentation held at: " + timeConvert.getTimeInCustomZone(zoneId, pattern);
        return new ResponseEntity<>(times, HttpStatus.OK);
    }
}
