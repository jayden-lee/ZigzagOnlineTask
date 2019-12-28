package date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateFormatMainTest {

    @Test
    @DisplayName("AM 시간 값에 N초 후 출력값이 올바른지 검증하는 테스트")
    public void Add_AmDate_AddSeconds() {
        // given
        String dateString = "AM 12:10:00";
        int second = 40;

        // when
        CustomTime customTime = new CustomTime(dateString);
        customTime = customTime.plusSecond(second);

        // then
        assertEquals(customTime.formatDateString(), "00:10:40");
    }

    @Test
    @DisplayName("PM 시간 값에 N초 후 출력값이 올바른지 검증하는 테스트")
    public void Add_PmDate_AddSeconds() {
        // given
        String dateString = "PM 01:00:00";
        int second = 10;

        // when
        CustomTime customTime = new CustomTime(dateString);
        customTime = customTime.plusSecond(second);

        // then
        assertEquals(customTime.formatDateString(), "13:00:10");
    }

}