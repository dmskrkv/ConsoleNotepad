package dao.storage;

import java.io.IOException;

public interface Storage {
    Storage loadFromFile(String path) throws IOException;
    void createNote(String text);
    void deleteNote(int index);
    void showNotes();
    void showInRange(int a, int b);
    void writeToFile(String path);
}
