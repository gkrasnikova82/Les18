package com.company;
//ДЗ 11. Кассовый чек
//Дан текстовый файл определенной структуры, в котором содержится информация о покупках.

//Формат файла:
//Название товара
//количество
//цена

//Необходимо написать программу, которая выведет на экран чек, сформированный из этого файла. В чеке должна быть информация:
//название товара:  цена Х кол-во = стоимость
//В конце отчета вывести итоговую стоимость.
//(Пример входного файла и вывода прикрепляю к задаче)

import java.io.*;

public class Main {


    public static void main(String[] args) {
        try {
            File file = new File("C://SomeDir//products.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            System.out.println("Наименование        Кол-во   Цена     Стоимость\n" +
                    "===============================================");
            double AllAmount = 0.0;//общая сумма
            double Amount = 0.0;//для вычесления суммы
            int Flag = 1;
            String line = reader.readLine();
            String lineOut = line;//формируемая строка для вывода
            while (line != null) {
                if (Flag == 1){
                    lineOut = String.format("%-20s", lineOut);
                    line = reader.readLine();
                    Flag++;
                }
                if (Flag == 2){
                    Amount = Double.valueOf(line);
                    //lineOut = lineOut + line + " X ";
                    lineOut = lineOut + String.format("%-6s", line) + " X ";
                    line = reader.readLine();
                    Flag++;

                }
                if (Flag == 3){
                    Amount = Amount * Double.valueOf(line);//стоимость конкретного продукта
                    AllAmount = AllAmount + Amount;
                    //lineOut = lineOut + line + " = " + String.format("%.2f", Amount);
                    lineOut = lineOut + String.format("%6s", line) + " = " + String.format("%.2f", Amount);
                    System.out.println(lineOut);
                    line = reader.readLine();//считывание строки с названием продукта
                    Flag = 1;
                    lineOut = line;
                }
            }
            reader.close();//закрываем считывание
            System.out.println("===============================================");
            System.out.printf("Итого:                              =%8.2f\n", AllAmount);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}