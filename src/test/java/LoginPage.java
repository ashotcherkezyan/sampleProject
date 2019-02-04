import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Iterator;
import java.util.Set;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class LoginPage {

    WebDriver driver;
    Actions action;
    Helper helper;
    public static final String path = System.getProperty("user.dir") + File.separator + "requirements" + File.separator + "webdrivers" + File.separator;

    @BeforeTest
    public void before(){
        System.setProperty("webdriver.chrome.driver",path+ "chromedriver-v2.37-win32.exe");
        driver = new ChromeDriver();
        helper = new Helper();
        driver.manage().window().maximize();
        driver.get("https://globbing.com/");
        action = new Actions(driver);
    }

    @Test
    public void Test1() throws InterruptedException {
        driver.get("https://globbing.com/arm/register");
        action.moveToElement(driver.findElement(By.xpath("//button[contains(text(),'Գրանցվել ')]"))).build().perform();
        Thread.sleep(2000);
        assertEquals("rgba(31, 139, 106, 1)",driver.findElement(By.xpath("//button[contains(text(),'Գրանցվել ')]")).getCssValue("background-color"));
    }
    @Test
    public void Test2(){
        driver.get("https://globbing.com/arm/register");
        driver.findElement(By.xpath("//button[contains(text(),'Գրանցվել ')]")).click();
        assertEquals("rgba(251, 192, 192, 1)",driver.findElement(By.xpath("//input[@id='register_personal_email']")).getCssValue("background-color"));

    }
    @Test
    public void Test3() throws InterruptedException {
        driver.get("https://globbing.com/arm/register");
        action.moveToElement(driver.findElement(By.xpath("//*[@id=\"register_personal_email\"]"))).build().perform();
        driver.findElement(By.xpath("//input[@id='register_personal_email']")).click();
        Thread.sleep(2000);
        assertEquals("rgba(24, 25, 26)",driver.findElement(By.xpath("//input[@id='register_personal_email']")).getCssValue("border-color"));

    }
    @Test
    public void Test4(){
        driver.get("https://globbing.com/arm/register");
        driver.findElement(By.xpath("//*[@id=\'register_personal_email\']")).sendKeys("Armen@gmail.com");
        driver.navigate().refresh();
        assertEquals("",driver.findElement(By.xpath("//*[@id=\'register_personal_email\']")).getText());
    }
    @Test
    public void Test5(){
        driver.get("https://globbing.com/arm/register");
        driver.findElement(By.xpath("//*[@id=\'register_personal_email\']")).sendKeys("#@%^%#$@#$@#.com");
        driver.findElement(By.xpath("//*[@id=\'f_registerFormValidation\']/div[5]/button")).click();
        assertTrue(driver.findElement(By.xpath("//*[@id=\"f_registerFormValidation\"]/div[1]/div/div[1]/div")).isDisplayed());
    }
    @Test
    public void Test6() {
        driver.get("https://globbing.com/arm/register");
        driver.findElement(By.xpath("//*[@id=\'register_personal_email\']")).sendKeys("ArmenAntonyan@gmail.com");
        driver.findElement(By.xpath("//*[@id=\'f_registerFormValidation\']/div[5]/button")).click();
        assertEquals("rgba(236, 248, 244, 1)",driver.findElement(By.xpath("//*[@id=\"register_personal_email\"]")).getCssValue("background-color"));
    }
    @Test
    public void Test7(){
        driver.get("https://globbing.com/arm/register");
        driver.findElement(By.xpath("//*[@id='register_personal_email']")).sendKeys("ArmenAntonyan@gmail.com");
        driver.findElement(By.xpath("//*[@id='register_personal_password']")).sendKeys("123456789");
        driver.findElement(By.xpath("//*[@id='register_personal_passwordConfirmation']")).sendKeys("123456789");
        driver.findElement(By.xpath("//*[@id='register_billing_phone']")).sendKeys("55575152");
        driver.findElement(By.xpath("//*[@id='f_registerFormValidation']/div[1]/div/div[2]/div[5]/label/span[1]")).click();
        driver.findElement(By.xpath("//*[@id='f_registerFormValidation']/div[5]/button")).click();
        assertTrue(driver.findElement(By.xpath("//*[@id='f_registerFormValidation']/div[1]/div/div[1]/div")).isDisplayed());
    }
    @Test
    public void Test8(){
        driver.get("https://globbing.com/arm/register");
        driver.findElement(By.xpath("//*[@id='register_personal_password']")).sendKeys("a");
        driver.findElement(By.xpath("//*[@id='f_registerFormValidation']/div[5]/button")).click();
        assertTrue(driver.findElement(By.xpath("//*[@id='f_registerFormValidation']/div[1]/div/div[2]/div[3]/div")).isDisplayed());
    }
    @Test
    public void Test11() throws InterruptedException {
        driver.get("https://globbing.com/arm/register");
        scroll(0,400);
        helper.wait(2000);
        driver.findElement(By.xpath("//*[@id='f_registerFormValidation']/div[5]/button")).click();
        helper.waitForPageToBeReady(driver);
        driver.findElement(By.xpath("//*[@id=\'f_registerFormValidation\']/div[1]/div/div[2]/div[5]/label/span[1]")).click();
        assertEquals("accept-reg-icon",driver.findElement(By.xpath("//*[@id=\'f_registerFormValidation\']/div[1]/div/div[2]/div[5]/label/span[1]")).getAttribute("class"));
        helper.captureScreen(driver);
    }
    @Test
    public void Test12(){
        driver.get("https://globbing.com/arm/register");
        driver.findElement(By.xpath("//*[@id=\'f_registerFormValidation\']/div[5]/ul/li/a")).click();
        switchToWindow();
        assertTrue(driver.getCurrentUrl().contains("facebook.com"));

    }
    protected String switchToWindow() {
        Set<String> set = driver.getWindowHandles();
        Iterator<String> it = set.iterator();
        String parentWindowId = it.next();
        String childWindowId = it.next();
        driver.switchTo().window(childWindowId);
        return driver.getCurrentUrl();

    }
    @AfterMethod
    public void after(){
        driver.quit();
    }

    public void scroll(int x, int y){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy("+x+","+y+")");
    }

}
