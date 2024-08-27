package edu.wgu.d387_sample_code.convertor;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
@CrossOrigin(origins = "http://localhost:4200")
public class TimeConvert {

    public String getTime() {
        return getTimeWithFormat("hh:mm a");
    }

    public String getTimeWithFormat(String timePattern) {
        ZonedDateTime time = ZonedDateTime.now();

        ZonedDateTime ET = time.withZoneSameInstant(ZoneId.of("America/New_York"));
        ZonedDateTime MT = time.withZoneSameInstant(ZoneId.of("America/Denver"));
        ZonedDateTime UTC = time.withZoneSameInstant(ZoneId.of("UTC"));

        DateTimeFormatter timePatternFormatter = DateTimeFormatter.ofPattern(timePattern);

        return String.format("%s ET / %s MT / %s UTC",
                ET.format(timePatternFormatter),
                MT.format(timePatternFormatter),
                UTC.format(timePatternFormatter));
    }

    public String getTimeInCustomZone(String zoneId, String timePattern) {
        ZonedDateTime time = ZonedDateTime.now();
        ZonedDateTime customZoneTime = time.withZoneSameInstant(ZoneId.of(zoneId));
        DateTimeFormatter timePatternFormatter = DateTimeFormatter.ofPattern(timePattern);
        return customZoneTime.format(timePatternFormatter);
    }
}

