package date;

/**
 * Alter Date Format 문제
 */
public class DateFormatMain {

    public static void main(String[] args) {
        final int TEST_CASE_NUMBER = 4;
        String[] inputDates = {"PM 01:00:00", "PM 11:59:59", "AM 12:10:00", "AM 05:24:03"};
        int[] inputSeconds = {10, 1, 40, 102392};

        System.out.println("| 시각 | N | 답 |");
        System.out.println("|---|---|---|");
        for (int i = 0; i < TEST_CASE_NUMBER; i++) {
            CustomTime customtime = new CustomTime(inputDates[i]);
            printTimeResult(inputDates[i], inputSeconds[i], customtime.plusSecond(inputSeconds[i]));
        }
    }

    public static void printTimeResult(String inputDate, int inputSecond, CustomTime customTime) {
        System.out.println("| " + inputDate + " | " + inputSecond + " | " + customTime.formatDateString() + " |");
    }
}
