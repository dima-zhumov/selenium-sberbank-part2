import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Autotest {
    public static WebDriver webDriver = null;

    @BeforeClass
    public static void TestUp(){
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    @AfterClass
    public static void CleanUp() {
        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void Sberbank() throws InterruptedException{
        webDriver.get("http://www.sberbank.ru/ru/person");

        WebDriverWait wait = new WebDriverWait(webDriver,30);

        webDriver.findElement(By.xpath("//span[contains(text(),'Страхование')]")).click();
        webDriver.findElement(By.xpath("//a[contains(@class,'lg-menu__sub-link') and contains(text(),'путешественников')]")).click();

        WebElement element = webDriver.findElement(By.xpath("//div[contains(@class,'xs-bottom')]//h2"));
        wait.until(ExpectedConditions.visibilityOf(element));

        webDriver.findElement(By.xpath("//b[contains(text(),'онлайн')]")).click();
        webDriver.findElement(By.xpath("//h3[text()='Минимальная']")).click();
        element=webDriver.findElement(By.xpath("//button[text()='Оформить']"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(element).build().perform();
        element.click();


        webDriver.findElement(By.xpath("//input[@placeholder='Surname']"))
                .sendKeys("Surname");
        webDriver.findElement(By.xpath("//input[@placeholder='Name']"))
                .sendKeys("Name");
        webDriver.findElement(By.xpath("//input[contains(@id,'birthDate_')]"))
                .sendKeys("09.10.1996");

        webDriver.findElement(By.xpath("//input[@id='person_lastName']"))
                .sendKeys("Фамилия");
        webDriver.findElement(By.xpath("//input[@id='person_firstName']"))
                .sendKeys("Имя");
        webDriver.findElement(By.xpath("//input[@id='person_middleName']"))
                .sendKeys("Отчество");
        webDriver.findElement(By.xpath("//input[@id='person_birthDate']"))
                .sendKeys("09.10.1996");

        webDriver.findElement(By.xpath("//input[@id='passportSeries']"))
                .sendKeys("1111");
        webDriver.findElement(By.xpath("//input[@id='passportNumber']"))
                .sendKeys("111111");
        webDriver.findElement(By.xpath("//input[@id='documentDate']"))
                .sendKeys("09.10.2016");
        webDriver.findElement(By.xpath("//input[@id='documentIssue']"))
                .sendKeys("Кем выдан");

        webDriver.findElement(By.xpath("//button[contains(text(),'Продолжить')]")).click();

        element = webDriver.findElement(By.xpath("//div[contains(@class,'error')]"));
        String title = element.getText();
        Assert.assertEquals("Сообщение об ошибке не появилось",title,"При заполнении данных произошла ошибка");


    }
}
