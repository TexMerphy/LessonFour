package ru.geekbrains.lesson.four;

import java.util.Random;
import java.util.Scanner;

public class Main {
    //region Инициализация
    static char[][] field;                                      // Игровое поле
    static int fieldSizeX;                                      // Ширина игрового поля
    static int fieldSizeY;                                      // Высота игрового поля
    static final char HUMAN_SIGN = 'X';                         // Знак пользователя
    static final char CPU_SIGN = '0';                           // Знак компьютера
    static final char EMPTY_SIGN = '-';                         // Пустое поле
    static final Scanner H_CHOICE = new Scanner(System.in);     // Ввод ходов пользователя
    static final Random C_CHOICE = new Random();                // Генератор ходов компьютера
    //endregion
    //region Постороение поля
    static void createField(){
       fieldSizeX = 3; // задаем размер поля по ширине
       fieldSizeY = 3; // задаем размер поля по высоте
        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeX; y++){
            for (int x = 0; x < fieldSizeX; x++){
                field[y][x] = EMPTY_SIGN;
            }
        }
    }
    //endregion
}
