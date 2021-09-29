package dao.storage;

import businesslogic.notes.Note;
import businesslogic.notes.TextNote;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

@Component
public class JsonReaderAndWriter {
    ObjectMapper jm;
    public JsonReaderAndWriter() {
        jm = new JsonMapper();
    }

    public LinkedList<Note> readFromFile(String path) throws IOException {
        File json = Paths.get(path).toFile();
        return jm.readValue(json, jm.getTypeFactory().constructCollectionType(LinkedList.class, TextNote.class));
    }

    public void writeToFile(List<Note> list, String path) {
        try {
            jm.writeValue(new File(path),list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
