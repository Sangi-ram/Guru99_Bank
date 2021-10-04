package Utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

import java.io.File;

public class Utility {

    public static void screenShot(WebDriver driver, String fileName) {

        try {

            TakesScreenshot ss = (TakesScreenshot) driver;
            File source = ss.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(source, new File("src\\Screenshots\\" + fileName + ".png"));
            System.out.println("Screenshot captured\n Filename :" + fileName + ".png");

        } catch (Exception e) {

            System.out.println("Exception caught while capturing ScreenShot :: " + e.getMessage());

        }

    }

    public static void LoginFn(WebDriver driver, String uname, String pwd) {
        driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td[2]/input")).sendKeys(uname);
        driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td[2]/input")).sendKeys(pwd);
        driver.findElement(By.xpath("/html/body/form/table/tbody/tr[3]/td[2]/input[1]")).click();

    }

    public static void click(WebDriver driver, String path) {

        driver.findElement(By.xpath(path)).click();

    }

    public static void sendKeys(WebDriver driver, String path, String data) {

        driver.findElement(By.xpath(path)).sendKeys(data);

    }

}

