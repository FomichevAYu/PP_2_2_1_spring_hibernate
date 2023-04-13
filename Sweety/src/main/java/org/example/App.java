package org.example;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;



public class App {
    public static void main(String[] args) {
        //читаем файлы
        try (FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\FomichevAYu\\Desktop\\DlyaDelov\\test1.xlsx"));
             FileInputStream fileInputStream2 = new FileInputStream(new File("C:\\Users\\FomichevAYu\\Desktop\\DlyaDelov\\test2.xlsx"));
             FileInputStream fileInputStream3 = new FileInputStream(new File("C:\\Users\\FomichevAYu\\Desktop\\DlyaDelov\\test3.xlsx"))) {
            //создаем модели excel таблиц
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFWorkbook workbook2 = new XSSFWorkbook(fileInputStream2);
            XSSFWorkbook workbook3 = new XSSFWorkbook(fileInputStream3);
            //цепляем листы по индексу
            StringArrCreator stringArrCreator = new StringArrCreator();
            stringArrCreator.setCell(6);
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFSheet sheet2 = workbook2.getSheetAt(3);
            XSSFSheet sheet3 = workbook2.getSheetAt(0);
            stringArrCreator.setRow(sheet);
            //создаем двумерный массив, прекрасное решение
            stringArrCreator.setArr(stringArrCreator.getRow(), stringArrCreator.getCell());
            String[][] arr = stringArrCreator.getArr();
            //двойным циклом проходимся по искомой странице и заполняем массив
            for (int i = 0; i < (sheet.getLastRowNum() + 1); i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < 6; j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    arr[i][j] = cell.toString();
                }
            }
            //двойным циклом пероходимся по заполняемому файлу и заполняем его из массива
            for (int r = 0; r < arr.length; r++) {
                Row row2 = sheet2.getRow(r+1);
                if (row2 == null) {
                    row2 = sheet2.createRow(r+1);
                }
                for (int i = 0; i < 5; i++) {
                    String value = arr[r][i];
                    XSSFCell cell2 = (XSSFCell) row2.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell2.setCellValue(value);
                }
            }
            //записываем изменения в файл
            workbook.write(new FileOutputStream(new File("C:\\Users\\FomichevAYu\\Desktop\\DlyaDelov\\test1.xlsx")));
            workbook.close();
            workbook2.write(new FileOutputStream(new File("C:\\Users\\FomichevAYu\\Desktop\\DlyaDelov\\test2.xlsx")));
            workbook2.close();

        } catch (IOException ex) {
            System.out.println("Исключение");
        }
    }

}





