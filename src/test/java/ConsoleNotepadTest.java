import dao.storage.NotesStorage;
import dao.storage.Storage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConsoleNotepadTest {

    @Test
    void ShowNotesTest() {
        NotesStorage ns = new NotesStorage();
        ns.showNotes();
        ns.createNote("Моя первая заметка");
        ns.createNote("Моя вторая заметка");
        ns.createNote("Моя третья заметка");
        ns.createNote("Моя четвертая заметка");
        System.out.println();
        ns.showNotes();
        System.out.println();
        ns.deleteNote(1);
        ns.showNotes();
        System.out.println();
        ns.createNote("Моя первая заметка");
        ns.showInRange(2,4);
    }

    @Test
    void jsonReadWriteTest() throws IOException {
        NotesStorage ns = new NotesStorage();
        ns.createNote("Моя первая заметка");
        ns.createNote("Моя вторая заметка");
        Path test1Path = Files.createTempFile("test1",".txt");
        String testString1Path = test1Path.toAbsolutePath().toString();
        ns.writeToFile(testString1Path);
        Storage ns1 = new NotesStorage();
        ns1.loadFromFile(testString1Path);
        ns1.showNotes();
    }


}
