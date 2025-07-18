package org.example;
/*
Написать код по аналогии с уроком для работы с JSON:

1) [ { "name": "1.txt", "size": 1234, "type": "txt" },
 { "name": "2.png", "size": 456, "type": "png" }, ... ]

2) { "count": 2, "files":
[ { "name": "1.txt", "size": 1234, "type": "txt" },
{ "name": "2.png", "size": 456, "type": "png" } ] }
- с функцией сравнения count с фактическим количеством файлов в массиве.
Выложить в одном проекте на GitHub.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;


class FileItem {
    String name;
    int size;
    String type;


    @Override
    public String toString() {
        return "FileItem{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", type='" + type + '\'' +
                '}';
    }
}

class FileList {
    int count;
    FileItem[] files;

    // Проверяем, соответствует ли значение поля count фактическому количеству элементов в массиве files.
    boolean isValid() {
        return count == files.length;
    }


    @Override
    public String toString() {
        return "FileList{" +
                "count=" + count +
                ", files=" + Arrays.toString(files) +
                '}';
    }
}

public class App2 {

    static final String JSON_ARRAY = """
            [
            { "name": "1.txt", "size": 1234, "type": "txt" },
            { "name": "2.png", "size": 456, "type": "png" }
            ]
            """;

    static final String JSON_WITH_COUNT = """
            {
              "count": 2,
              "files": [ 
                { "name": "1.txt", "size": 1234, "type": "txt" }, 
                { "name": "2.png", "size": 456, "type": "png" } 
                ]
            }
            """;

    public static void main(String[] args) {
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Gson gson = new GsonBuilder().create();

        //Десериализация JSON-массив(имеем в виду тип структуры внутри этой JSON-строки)
        // → Java-массив объектов
        FileItem[] fileItem1 = gson.fromJson(JSON_ARRAY, FileItem[].class);
        System.out.println("Десериализованный массив файлов:");
        for(FileItem item : fileItem1){
            System.out.println(item);
        }

        //Десериализация JSON-объект(имеем в виду тип структуры внутри этой JSON-строки)
        // → Java-объект с полями count и files
        FileList fileList = gson.fromJson(JSON_WITH_COUNT, FileList.class);
        System.out.println("Десериализованный файл-лист:\n" + fileList);

        //Сравнение количества файлов
        System.out.println("\nПроверка: count "
                + (fileList.isValid() ? "совпадает" : " не совпадает") +
                " с фактическим количестовом файлов");

        //Сериализация обратно в JSON
        String jsonArray  = gson.toJson(fileItem1);
        System.out.println("Сериализованный JSON:\n" + jsonArray);

        String jsonObject = gson.toJson(fileList);
        System.out.println("Сериализованный JSON:\n" + jsonObject);
    }

}