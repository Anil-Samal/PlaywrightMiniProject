package utils;

import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    private Workbook workbook;

    public ExcelReader(String fileName) {

        try {

            InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream("testdata/" + fileName);

            if (is == null) {
                throw new RuntimeException("Excel file not found : " + fileName);
            }

            workbook = new XSSFWorkbook(is);

        } catch (Exception e) {

            throw new RuntimeException("Unable to load Excel file : " + fileName, e);

        }
    }

    public String getCellData(String sheetName, int row, int column) {

        Sheet sheet = workbook.getSheet(sheetName);

        Row excelRow = sheet.getRow(row);

        Cell cell = excelRow.getCell(column);

        return cell.toString();
    }

}