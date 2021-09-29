package dao.storage;

import businesslogic.notes.Note;
import businesslogic.notes.TextNote;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Component
public class NotesStorage implements Storage {
    private List<Note> noteList;
    JsonReaderAndWriter jsrw;

    public NotesStorage() {
        noteList = new LinkedList<>();
        jsrw = new JsonReaderAndWriter();
    }

    @Override
    public Storage loadFromFile(String path) throws IOException {
        noteList = jsrw.readFromFile(path);
        return this;
    }

    @Override
    public void writeToFile(String path) {
        jsrw.writeToFile(noteList, path);
    }

    @Override
    public void createNote(String text) {
        TextNote note = new TextNote();
        note.setText(text);
        noteList.add(note);
    }

    @Override
    public void deleteNote(int index) {
        noteList.remove(index - 1);
    }

    @Override
    public void showNotes() {
        System.out.println("----------------------------------------");
        if (noteList.isEmpty()) {
            System.out.println("Заметки отсутствуют.");
        } else {
            System.out.printf("%-7s%-15s%-15s%n", "Номер", "Дата", "Заметка");
            for (int i = 0; i < noteList.size(); i++) {
                System.out.printf("%-7s%-15s%-15s%n", i + 1, noteList.get(i).getDateOfCreation(), noteList.get(i).getText());
            }
        }
        System.out.println("----------------------------------------");
    }

    @Override
    public void showInRange(int begin, int end) {
        System.out.println("----------------------------------------");
        if (noteList.isEmpty()) {
            System.out.println("Заметки отсутствуют.");
        } else if (end > noteList.size()) {
            System.out.println("Неверно указан диапозон заметок.");
        } else {
            System.out.printf("%-7s%-15s%-15s%n", "Номер", "Дата", "Заметка");
            for (int i = begin; i <= end; i++) {
                System.out.printf("%-7s%-15s%-15s%n", i, noteList.get(i - 1).getDateOfCreation(), noteList.get(i - 1).getText());
            }
        }
        System.out.println("----------------------------------------");
    }
}
