package PR6;

public class Day {
    private int day;
    public Day(int day, Month month, int year) {
        if (day < 1 || day > getDaysInMonth(month.getMonth(), year)) {
            throw new IllegalArgumentException("Некорректный день.");
        }
        this.day = day;
    }
    public int getDay() {
        return day;
    }
    private int getDaysInMonth(int month, int year) {
        switch (month) {
            case 2:
                return isLeapYear(year) ? 29 : 28;
            case 4: case 6: case 9: case 11:
                return 30;
            default:
                return 31;
        }
    }
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
