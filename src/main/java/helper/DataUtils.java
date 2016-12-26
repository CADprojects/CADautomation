package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 16.11.2016.
 */
public class DataUtils {

    public static List<List<String>> getInfoFromRequiredGrid(String locatorName, WebDriver driver, String regex) {
        List<WebElement> rows = driver.findElements(get(locatorName));
        List<List<String>> infoFromUIGrid = new ArrayList<>();
        for (WebElement row: rows) {
            List<String> cellsData = new ArrayList<>();
            List<WebElement> cells = row.findElements(get(locatorName+ "Cells"));
            for (WebElement cell: cells) {
                cellsData.add(cell.getText().replaceAll(regex,""));
            }
            infoFromUIGrid.add(cellsData);
        }
        return infoFromUIGrid;
    }

    public static List<List<String>> getInfoFromExcelFile(String exportPath) {
        File folder = new File(exportPath);
        List<List<String>> infoFromExcelFile = new ArrayList<>();
        String excelLine;
        try (FileReader fileReader = new FileReader(exportPath+ folder.list()[0])) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((excelLine = bufferedReader.readLine()) != null) {
                List<String> excelLineCells = new ArrayList<>(Arrays.asList(excelLine.split(",", -1)));
                excelLineCells.remove(excelLineCells.size()-1);
                infoFromExcelFile.add(excelLineCells);
            }
            bufferedReader.close();
        } catch (IOException ex) {
            System.out.println("File not found " + ex.getMessage());
        }
        infoFromExcelFile.remove(0);
        return infoFromExcelFile;
    }

    public static boolean compareData(List<List<String>> uiData, List<List<String>> dbOrExcelData) {
        int numberOfMismatchedRows = 0;
        if (uiData.size() == dbOrExcelData.size()) {
            for (int i = 0; i < uiData.size(); i++) {
                if (uiData.get(i).size() == dbOrExcelData.get(i).size()) {
                    for (int j = 0; j < uiData.get(i).size(); j++) {
                        if (uiData.get(i).get(j).contentEquals(dbOrExcelData.get(i).get(j))) {
                        } else {
                            numberOfMismatchedRows++;
                        }
                    }
                } else return false;
            }
            return numberOfMismatchedRows == 0;
        } else {
            return false;
        }
    }
}
