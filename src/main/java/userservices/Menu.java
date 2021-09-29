package userservices;

import dao.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class Menu {
    String path;
    Storage storage;
    public final Scanner scanner;

    public Menu() {
        scanner = new Scanner(System.in);
    }

    Storage openStorage() throws IOException {
        System.out.println("Введите путь к файлу-хранилищу.");
        path = scanner.nextLine();
        return storage.loadFromFile(path);
    }

    void showNotes(Storage storage) {
        storage.showNotes();
    }

    void workWithStorage() throws IOException {
        this.storage = openStorage();
        showNotes(storage);
        int choice;
        do {
            System.out.println();
            System.out.println("Выберите действие:\n" +
                    "1. Создать заметку;\n" +
                    "2. Удалить заметку;\n" +
                    "3. Показать заметки в диапазоне.\n" +
                    "4. Закрыть текущую БД;\n" +
                    "5. Завершить работу;");
            choice = scanner.nextInt();
            /*Примечание. Метод nextInt() класса Scanner сканирует следующее целое число,
            при этом, поскольку вводя в консоли число пользователь нажимает Enter, в памяти
            остается несчитанным символ переноса строки, который при попытке последующего
            использования метода nextLine() считает строку до конца, т.е. символ переноса строки.
            Чтобы этого избежать, командой scanner.nextLine() мы заставляем сканер принудительно
            перейти на следующую строку.*/
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Введите текст заметки:");
                    storage.createNote(scanner.nextLine());
                    System.out.println("Заметка создана!");
                    break;
                case 2:
                    System.out.println("Введите номер заметки для удаления:");
                    storage.deleteNote(scanner.nextInt());
                    scanner.nextLine();
                    System.out.println("Заметка удалена!");
                    break;
                case 3:
                    System.out.println("Введите начало диапазона:");
                    int a = scanner.nextInt();
                    System.out.println("Введите конец диапазона:");
                    int b = scanner.nextInt();
                    scanner.nextLine();
                    storage.showInRange(a, b);
                    break;
                case 4:
                    System.out.println("Закрытие текущей базы данных.");
                    System.out.println();
                    storage.writeToFile(path);
                    openStorage();
                    showNotes(storage);
                case 5:
                    System.out.println("Завершение работы...");
                    break;
            }
        } while (choice != 5);
    }
}
