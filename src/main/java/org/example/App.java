package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

/*

Json (JavaScript Object Notation) — это формат обмена данными.
Json способ серелиализации десериализации JavaScript объектов
================
Сериализация — это процесс превращения объекта Java в формат,
который можно передать или сохранить
📦 Ассоциация: как упаковать чемодан, чтобы отправить его по почте:

У тебя есть объект Java (Person)
Ты сериализуешь его в JSON (строку)
Отправляешь
Получатель десериализует — распаковывает обратно в объект
=================
Термин	Значение
JSON	Текстовый формат передачи данных
Сериализация	Преобразование объекта → в JSON/текст
Десериализация	Преобразование JSON → в объект
 */
class Address {
    String country;
    String city;

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

class Person{
    String name;
    String surname;
    int age;
    String [] phones;
    Address address;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", phones=" + Arrays.toString(phones) +
                ", address=" + address +
                '}';
    }
}


public class App {
    static final String JSON = """
            {
                "name": "Vsevolod",
                "surname": "Ievgiienko",
                "phones":["3834543534","123456677"],
                "age": 38,
                "address": {
                    "country": "UA",
                    "city": "Kyiv"
            
            }
        }
    """;

    public static void main(String[] args) {
//        System.out.println("Hello World!");
        Gson gson = new GsonBuilder().create();
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //красивый gson, но весит больше
        Person person = gson.fromJson(JSON, Person.class);
        System.out.println(person);

        person.age = 15;

        String json = gson.toJson(person);//взять person, превратить в строку
        //обратное преобразование java-объект в gson
        System.out.println(json);

    }
}

/*
На servlet прилетел gson, Я его конвертирую в java-объект, и дальше с ним работаю.
Вызываю методы и другое из ООР.
 */