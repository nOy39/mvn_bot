import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;

public class Test {
    public static void main(String[] args) {
        LocalDateTime ldt = LocalDateTime.now();

        System.out.println(ldt);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
        System.out.println(formatter.format(ldt));
    }
}
