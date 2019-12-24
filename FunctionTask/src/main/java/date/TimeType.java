package date;

public enum TimeType {

    AM("AM") {
        @Override
        public int computeNewHour(int baseHour, int newHour) {
            if (baseHour >= 12) {
                newHour -= 12;
            }

            return convertOverValue(newHour);
        }
    },
    PM("PM") {
        @Override
        public int computeNewHour(int baseHour, int newHour) {
            newHour += 12;
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

    TimeType(String name) {
        this.name = name;
    }

    public abstract int computeNewHour(int baseHour, int newHour);

    int convertOverValue(int value) {
        if (value >= 24) {
            return value % 24;
        }

        return value;
    }

    public static TimeType of(String timeTypeName) {
        for (TimeType timeType : TimeType.values()) {
            if (timeType.name.equalsIgnoreCase(timeTypeName)) {
                return timeType;
            }
        }

        throw new IllegalArgumentException("매개변수 정보가 잘못되었습니다");
    }
}
