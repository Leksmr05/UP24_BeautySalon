package PR3;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.DayOfWeek;
public class Year {
    private int year;
    private Month month;
    private Day day;
    public Year(int year, Month month, Day day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public void setDate(int year, Month month, Day day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public String getDayOfWeek() {
        LocalDate date = LocalDate.of(year, month.getMonth(), day.getDay());
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.toString();  // Вернет день недели на английском (например, "MONDAY")
    }
    public static long calculateDaysBetween(Year start, Year end) {
        LocalDate startDate = LocalDate.of(start.year, start.month.getMonth(), start.day.getDay());
        LocalDate endDate = LocalDate.of(end.year, end.month.getMonth(), end.day.getDay());
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
    public static long calculateMonthsBetween(Year start, Year end) {
        LocalDate startDate = LocalDate.of(start.year, start.month.getMonth(), start.day.getDay());
        LocalDate endDate = LocalDate.of(end.year, end.month.getMonth(), end.day.getDay());
        return ChronoUnit.MONTHS.between(startDate, endDate);
    }
}


