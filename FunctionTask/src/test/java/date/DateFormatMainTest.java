package date;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateFormatMainTest {

    @Test
    public void Add_AmDate_AddSeconds() {
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