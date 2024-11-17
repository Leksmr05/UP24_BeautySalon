package PR5;
public class PunctuationCounter {
    public static void main(String[] args) {
        String text = "Пример текста: здесь есть запятые, точки. И другие знаки препинания!";
        int punctuationCount = countPunctuationMarks(text);
        System.out.println("Количество знаков препинания: " + punctuationCount);
    }
    public static int countPunctuationMarks(String text) {
        String punctuationMarks = ".,!?;:()-\"'";
        int count = 0;
        for (char c : text.toCharArray()) {
            if (punctuationMarks.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }
}

