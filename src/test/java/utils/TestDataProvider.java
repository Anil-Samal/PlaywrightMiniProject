package utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name="loginData")
    public Object[][] loginData(){

        ExcelReader excel =
                new ExcelReader("LoginData.xlsx");

        int rows = excel.getRowCount("Login");

        int cols = excel.getColumnCount("Login");

        Object[][] data = new Object[rows][cols];

        for(int i=1;i<=rows;i++){

            for(int j=0;j<cols;j++){

                data[i-1][j]=excel.getCellData("Login",i,j);

            }

        }

        return data;

    }

}