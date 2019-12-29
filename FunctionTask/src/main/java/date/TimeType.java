package date;

public enum TimeType {

    AM("AM") {
        @Override
        public int computeNewHour(int baseHour, int newHour) {
            if (baseHour >= TWELVE) {
                newHour -= TWELVE;
            }

            return convertOverValue(newHour);
        }
    },
    PM("PM") {
        @Override
        public int computeNewHour(int baseHour, int newHour) {
            newHour += TWELVE;
            return convertOverValue(newHour);
        }
    },
    NONE("NONE") {
        @Override
        public int computeNewHour(int baseHour, int newHour) {
            return newHour;
        }
    };

    private String name;
    private static final int TWELVE = 12;
    private static final int TWENTY_FOUR = 24;

    TimeType(String name) {
        this.name = name;
    }

    public abstract int computeNewHour(int baseHour, int newHour);

    int convertOverValue(int value) {
        if (value >= TWENTY_FOUR) {
            return value % TWENTY_FOUR;
        }

        return value;
    }

    public static TimeType of(String timeTypeName) {
        for (TimeType timeType : TimeType.values()) {
            if (timeType.name.equalsIgnoreCase(timeTypeName)) {
                return timeType;
            }
        }

        throw new IllegalArgumentException("Invalid time type.");
    }
}
