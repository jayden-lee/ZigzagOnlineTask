package date;

import java.text.MessageFormat;

public class CustomTime {

    private TimeType timeType = TimeType.NONE;
    private int hour;
    private int minute;
    private int second;

    private final int HOURS_OF_DAY = 24;
    private final int MINUTES_OF_HOUR = 60;
    private final int SECONDS_OF_MINUTE = 60;
    private final int SECONDS_OF_HOUR = MINUTES_OF_HOUR * SECONDS_OF_MINUTE;
    private final int SECONDS_OF_DAY = HOURS_OF_DAY * SECONDS_OF_HOUR;

    private String dateFormatPattern;
    private final String DEFAULT_DATE_FORMAT_PATTERN = "{0}:{1}:{2}";

    private CustomTime() {
        dateFormatPattern = DEFAULT_DATE_FORMAT_PATTERN;
    }

    private CustomTime(TimeType timeType, int hour, int minute, int second) {
        this();
        checkTimeTypeValue(timeType);
        this.timeType = timeType;
        this.hour = hour;
        this.second = second;
        this.minute = minute;
    }

    private void checkTimeTypeValue(TimeType timeType) {
        if (timeType == null) {
            throw new IllegalArgumentException("Invalid date input value in 12 o'clock system.");
        }
    }

    public CustomTime(String value) {
        this();
        checkValidInputTimeValue(value);
        init(value);
    }

    private void init(String value) {
        String[] values = value.split(" ");
        timeType = TimeType.of(values[0]);

        String[] dateValues = values[1].split(":");
        checkValidInputDateValues(dateValues);

        hour = Integer.valueOf(dateValues[0]);
        minute = Integer.valueOf(dateValues[1]);
        second = Integer.valueOf(dateValues[2]);

        check12ClockTimeValues(hour, minute, second);
    }

    private void checkValidInputDateValues(String[] dateValues) {
        if (dateValues == null || dateValues.length != 3) {
            throw new IllegalArgumentException("The date input format is invalid.");
        }
    }

    private void check12ClockTimeValues(int hour, int minute, int second) {
        if (hour < 1 || hour > 12) {
            throw new IllegalArgumentException("The hour value was entered incorrectly.");
        }

        if (minute < 0 || minute >= 60) {
            throw new IllegalArgumentException("The minute value was entered incorrectly.");
        }

        if (second < 0 || second >= 60) {
            throw new IllegalArgumentException("The second value was entered incorrectly.");
        }
    }

    private void checkValidInputTimeValue(String inputDate) {
        if (inputDate == null || inputDate.length() != 11) {
            throw new IllegalArgumentException("Datetime value is invalid.");
        }
    }

    public CustomTime plusSecond(int inputSecond) {
        checkValidInputSeconds(inputSecond);
        if (inputSecond == 0) {
            return createNoneTypeCustomTimeWithoutCompareValue();
        }

        int baseTotalSeconds = totalSeconds(hour, minute, second);
        int newTotalSeconds = ((inputSecond % SECONDS_OF_DAY) + baseTotalSeconds + SECONDS_OF_DAY) % (SECONDS_OF_DAY);

        if (baseTotalSeconds == newTotalSeconds) {
            return createNoneTypeCustomTimeWithoutCompareValue();
        }

        int newHour = timeType.computeNewHour(hour, newTotalSeconds / (SECONDS_OF_HOUR));
        int newMinute = (newTotalSeconds / MINUTES_OF_HOUR) % MINUTES_OF_HOUR;
        int newSecond = newTotalSeconds % SECONDS_OF_MINUTE;

        return CustomTime.of(TimeType.NONE, newHour, newMinute, newSecond);
    }

    private void checkValidInputSeconds(int inputSecond) {
        if (inputSecond < 0 || inputSecond > 200000) {
            throw new IllegalArgumentException("N value must be greater than 0 and less than or equal to 200000.");
        }
    }

    private CustomTime createNoneTypeCustomTimeWithoutCompareValue() {
        int baseTotalSecond = totalSeconds(hour, minute, second);
        int newHour = timeType.computeNewHour(hour, baseTotalSecond / (SECONDS_OF_HOUR));
        int newMinute = (baseTotalSecond / MINUTES_OF_HOUR) % MINUTES_OF_HOUR;
        int newSecond = baseTotalSecond % SECONDS_OF_MINUTE;
        return CustomTime.of(TimeType.NONE, newHour, newMinute, newSecond);
    }

    private int totalSeconds(int hour, int minute, int second) {
        return hour * SECONDS_OF_HOUR + minute * SECONDS_OF_MINUTE + second;
    }

    public String formatDateString() {
        return MessageFormat.format(dateFormatPattern,
            convertTwoDigitFormatValue(hour),
            convertTwoDigitFormatValue(minute),
            convertTwoDigitFormatValue(second)
        );
    }

    private String convertTwoDigitFormatValue(int value) {
        return value < 10 ? "0" + value : String.valueOf(value);
    }

    public void changeDateFormat(String dateFormat) {
        this.dateFormatPattern = dateFormat;
    }

    @Override
    public String toString() {
        return "CustomTime{" +
            "timeType=" + timeType +
            ", hour=" + hour +
            ", minute=" + minute +
            ", second=" + second +
            '}';
    }

    public static CustomTime of(TimeType timeType, int hour, int minute, int second) {
        return new CustomTime(timeType, hour, minute, second);
    }
}
