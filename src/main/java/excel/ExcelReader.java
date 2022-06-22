package excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ExcelReader {

    public Map<String, String> getRowData(String file, String sheetName, String row) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        int lastColumnNum = sheet.getRow(1).getLastCellNum();

        Map<String, String> data = new HashMap<>();

        for (int i = 0; i < lastColumnNum; i++){
            String key;
            String value;

            key = sheet.getRow(1).getCell(i).getStringCellValue().trim();
            value = sheet.getRow(Integer.parseInt(row)+1).getCell(i).getStringCellValue().trim();

            data.put(key,value);
        }

        return data;
    }

    public Map<String, String> getColumnData(String file, String sheetName, String column) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        int lastRowNum = sheet.getLastRowNum();

        Map<String,String> data = new HashMap<>();

        for (int i = 0; i <=lastRowNum; i++){
            String key;
            String value;

            key = sheet.getRow(i).getCell(0).getStringCellValue().trim();
            value = sheet.getRow(i).getCell(Integer.parseInt(column)).getStringCellValue().trim();

            data.put(key,value);
        }

        return data;
    }
}
