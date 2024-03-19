package testcron;

import com.cronutils.model.Cron;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import java.time.ZonedDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.parkjg20.testcron.utils.CronUtils;


/**
 *<pre>
 *--
 *cron-utils 테스트
 *테스트 케이스 - 반복 Push 발송 시 지원하는 범위에 대해 테스트
 *--
 *</pre>
 *  1. 매일 실행
 *  <ul>
 *      <li>시간이 다른 경우</li>
 *  </ul>
 *  2. 매주 실행
 *  <ul>
 *      <li>일반</li>
 *      <li>주말 제외</li>
 *      <li>공휴일 제외</li>
 *      <li>주말, 공휴일 제외</li>
 *  </ul>
 *  3. 매월 실행
 *  <ul>
 *      <li>31일</li>
 *      <li>마지막 날</li>
 *      <li>셋째 주 토요일(공휴일 제외)</li>
 *  </ul>
 *  4. 매년 실행
 *  <ul>
 *      <li>1월 1일</li>
 *      <li>공휴일 제외(공휴일만 가져와서 판단)</li>
 *  </ul>
 */
public class WeeklyCronTests {

    @Test
    @DisplayName("1.1 매일 실행")
    public void everyday() {
        CronParser parser = CronUtils.getCronParser();

        // 초 분 시 일 월 요일 년도
        String cronString = "0 0 0 * * *";

        Cron cron = parser.parse(cronString);

        // crontab 조건 테스트 날짜별 L 동작하는 지 확인
        List<ZonedDateTime> list = CronUtils.getDaysOfWholeYear();
        List<String> filtered = list.stream()
            .filter(localDateTime -> ExecutionTime.forCron(cron).isMatch(localDateTime))
            .map(localDateTime -> localDateTime + "[WeekDay] " + localDateTime.getDayOfWeek())
            .toList();

        Assertions.assertNotNull(filtered);
        Assertions.assertEquals(filtered.size(), list.size());

        filtered.forEach(System.out::println);
    }


    @Test
    @DisplayName("매 월 마지막 날을 잘 꺼내오는 지 검사")
    public void everyLastDayOfMonth() {
        CronParser parser = CronUtils.getCronParser();

        // 초 분 시 일 월 요일 년도
        Cron cron = parser.parse("0 0 0 L * ? *");

        // crontab 조건 테스트 날짜별 L 동작하는 지 확인
        List<ZonedDateTime> list = CronUtils.getDaysOfWholeYear();

        List<String> filtered = list.stream()
            .filter(localDateTime -> ExecutionTime.forCron(cron).isMatch(localDateTime))
            .map(localDateTime -> localDateTime + "[WeekDay] " + localDateTime.getDayOfWeek())
            .toList();

        Assertions.assertNotNull(filtered);
        Assertions.assertEquals(filtered.size(), 12);

        filtered.forEach(System.out::println);
    }

    @Test
    @DisplayName("매 월 3번째 화요일 잘 꺼내오는 지 검사")
    public void everyThirdTuesday() {
        CronParser parser = CronUtils.getCronParser();

        // 몇 번째?
        int nth = 3;

        // 초 분 시 일 월 요일 년도
        String cronString = "0 0 0 ? * TUE#" + nth;
        System.out.println("cron string: " + cronString);

        Cron cron = parser.parse(cronString);

        // crontab 조건 테스트 날짜별 L 동작하는 지 확인
        List<ZonedDateTime> list = CronUtils.getDaysOfWholeYear();
        List<String> filtered = list.stream()
            .filter(localDateTime -> ExecutionTime.forCron(cron).isMatch(localDateTime))
            .map(localDateTime -> localDateTime + "[WeekDay] " + localDateTime.getDayOfWeek())
            .toList();

        Assertions.assertNotNull(filtered);
        Assertions.assertEquals(filtered.size(), 12);

        filtered.forEach(System.out::println);
    }

}
