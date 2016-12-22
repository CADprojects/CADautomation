package page.settings;

import base.PageBase;
import helper.DataUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static helper.Locators.get;

/**
 * Created by Andrei.Ostrovski on 14.11.2016.
 */
public class EarningsPage extends PageBase {

    private static final String PAGETITLE = "Earnings";
    private static final By EXPORTBUTTON = get("EarningsPage.ExportButton");
    private static final String GRIDROWS = "EarningsPage.EarningsGridBodyRows";
    private static final String REGEX = "[%$,]";


    public EarningsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isEarningsPageOpened() {
        return driver.getTitle().contentEquals(PAGETITLE);
    }

    public void exportDataToExcel() {
        driver.findElement(EXPORTBUTTON).click();
    }

    public List<List<String>> getDataFromExcel(String exportPath) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        List<List<String>> infoFromExcel = new ArrayList<>(DataUtils.getInfoFromExcelFile(exportPath));
        for (List<String> row: infoFromExcel) {
            row.set(0, row.get(0).substring(0, row.get(0).indexOf(" ")));
            try {
                row.set(0, formatter.format(formatter.parse(row.get(0))));
            } catch (ParseException ex) {
                System.out.println("Wrong date format" + ex.getMessage());
            }
            row.set(2, row.get(2).replaceAll("00", ""));
            row.set(3, row.get(3).replaceAll("00", ""));
        }
        return infoFromExcel;
    }

    public List<List<String>> getDataFromUIGrid() {
        List<List<String>> infoFromUI = new ArrayList<>(DataUtils.getInfoFromRequiredGrid(GRIDROWS, driver, REGEX));
        infoFromUI.remove(0);
        for (List<String> row: infoFromUI) {
            row.set(2, row.get(2).replaceAll("00", ""));
            row.set(3, row.get(3).replaceAll("00", ""));
        }
        return infoFromUI;
    }
}
