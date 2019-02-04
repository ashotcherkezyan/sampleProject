import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class Helper {
    //taking a screenshot
    public String captureScreen(WebDriver driver) {
        String path;
        try {
            WebDriver augmentedDriver = new Augmenter().augment(driver);
            File source = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
            path = "./target/screenshots/" + source.getName();
            FileUtils.copyFile(source, new File(path));
        }
        catch(IOException e) {
            path = "Failed to capture screenshot: " + e.getMessage();
        }
        return path;
    }


    //wait in millisec
    public void wait(int miliSecond) throws InterruptedException {
        Thread.sleep(miliSecond);
    }


    //waiting until element is present
    public void waitForElementPresent(WebDriver driver,String xpath, int maxTime) throws InterruptedException {
        try{
            int t=0;
            while (!driver.findElement(By.xpath(xpath)).isDisplayed() && t<=maxTime){
                t+=200;
                Thread.sleep(t);
            }
        }
        catch (Exception e)
        {
            Thread.sleep(maxTime);
        }

    }

    //waiting for URL
    public void waitForUrlPresent(WebDriver driver, String text, int maxTime) throws InterruptedException {
        try {
            int t = 0;
            while (!(text.equals(driver.getCurrentUrl())) && t <= maxTime) {
                t += 200;
                Thread.sleep(t);
            }
        } catch (Exception e) {
            System.out.println("Connection error/Cant locate an element");
        }
    }


    //waiting for page to be ready
    public void waitForPageToBeReady(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
    }
}
