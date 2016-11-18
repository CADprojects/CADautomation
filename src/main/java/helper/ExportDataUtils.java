package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 16.11.2016.
 */
public class ExportDataUtils {


    public List<String> getInfoFromRequiredGrid(String locatorName, WebDriver driver) {
        List<WebElement> rows = driver.findElements(get(locatorName));
        List<String> infoFromUIGrid = new ArrayList<>();
        for (WebElement row: rows) {
            infoFromUIGrid.add(row.getText().replaceAll("[%$,]",""));
        }
        return infoFromUIGrid;
    }

    public List<String> getInfoFromExcelFile(String exportPath) {
        File folder = new File(exportPath);
        List<String> infoFromExcelFile = new ArrayList<>();
        String excelLine;
        try (FileReader fileReader = new FileReader(exportPath+ folder.list()[0])) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((excelLine = bufferedReader.readLine()) != null) {
                excelLine = excelLine.replaceAll(",", " ").trim();
                infoFromExcelFile.add(excelLine);
            }
            bufferedReader.close();
        } catch (IOException ex) {
            System.out.println("File nor found " + ex.getMessage());
        }
        infoFromExcelFile.remove(0);
        return infoFromExcelFile;
    }

    public boolean compareExcelAndUI(List<String> uiData, List<String> excelData) {
        int numberOfMismatchedRows = 0;
        if (uiData.size() == excelData.size()) {
            for (int i = 0; i < uiData.size(); i++) {
                if (uiData.get(i).contentEquals(excelData.get(i))) {
                } else {
                    System.out.println(numberOfMismatchedRows);
                    numberOfMismatchedRows++;
                }
            }
            if (numberOfMismatchedRows == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
