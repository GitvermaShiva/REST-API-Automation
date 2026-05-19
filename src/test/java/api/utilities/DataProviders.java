package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "UserDataFromExcel")
    public String[][] getUserDataFromExcel() throws Exception {

        String excelPath = "./testData/userData.xlsx";     // location of Excel
        String sheetName = "Sheet1";

        int rowCount = ExcelUtility.getRowCount(excelPath, sheetName);
        int colCount = ExcelUtility.getColumnCount(excelPath, sheetName);

        String[][] data = new String[rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = ExcelUtility.getCellData(excelPath, sheetName, i, j);
            }
        }

        return data;
    }

}
