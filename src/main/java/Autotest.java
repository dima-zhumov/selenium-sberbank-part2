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
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();


        WebElement surname = webDriver.findElement(By.xpath("//input[@placeholder='Surname']"));
        surname.sendKeys("Surname");

        WebElement name = webDriver.findElement(By.xpath("//input[@placeholder='Name']"));
        name.sendKeys("Name");

        WebElement birthDate = webDriver.findElement(By.xpath("//input[contains(@id,'birthDate_')]"));
        birthDate.sendKeys("09.10.1996");

        WebElement personLastName = webDriver.findElement(By.xpath("//input[@id='person_lastName']"));
        personLastName.sendKeys(Keys.CONTROL + "a");
        personLastName.sendKeys("Фамилия");

        WebElement personFirstName = webDriver.findElement(By.xpath("//input[@id='person_firstName']"));
        personFirstName.sendKeys("Имя");

        WebElement personMiddleName = webDriver.findElement(By.xpath("//input[@id='person_middleName']"));
        personMiddleName.sendKeys("Отчество");

        WebElement personBirthDate = webDriver.findElement(By.xpath("//input[@id='person_birthDate']"));
        personBirthDate.sendKeys("09.10.1996");

        WebElement passportSeries = webDriver.findElement(By.xpath("//input[@id='passportSeries']"));
        passportSeries.sendKeys(Keys.CONTROL + "a");
        passportSeries.sendKeys("1111");

        WebElement passportNumber = webDriver.findElement(By.xpath("//input[@id='passportNumber']"));
        passportNumber.sendKeys("111111");

        WebElement documentDate = webDriver.findElement(By.xpath("//input[@id='documentDate']"));
        documentDate.sendKeys("09.10.2016");

        WebElement documentIssue = webDriver.findElement(By.xpath("//input[@id='documentIssue']"));
        documentIssue.sendKeys(Keys.CONTROL + "a");
        documentIssue.sendKeys("Кем выдан");


        Assert.assertEquals("Ошибка. Фамилия не совпадает.",surname.getAttribute("value"),"Surname");
        Assert.assertEquals("Ошибка. Имя не совпадает.",name.getAttribute("value"),"Name");
        Assert.assertEquals("Ошибка. Дата рождения не совпадает.",birthDate.getAttribute("value"),"09.10.1996");
        Assert.assertEquals("Ошибка. Фамилия страхователя не совпадает.",personLastName.getAttribute("value"),"Фамилия");
        Assert.assertEquals("Ошибка. Имя страхователя не совпадает.",personFirstName.getAttribute("value"),"Имя");
        Assert.assertEquals("Ошибка. Отчество страхователя не совпадает.",personMiddleName.getAttribute("value"),"Отчество");
        Assert.assertEquals("Ошибка. Дата рождения страхователя не совпадает.",personBirthDate.getAttribute("value"),"09.10.1996");
        Assert.assertEquals("Ошибка. Серия паспорта не совпадает.",passportSeries.getAttribute("value"),"1111");
        Assert.assertEquals("Ошибка. Номер паспорта не совпадает.",passportNumber.getAttribute("value"),"111111");
        Assert.assertEquals("Ошибка. Дата выдачи паспорта не совпадает.",documentDate.getAttribute("value"),"09.10.2016");
        Assert.assertEquals("Ошибка. Кем выдан паспорт не совпадает.",documentIssue.getAttribute("value"),"Кем выдан");


        webDriver.findElement(By.xpath("//button[contains(text(),'Продолжить')]")).click();

        element = webDriver.findElement(By.xpath("//div[contains(@class,'error')]"));
        String title = element.getText();
        Assert.assertEquals("Сообщение об ошибке не появилось",title,"При заполнении данных произошла ошибка");


    }
}
