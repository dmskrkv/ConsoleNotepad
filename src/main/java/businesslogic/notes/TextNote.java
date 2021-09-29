package businesslogic.notes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TextNote implements Note {
    private final String dateOfCreation;
    private String text;

    public TextNote() {
        LocalDate ld = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        dateOfCreation = ld.format(formatter);
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
