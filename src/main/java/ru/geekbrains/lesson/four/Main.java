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
    //region Вывод игрового поля
    static void printField (){
        System.out.print("+");
        for (int i = 0; i < fieldSizeX * 2 + 1; i++){
            if (i % 2 == 0){
                System.out.print("-");
            } else {
                System.out.print(i / 2 + 1);
            }
        }
        System.out.println();

        for (int y = 0; y < fieldSizeY; y++){
            System.out.print(y + 1 + "|");
            for (int x = 0; x < fieldSizeX; x++){
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }
        for (int i = 0; i <= fieldSizeX * 2 + 1; i++){
            System.out.print("-");
        }
        System.out.println();
    }
    //endregion
    //region Логика хода игрока
    static void humanMove (){
        int x, y;
        do {
            System.out.println("Укажите координаты хода по горизонтали (X) \n " +
                    "и вертикали (Y) от 1 до 3 через пробел");
            x = H_CHOICE.nextInt() - 1;
            y = H_CHOICE.nextInt() - 1;
        }
        while(!isCellValid(x, y) || !isCellEmpty(x, y));
        field[y][x] = HUMAN_SIGN;
    }
    //endregion
    //region логика хода компьютера
    static void aiMove (){
        int x, y;
        do{
            x = C_CHOICE.nextInt(fieldSizeX);
            y = C_CHOICE.nextInt(fieldSizeY);
        }
        while (!isCellEmpty(x, y));
        field[y][x] = CPU_SIGN;
    }
    //endregion
    //region Проверка допустимости хода
    static boolean isCellValid (int x, int y){
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }
    //endregion
    //region Проверка на "занятость" клетки
    static boolean isCellEmpty (int x, int y){
        return field[y][x] == EMPTY_SIGN;
    }
    //endregion
    //region Условия победы
    static boolean isWin(char c){
        for (int i = 0; i < fieldSizeX; i++) {
            if ((field[i][0] = field[i][1] = field[i][2]) == c || (field[0][i] == c && field[1][i] == c && field[2][i] == c)) {
                return true;
            }
            if ((field[0][0] == c && field[1][1] == c && field[2][2] == c) || (field[0][2] == c && field[1][1] == c && field[2][0] == c)) {
                return true;
            }
        }
        return false;
    }
    //endregion
    //region Условия ничьей
    static boolean isDraw(){
        for (int y = 0; y < fieldSizeY; y++){
            for (int x = 0; x < fieldSizeX; x++){
                if (isCellEmpty(x, y))
                    return false;
            }
        }
        return true;
    }
    //endregion
    //region Статус игры
    static boolean gameStatus (char c, String w){
        if (isWin(c)){
            System.out.println(w);
            return true;
        }
        if (isDraw()){
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }
    //endregion

    public static void main(String[] args) {
        while (true) {
            createField();
            printField();
            while (true) {
                humanMove();
                printField();
                if (gameStatus(HUMAN_SIGN, "Вы победили!"))
                    break;
                aiMove();
                printField();
                if (gameStatus(CPU_SIGN, "Победил компьютер!"))
                    break;
            }
            System.out.println("Еще партию? (1 - да): ");
            if (!H_CHOICE.next().equalsIgnoreCase("1")) {
                break;
            }
        }
    }
}
