import Utilities.Utility;
import common_lib.browser;
import element.CustomerAndAccountsSelector;
import input.data;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Accounts {

    data data = new data();
    CustomerAndAccountsSelector CustomerAndAccountsSelector = new CustomerAndAccountsSelector();
    browser brw = new browser();

    @BeforeClass
    public void openBrowser() {

        //BrowserSetup
        brw.chromeBrowser(data.baseUrl);

    }

    @Test(priority = 20, description ="Verify Mini Statement")
    public void miniStatement() {

        Utility.LoginFn(brw.driver);
        //CHECK MINI STATEMENT
        JavascriptExecutor executor = (JavascriptExecutor) brw.driver;
        WebElement element = brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.mini_stmt_btn));
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.macc_no_inp)).sendKeys(data.payersAccountNo);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.msubmit)).click();
        WebElement miniStatement = brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.miniStatementAssert));
        Assert.assertNotNull(miniStatement);

    }

    @Test(priority = 21, description ="Check Account Balance")
    public void accountBalance() {

//        Utility.LoginFn(brw.driver);
        //CHECK BALANCE
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.bal_enquiry_btn)).click();
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.bal_acc_no_inp)).sendKeys(data.payersAccountNo);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.bal_submit)).click();
        WebElement balance = brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.balanceAssert));
        Assert.assertNotNull(balance);

    }

    @Test(priority = 22, description ="Generate Custom Transaction Statement")
    public void customTransactionStatement() {

//        Utility.LoginFn(brw.driver);
        //CHECK CUSTOM TRANSACTION
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.custom_stmt_btn)).click();
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.custom_acc_no_inp)).sendKeys(data.payersAccountNo);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.from_date)).sendKeys(data.start_date);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.to_date)).sendKeys(data.end_date);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.min_trans)).sendKeys(data.min_transaction);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.noOfTrans)).sendKeys(data.noOfTransactions);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.custom_submit)).click();
        WebElement customTransaction = brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.customTransactionAssert));
        Assert.assertNotNull(customTransaction);

    }

    @Test(priority = 23, description ="Generate Custom Transaction Statement where From Date is Greater Than To Date")
    public void customTransactionStatementLateFromDate() {

//        Utility.LoginFn(brw.driver);
        //CHECK CUSTOM TRANSACTION
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.custom_stmt_btn)).click();
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.custom_acc_no_inp)).sendKeys(data.payersAccountNo);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.from_date)).sendKeys(data.end_date);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.to_date)).sendKeys(data.start_date);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.min_trans)).sendKeys(data.min_transaction);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.noOfTrans)).sendKeys(data.noOfTransactions);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.custom_submit)).click();
        Alert lateToDate = brw.driver.switchTo().alert();
        System.out.println("Alert message ::"+lateToDate.getText());
        Assert.assertNotNull(lateToDate);
        lateToDate.accept();

    }

    @Test(priority = 24, description ="Logout")
    public void logout() {
//
//        Utility.LoginFn(brw.driver);

        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.logout_btn)).click();
        brw.driver.switchTo().alert().accept();

    }


    @AfterClass
    public void closeBrowser(){

        brw.driver.close();
        brw.driver.quit();

    }

}
