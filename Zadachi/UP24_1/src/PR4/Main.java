package PR4;

import java.time.LocalDate;
public class Main {
    public static void main(String[] args) {
        Notepad notepad = new Notepad();
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        notepad.addEntry(today, "Покупки", "Купить чехол, тетради, зарядку");
        notepad.addEntry(today, "Учеба", "Закончить отчет по практике");
        notepad.addEntry(yesterday, "Спорт", "Сходить в спортзал");
        notepad.printEntriesByDate(today);
        notepad.printEntriesByDate(yesterday);
    }
}

