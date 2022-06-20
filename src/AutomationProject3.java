import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutomationProject3 {


    public static void main(String[] args) throws InterruptedException {


        System.setProperty("webdriver.chrome.driver", "/Users/mervegurleyen/Desktop/BrowserDrivers/chromedriver-3");


        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));

//        Navigate to cars.com.

        driver.get("https://www.cars.com/");

//        Verify the default selected options for the dropdowns are the following:

        WebElement newUsed = driver.findElement(By.id("make-model-search-stocktype"));

        Select select = new Select(newUsed);

        WebElement firstSelectedOption = select.getFirstSelectedOption();

        Assert.assertEquals(firstSelectedOption.getText(), "Certified cars");

        Thread.sleep(2000);

//        In the New/used dropdown box, verify that the expected values are the following
        List<WebElement> options = select.getOptions();

        List<String> newUsed1 = new ArrayList<>();

        for (WebElement option : options) {
            newUsed1.add(option.getText());

        }

//        Choose Used Cars from New/used dropdown
        select.selectByValue("used");

//        Choose Tesla from Make dropdown

        new Select(driver.findElement(By.id("makes"))).selectByVisibleText("Tesla");

//        Verify Models dropdown contains the following:

        Select select1 = new Select(driver.findElement(By.id("models")));

        List<WebElement> options1 = select1.getOptions();

        List<String> TeslaModels = new ArrayList<>();

        for (WebElement webElement : options1) {
            TeslaModels.add(webElement.getText());
//            System.out.println(TeslaModels);
        }

//        Choose Model S from the dropdown

        select1.selectByValue("tesla-model_s");

//        Choose $100,000 from Price dropdown

        new Select(driver.findElement(By.id("make-model-max-price"))).selectByValue("100000");

//        Choose 50 miles from Distance dropdown

        new Select(driver.findElement(By.id("make-model-maximum-distance"))).selectByValue("50");

        Thread.sleep(2000);

//        Enter 22182 for ZIP and click Search

        driver.findElement(By.id("make-model-zip")).clear();

        driver.findElement(By.id("make-model-zip")).sendKeys("22182" + Keys.ENTER);

        Thread.sleep(2000);

//        In the next Search Results Page, verify that there are 19 results on the page and each search result title contains “Tesla Model S”
        List<WebElement> elements = driver.findElements(By.xpath("// a[@class = 'vehicle-card-link js-gallery-click-link']"));

        Assert.assertEquals(20, elements.size());

        Thread.sleep(2000);

//Choose Lowest Price from Sort by dropdown and verify that all the results are sorted in ascending order of the price:
        WebElement element1 = driver.findElement(By.id("sort-dropdown"));
        Select selection = new Select(element1);
        selection.selectByValue("list_price");

        Thread.sleep(2000);

//        Choose Highest Mileage from Sort by dropdown and verify that all the results are sorted in descending order
//        of the mileage:
        List<WebElement> elements1 = driver.findElements(By.xpath("//span[@class='primary-price']"));

        double priceSelected = 0.0;
        for (WebElement count : elements1) {
            Assert.assertTrue(Double.parseDouble(count.getText().replaceAll("[$,]", "")) >= priceSelected);
            priceSelected = Double.parseDouble(count.getText().replaceAll("[$,]", ""));
        }

        selection.selectByValue("mileage_desc");

        Thread.sleep(2000);

//        Choose Nearest location from Sort by dropdown and verify that all the results are sorted in ascending order
//        of the proximity to the current zip (exclude online sellers)
        List<WebElement> elements2 = driver.findElements(By.xpath("//div[@class='mileage'][contains(text(),' mi.')]"));

        long milesSelected = 1000000000;
        for (WebElement webElement : elements2) {
            Assert.assertTrue(Long.parseLong(webElement.getText().replaceAll("[$,mi. ]", "")) < milesSelected);
            milesSelected = (Long.parseLong(webElement.getText().replaceAll("[$,mi. ]", "")));

        }

        selection.selectByValue("distance");

        Thread.sleep(2000);


        List<WebElement> elements3 = driver.findElements(By.xpath("//div[@data-qa='miles-from-user']"));

        int zipSelected = 0;
        for (WebElement webElement : elements3) {
            Assert.assertTrue(Integer.parseInt(webElement.getText().substring(0, 4).replaceAll("[$,mi. ]", "")) >= zipSelected);
            zipSelected = (Integer.parseInt(webElement.getText().substring(0, 4).replaceAll("[$,mi. ]", "")));
//
        }


//        Choose Oldest year from Sort by dropdown and verify that all the results are sorted in ascending order of the year

        selection.selectByValue("year");
//
        Thread.sleep(2000);

//
        List<WebElement> elements4 = driver.findElements(By.xpath("//h2[@class='title'] "));

        int yearSelected = 0;
        for (WebElement webElement : elements4) {

            Assert.assertTrue(Integer.parseInt(webElement.getText().substring(0, 5).replaceAll("[$,mi. ]", "")) >= yearSelected);
            yearSelected = (Integer.parseInt(webElement.getText().substring(0, 5).replaceAll("[$,mi. ]", "")));


        }
//        Close the browser
        driver.close();

    }
}
