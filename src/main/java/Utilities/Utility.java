package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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

    public static void LoginFn(WebDriver driver) {
        driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td[2]/input")).sendKeys("mngr354021");
        driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td[2]/input")).sendKeys("udYbEna");
        driver.findElement(By.xpath("/html/body/form/table/tbody/tr[3]/td[2]/input[1]")).click();

    }
}

