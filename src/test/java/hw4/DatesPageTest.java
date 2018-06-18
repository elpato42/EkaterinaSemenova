package hw4;

import hw4.pageobjects.Dates;
import hw4.pageobjects.HomePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.page;
import static hw4.enums.User.PITER_CHAILOVSKII;

/**
 * Created by Ekaterina on 01.06.2018.
 */
public class DatesPageTest extends ServiceSuiteBase {
    private Dates datesPage;
    private HomePage homePage;

    @DataProvider
    public Object[][] information() {
        return new Object[][]{
                {0, 100},
                {0, 0},
                {100, 100},
                {30, 70}
        };
    }

    @BeforeClass(alwaysRun = true)
    public void login() {
        homePage = page(HomePage.class);
        //1 Open test site by URL
        homePage.open();

        //2 Assert Browser title
        homePage.checkTitle();

        //3 Perform login
        homePage.login(PITER_CHAILOVSKII.login, PITER_CHAILOVSKII.password);

        //4 Open through the header menu Service -> Dates Page
        homePage.openService();
        homePage.openDatesPage();
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        //7 Close browser
        close();
    }


    @Test(dataProvider = "information")
    public void sliderTest(int from, int to) {


        datesPage = page(Dates.class);
        //5 Range sliders
        datesPage.setHandles(from, to);

        //6 Assert that for "From" and "To" sliders there are logs rows with corresponding values
        datesPage.checkLogsValues(from, to);
    }
}
