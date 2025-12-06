package api.utilities;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;

public class ExcelUtility {

    public static FileInputStream fis;
    public static XSSFWorkbook workbook;
    public static XSSFSheet sheet;

    public static int getRowCount(String file, String sheetName) throws Exception {
        fis = new FileInputStream(file);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        int rowcount = sheet.getLastRowNum();
        workbook.close();
        fis.close();
        return rowcount;
    }

    public static int getColumnCount(String file, String sheetName) throws Exception {
        fis = new FileInputStream(file);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        int colCount = sheet.getRow(0).getLastCellNum();
        workbook.close();
        fis.close();
        return colCount;
    }

    public static String getCellData(String file, String sheetName, int row, int col) throws Exception {

        fis = new FileInputStream(file);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);

        XSSFCell cell = sheet.getRow(row).getCell(col);

        String data;

        if (cell.getCellType() == CellType.STRING)
            data = cell.getStringCellValue();

        else if (cell.getCellType() == CellType.NUMERIC)
            data = String.valueOf((long) cell.getNumericCellValue());

        else
            data = "";

        workbook.close();
        fis.close();

        return data;
    }
}
