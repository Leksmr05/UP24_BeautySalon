package PR3;

public class Day {
    private int day;
    public Day(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Некорректный день.");
        }
        this.day = day;
    }
    public int getDay() {
        return day;
    }
}
