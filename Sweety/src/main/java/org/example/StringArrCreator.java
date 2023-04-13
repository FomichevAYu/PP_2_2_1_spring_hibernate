package org.example;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public class StringArrCreator {
    private String [] [] arr;

    private int row = 0;

    private int cell;


    public void setRow (XSSFSheet sheet) {
        this.row+=sheet.getLastRowNum()+1;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public int getRow() {
        return row;
    }

    public int getCell() {
        return cell;
    }

    public void setArr (int row, int cell) {
        String [][] arr = new String[row][cell];
        this.arr = arr;
    }

    public String[][] getArr() {
        return arr;
    }
}
