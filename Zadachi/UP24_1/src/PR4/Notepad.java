package PR4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Notepad {
    private Map<LocalDate, List<Entry>> entriesByDate;
    public Notepad() {
        entriesByDate = new HashMap<>();
    }
    public class Entry {
        private String title;
        private String content;
        public Entry(String title, String content) {
            this.title = title;
            this.content = content;
        }
        public String getTitle() {
            return title;
        }
        public String getContent() {
            return content;
        }
        @Override
        public String toString() {
            return "Запись: " + title + "\nСодержание: " + content;
        }
    }
    public void addEntry(LocalDate date, String title, String content) {
        Entry newEntry = new Entry(title, content);
        entriesByDate.computeIfAbsent(date, k -> new ArrayList<>()).add(newEntry);
    }
    public List<Entry> getEntriesByDate(LocalDate date) {
        return entriesByDate.getOrDefault(date, new ArrayList<>());
    }
    public void printEntriesByDate(LocalDate date) {
        List<Entry> entries = getEntriesByDate(date);
        if (entries.isEmpty()) {
            System.out.println("На " + date + " нет записей.");
        } else {
            System.out.println("Записи на " + date + ":");
            for (Entry entry : entries) {
                System.out.println(entry);
            }
        }
    }
}

