import common_lib.browser;
import element.CustomerAndAccountsSelector;
import input.data;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Login {

    data data = new data();
    CustomerAndAccountsSelector CustomerAndAccountsSelector = new CustomerAndAccountsSelector();
    browser brw = new browser();

    @BeforeClass
    public void openBrowser() {

        //BrowserSetup
        brw.chromeBrowser(data.baseUrl);

    }

    @Test(priority = 1, dataProvider = "Creds_Data", description = "Check Login functionality")
    public void login(String uid, String pass){

        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.uid)).sendKeys(uid);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.passd)).sendKeys(pass);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.login_btn)).click();

        try {
            Alert alert = brw.driver.switchTo().alert();
            System.out.println("Login Error");
            String expected_alert = "User or Password is not valid";
            Assert.assertEquals(alert.getText(), expected_alert);
            alert.accept();
        }
        catch(Exception ex){
            System.out.println("Login Successful");
            String actual_uid = brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.manager_id)).getText();
            String expected_uid = "Manger Id : mngr354021";
            Assert.assertEquals(expected_uid,actual_uid);
            brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.logout_btn)).click();
            brw.driver.switchTo().alert().accept();
        }

    }



//   ****************************************************************
//                           DATA PROVIDER
//   ****************************************************************

    @DataProvider(name = "Creds_Data")
    public Object[][] data(){

        Object[][] names = new Object[4][2];
        //Valid User ID & Pass
        names[0][0] = "mngr354021";
        names[0][1] = "udYbEna";

        //Invalid UserID Valid Pass
        names[1][0] = "fgyuicsRRunh";
        names[1][1] = "udYbEna";

        //Valid UserID Invalid Pass
        names[2][0] = "mngr354021";
        names[2][1] = "mnegr35fe4021";

        //Invalid UserID Valid Pass
        names[3][0] = "mnegr35fe4021";
        names[3][1] = "fgyuicsRRunh";



        return names;

    }

    @AfterClass
    public void closeBrowser(){

        brw.driver.close();
        brw.driver.quit();

    }

}
