// Дана json строка вида:
// String json = "[{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"}," +
// "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"}," +
// "{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}]";
// Задача написать метод(ы), который распарсит строку и выдаст ответ вида:
// Студент Иванов получил 5 по предмету Математика.
// Студент Петрова получил 4 по предмету Информатика.
// Студент Краснов получил 5 по предмету Физика.
// Используйте StringBuilder для подготовки ответа
// Далее создайте метод, который запишет результат работы в файл. Обработайте исключения и запишите ошибки в лог файл с помощью Logger.
// *Получить исходную json строку из файла, используя FileReader или Scanner
// *Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.

import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.Scanner;

public class exercise1 {
    public static void main(String[] args) throws FileNotFoundException {

        // String json = "[{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"},\n" +
        //  "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"},\n" +
        //  "{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}]";

        String json = new Scanner(new File("./start.txt")).useDelimiter("\\Z").next();
        String result = changeLine(json);
        WriteToFile(result);

    }

    static String changeLine(String json) {

        StringBuilder newjson = new StringBuilder();
        newjson.append(json);
        newjson = replaceAll(newjson, "[^а-яА-Я0-9\n]", "");
        newjson = replaceAll(newjson, "фамилия", "Студент ");
        newjson = replaceAll(newjson, "оценка", " получил ");
        newjson = replaceAll(newjson, "предмет", " по предмету ");
        String newjson2 = newjson.toString();

        // json = json.replaceAll("[^а-яА-Я0-9\n]","");
        // json = json.replaceAll("фамилия","Студент ");
        // json = json.replaceAll("оценка"," получил ");
        // json = json.replaceAll("предмет"," по предмету ");

        return newjson2;

    }

    public static StringBuilder replaceAll(StringBuilder sb, String find, String replace) {
        return new StringBuilder(Pattern.compile(find).matcher(sb).replaceAll(replace));
    }

    static void WriteToFile(String result) {

        Logger logger = Logger.getAnonymousLogger();
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("log.txt");
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.addHandler(fileHandler);
        try(FileWriter writer = new FileWriter("file.txt", false)) {
            writer.write(result);
            writer.flush();
        } catch (Exception e) {
            logger.severe(e.toString());
        }
        fileHandler.close();

    }
}