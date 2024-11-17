package PR3;

public class Main {
    public static void main(String[] args) {
        // Создаем объекты даты
        Month january = new Month(1);
        Day firstDay = new Day(1);
        Year year2023 = new Year(2023, january, firstDay);
        Month december = new Month(12);
        Day thirtyFirstDay = new Day(31);
        Year year2024 = new Year(2024, december, thirtyFirstDay);
        System.out.println("День недели для 1 января 2023 года: " + year2023.getDayOfWeek());
        long daysBetween = Year.calculateDaysBetween(year2023, year2024);
        System.out.println("Количество дней между 1 января 2023 и 31 декабря 2024: " + daysBetween);
        long monthsBetween = Year.calculateMonthsBetween(year2023, year2024);
        System.out.println("Количество месяцев между 1 января 2023 и 31 декабря 2024: " + monthsBetween);
    }
}

