package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class ShareLaneTests {
    WebDriver driver;
    @Test
    //тест-метод из урока [все поля оставить пустыми, нажать кнопку Register]
    public void verifiedRegisterFormTest(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.xpath("//input[@name= 'zip_code']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@value= 'Continue']")).click();
        driver.findElement(By.xpath("//input[@value= 'Register']")).click();
        String actualText1 = driver.findElement(By.xpath("//span[@class= 'error_message']")).getText();
        Assert.assertEquals(actualText1, "Oops, error on page. Some of your fields have invalid data or email " +
                "was previously used");
    }

    @Test
    //тест-метод #1 [заполнить все только обязательные поля, нажать кнопку Register]
    public void checkRegisterFormPositiveTest1(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.xpath("//input[@name= 'zip_code']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@value= 'Continue']")).click();
        driver.findElement(By.xpath("//input[@name= 'first_name']")).sendKeys("Aleksandra");
        driver.findElement(By.xpath("//input[@name= 'email']")).sendKeys("a.a@a.com");
        driver.findElement(By.xpath("//input[@name= 'password1']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@name= 'password2']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@value= 'Register']")).click();
        String actualText2 = driver.findElement(By.xpath("//span[@class= 'confirmation_message']")).getText();
        Assert.assertEquals(actualText2, "Account is created!");
    }
    @Test
    //тест-метод #2 [заполнить все поля, нажать кнопку Register]
    public void checkRegisterFormPositiveTest2(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.xpath("//input[@name= 'zip_code']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@value= 'Continue']")).click();
        driver.findElement(By.xpath("//input[@name= 'first_name']")).sendKeys("Aleksandra");
        driver.findElement(By.xpath("//input[@name= 'last_name']")).sendKeys("Karpovich");
        driver.findElement(By.xpath("//input[@name= 'email']")).sendKeys("a.a@a.com");
        driver.findElement(By.xpath("//input[@name= 'password1']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@name= 'password2']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@value= 'Register']")).click();
        String actualText3 = driver.findElement(By.xpath("//span[@class= 'confirmation_message']")).getText();
        Assert.assertEquals(actualText3, "Account is created!");

    }
    @Test
    //тест-метод #3
    // [заполнить все поля, в поле Password и Password Confirm ввести разные значения, нажать кнопку Register]
    // по функционалу баг, тест должен упасть, потому что не совпадет актуальный (создается аккаунт) и ожидаемый
    // (показывается ошибка) результат
    public void checkRegisterFormNegativeTest1(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.xpath("//input[@name= 'zip_code']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@value= 'Continue']")).click();
        driver.findElement(By.xpath("//input[@name= 'first_name']")).sendKeys("Aleksandra");
        driver.findElement(By.xpath("//input[@name= 'last_name']")).sendKeys("Karpovich");
        driver.findElement(By.xpath("//input[@name= 'email']")).sendKeys("a.a@a.com");
        driver.findElement(By.xpath("//input[@name= 'password1']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@name= 'password2']")).sendKeys("54321");
        driver.findElement(By.xpath("//input[@value= 'Register']")).click();
        String actualText4 = driver.findElement(By.xpath("//span[@class= 'error_message']")).getText();
        Assert.assertEquals(actualText4, "Oops, error on page. Some of your fields have invalid data or email " +
                "was previously used");

    }
    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}
