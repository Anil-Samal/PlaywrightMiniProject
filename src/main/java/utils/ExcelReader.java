package utils;

import java.io.InputStream;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    private Workbook workbook;

    public ExcelReader(String fileName) {

        try {

            InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream("testdata/" + fileName);

            workbook = new XSSFWorkbook(is);

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

    public int getRowCount(String sheetName) {

        return workbook
                .getSheet(sheetName)
                .getLastRowNum();

    }

    public int getColumnCount(String sheetName) {

        return workbook
                .getSheet(sheetName)
                .getRow(0)
                .getLastCellNum();

    }

    public String getCellData(String sheetName,int row,int col){

        return workbook
                .getSheet(sheetName)
                .getRow(row)
                .getCell(col)
                .toString();

    }

}