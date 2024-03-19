package site.parkjg20.testcron.utils;

import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.parser.CronParser;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class CronUtils {

    public static CronParser getCronParser() {

        return new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ));
    }

    public static List<ZonedDateTime> getDaysOfWholeYear() {

        ZonedDateTime first = LocalDateTime.parse("2024-01-01T00:00:00").atZone(ZoneId.of("Asia/Seoul"));
        ZonedDateTime last = LocalDateTime.parse("2024-12-31T00:00:00").atZone(ZoneId.of("Asia/Seoul"));
        return getDaysOfPeriod(first, last);
    }

    public static List<ZonedDateTime> getDaysOfPeriod(ZonedDateTime start, ZonedDateTime end) {
        ZonedDateTime date = start;

        List<ZonedDateTime> dateList = new ArrayList<>();
        do {
            dateList.add(ZonedDateTime.from(date));

            date = date.plusDays(1);
        } while(date.isBefore(end));

        return dateList;
    }

    /**
     * 2024년 공휴일 임의 등록
     */
    public static List<ZonedDateTime> getHolidays() {
        return List.of(
            LocalDateTime.parse("2024-01-01T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-02-09T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-02-10T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-02-11T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-02-12T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-03-01T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-05-01T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-05-05T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-05-15T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-06-06T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-08-15T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-09-16T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-09-17T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-09-18T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-10-03T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-12-25T00:00:00").atZone(ZoneId.of("Asia/Seoul"))
        );
    }
}
