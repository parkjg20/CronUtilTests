import com.cronutils.mapper.CronMapper;
import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import com.cronutils.validation.CronValidator;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class CronTestApplication {

    public static void main(String[] args) {
        System.out.println("==================== START ====================");



        CronParser parser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ));

        // crontab 조건 테스트 날짜별 L 동작하는 지 확인
        List<ZonedDateTime> list = getDaysOfWholeYear();

        Cron everyLastDayOfMonth = lastDayOfMonth(parser);

        for (ZonedDateTime localDateTime : list) {
            if (ExecutionTime.forCron(everyLastDayOfMonth).isMatch(localDateTime)) {
                System.out.println(localDateTime + "[WeekDay] " +localDateTime.getDayOfWeek());
            }
        }

        System.out.println("====================  END  ====================");
    }

    private static Cron lastDayOfMonth(CronParser parser) {

        // 초 분 시 일 월 요일 년도
        return parser.parse("0 0 0 L * ? *"); // 매 월 마지막 날
    }

    private static List<ZonedDateTime> makeDataList() {
        return List.of(
            LocalDateTime.parse("2024-01-01T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-01-13T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-01-25T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-01-31T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-02-01T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-02-13T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-02-28T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-02-29T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-03-01T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-03-13T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-03-25T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-03-31T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-04-01T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-04-13T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-04-25T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-04-30T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-05-01T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-05-13T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-05-25T00:00:00").atZone(ZoneId.of("Asia/Seoul")),
            LocalDateTime.parse("2024-05-31T00:00:00").atZone(ZoneId.of("Asia/Seoul"))
        );

    }

    private static List<ZonedDateTime> getDaysOfWholeYear() {
        ZonedDateTime date = LocalDateTime.parse("2024-01-01T00:00:00").atZone(ZoneId.of("Asia/Seoul"));

        List<ZonedDateTime> dateList = new ArrayList<>();
        do {
            dateList.add(ZonedDateTime.from(date));

            date = date.plusDays(1);
        } while(date.getYear() < 2025);

        return dateList;
    }

}
