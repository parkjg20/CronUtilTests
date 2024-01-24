import com.cronutils.model.Cron;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CronTests {


    @Test
    @DisplayName("매 월 마지막 날을 잘 꺼내오는 지 검사")
    private void lastDayOfMonth(CronParser parser) {

        // 초 분 시 일 월 요일 년도
        Cron cron = parser.parse("? 0 0 L * ? *");

        // crontab 조건 테스트 날짜별 L 동작하는 지 확인
        List<ZonedDateTime> list = getDaysOfWholeYear();

        Cron everyLastDayOfMonth = lastDayOfMonth(parser);

        for (ZonedDateTime localDateTime : list) {
            if (ExecutionTime.forCron(everyLastDayOfMonth).isMatch(localDateTime)) {
                System.out.println(localDateTime + "[WeekDay] " +localDateTime.getDayOfWeek());
            }
        }
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
