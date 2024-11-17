package PR3;

public class Month {
    private int month;
    public Month(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Некорректный месяц.");
        }
        this.month = month;
    }
    public int getMonth() {
        return month;
    }
}

