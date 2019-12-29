package date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateFormatMainTest {

    @Test
    @DisplayName("AM 시간 값에 N초 후 출력값이 올바른지 검증하는 테스트")
    public void Add_AmDate_AddSeconds() {
        // given
        String dateString = "AM 05:24:03";
        int second = 102392;

        // when
        CustomTime customTime = new CustomTime(dateString);
        customTime = customTime.plusSecond(second);

        // then
        assertEquals(customTime.formatDateString(), "09:50:35");
    }

    @Test
    @DisplayName("AM 시간 값에 N초 후 정오값이 출력되는지 검증하는 테스트")
    public void Add_AmDate_AddSeconds_Return_Noontime() {
        // given
        String dateString = "AM 11:59:59";
        int second = 1;

        // when
        CustomTime customTime = new CustomTime(dateString);
        customTime = customTime.plusSecond(second);

        // then
        assertEquals(customTime.formatDateString(), "12:00:00");
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

    @Test
    @DisplayName("PM 시간 값에 N초 후 자정값이 출력되는지 검증하는 테스트")
    public void Add_PmDate_AddSeconds_Return_Midnight() {
        // given
        String dateString = "PM 11:59:59";
        int second = 1;

        // when
        CustomTime customTime = new CustomTime(dateString);
        customTime = customTime.plusSecond(second);

        // then
        assertEquals(customTime.formatDateString(), "00:00:00");
    }

    @Test
    @DisplayName("주어진 형식에 맞지 않는 입력 시간 값이 주어진 경우에 에러가 발생하는지 테스트")
    public void Wrong_Input_DateValue_Format_ThrowException() {
        // given
        String dateString = "PM12:00:00";
        int second = 10;

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> {
                CustomTime customTime = new CustomTime(dateString);
                customTime = customTime.plusSecond(second);
            },
            "Expected new CustomTime(dateString) to throw");

        // then
        assertTrue(exception.getMessage().contains("Datetime value is invalid."));
    }

    @Test
    @DisplayName("입력 시간 값이 올바르지 않은 경우에 에러가 발생하는지 테스트")
    public void Wrong_Input_DateValue_Invalid_ThrowException() {
        // given
        String dateString = "AM 13:00:00";
        int second = 50;

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> {
                CustomTime customTime = new CustomTime(dateString);
                customTime = customTime.plusSecond(second);
            },
            "Expected new CustomTime(dateString) to throw");

        // then
        assertTrue(exception.getMessage().contains("The hour value was entered incorrectly."));
    }

    @Test
    @DisplayName("올바르지 않은 N 값이 입력될 때 에러가 발생하는지 테스트")
    public void Wrong_Input_N_ThrowException() {
        // given
        String dateString = "PM 05:00:00";
        int second = 250000;

        // when
        final CustomTime customTime = new CustomTime(dateString);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> {
                customTime.plusSecond(second);
            },
            "Expected plusSecond(second) to throw");

        // then
        assertTrue(exception.getMessage().contains("N value must be greater than 0 and less than or equal to 200000."));
    }
}