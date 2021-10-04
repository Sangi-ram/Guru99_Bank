package Customer;

import Utilities.Utility;
import common_lib.browser;
import element.CustomerPageSelectors;
import input.data;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerTransactions {

    data data = new data();
    CustomerPageSelectors cpSelectors = new CustomerPageSelectors();
    browser brw = new browser();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.now();

    @BeforeClass
    public void openBrowser() {

        //BrowserSetup
        brw.chromeBrowser(data.baseUrl);
        Utility.LoginFn(brw.driver, "69669", "nishanth");

    }

    @Test(priority = 1, description ="Check Account Balance")
    public void accountBalance() {

        //CHECK BALANCE
        Utility.click(brw.driver, cpSelectors.balanceEnquiryBtn);
        Select accountNO = new Select(brw.driver.findElement(By.xpath(cpSelectors.accountNOSelect)));
        accountNO.selectByVisibleText(data.accountNO);
        Utility.click(brw.driver, cpSelectors.balanceSubmit);
        WebElement balance = brw.driver.findElement(By.xpath(cpSelectors.balanceAssert));
        Assert.assertNotNull(balance);
    }

    @Test(priority = 2, description ="Check MiniStatement")
    public void miniStatement() {

        Utility.click(brw.driver, cpSelectors.miniStatementBtn);
        Utility.sendKeys(brw.driver,cpSelectors.miniStatementAccount,data.accountNO);
        Utility.click(brw.driver, cpSelectors.miniStatementSubmitBtn);
        WebElement miniStatement = brw.driver.findElement(By.xpath(cpSelectors.miniStatementAssert));
        Assert.assertNotNull(miniStatement);
    }

    @Test(priority = 3, description ="Check MiniStatement with Wrong Account Number")
    public void miniStatementWrongAccount() {

        Utility.click(brw.driver, cpSelectors.miniStatementBtn);
        Utility.sendKeys(brw.driver,cpSelectors.miniStatementAccount,data.wrongAccount);
        Utility.click(brw.driver, cpSelectors.miniStatementSubmitBtn);
        Alert wrongMiniStatement = brw.driver.switchTo().alert();
        System.out.println(wrongMiniStatement.getText());
        Assert.assertNotNull(wrongMiniStatement);
        wrongMiniStatement.accept();
    }

    @Test(priority = 4, description ="Check MiniStatement of an Unauthorised Account")
    public void miniStatementUnAuthorisedAccount() {

        Utility.click(brw.driver, cpSelectors.miniStatementBtn);
        Utility.sendKeys(brw.driver,cpSelectors.miniStatementAccount,data.payersAccountNo);
        Utility.click(brw.driver, cpSelectors.miniStatementSubmitBtn);
        Alert unauthorisedMiniStatement = brw.driver.switchTo().alert();
        Assert.assertNotNull(unauthorisedMiniStatement);
        System.out.println(unauthorisedMiniStatement.getText());
        unauthorisedMiniStatement.accept();
    }

    @Test(priority = 5, description ="Check Fund Transfer")
    public void fundTransfer() {

        Utility.click(brw.driver, cpSelectors.fundTransferBtn);
        Utility.sendKeys(brw.driver, cpSelectors.payersAccountNO, data.accountNO);
        Utility.sendKeys(brw.driver, cpSelectors.payeesAccountNO, data.payersAccountNo);
        Utility.sendKeys(brw.driver, cpSelectors.amount, data.fundTransferAmount);
        Utility.sendKeys(brw.driver, cpSelectors.description, data.fundDescription);
        Utility.click(brw.driver, cpSelectors.fundTransferSubmitBtn);
        WebElement fundTransfer = brw.driver.findElement(By.xpath(cpSelectors.fundTransferAssert));
        Assert.assertNotNull(fundTransfer);
    }

    @Test(priority = 6, description ="Check Fun Transfer is Not Done After Page is Refreshed")
    public void refreshFundTransfer() {

        brw.driver.navigate().refresh();
        String refreshed = brw.driver.findElement(By.xpath(cpSelectors.payeesAccountNO)).getText();
        Assert.assertEquals(refreshed, "");
    }

    @Test(priority = 7, description ="Verify Fund Transfer Details On Customised Statements")
    public void verifyCustomStatement() {

        Utility.click(brw.driver,cpSelectors.customStatementBtn);
        Utility.sendKeys(brw.driver, cpSelectors.customStatementAccount, data.accountNO);
        Utility.sendKeys(brw.driver, cpSelectors.fromDate, data.start_date);
        Utility.sendKeys(brw.driver, cpSelectors.toDate, data.end_date);
        Utility.sendKeys(brw.driver, cpSelectors.minimunTransactionAmount, data.min_transaction);
        Utility.sendKeys(brw.driver, cpSelectors.noOfTransaction, data.noOfTransactions);
        Utility.click(brw.driver, cpSelectors.customStatementSubmitBtn);
        Assert.assertTrue(brw.driver.getPageSource().contains(dtf.format(localDate)));

    }

    @Test(priority = 8, description ="Check Fund Transfer using Unauthorised Payers Account, Same Payer And Payee Account, Non-Existing Payee", dataProvider = "fundTransfer")
    public void fundTransferErrorGeneration(String payer, String payee) {

        Utility.click(brw.driver, cpSelectors.fundTransferBtn);
        Utility.sendKeys(brw.driver, cpSelectors.payersAccountNO, payer);
        Utility.sendKeys(brw.driver, cpSelectors.payeesAccountNO, payee);
        Utility.sendKeys(brw.driver, cpSelectors.amount, data.fundTransferAmount);
        Utility.sendKeys(brw.driver, cpSelectors.description, data.fundDescription);
        Utility.click(brw.driver, cpSelectors.fundTransferSubmitBtn);
        Alert fundTransfer = brw.driver.switchTo().alert();
        System.out.println(fundTransfer.getText());
        Assert.assertNotNull(fundTransfer);
        fundTransfer.accept();
    }

    @Test(priority = 9, description ="Verify Customised Statements Output for Unauthorised Account, Wrong Account NO, To-Date Lower Than From-Date", dataProvider = "customStatement")
    public void customStatementErrorGeneration(String account, String toDate) {

        Utility.click(brw.driver,cpSelectors.customStatementBtn);
        Utility.sendKeys(brw.driver, cpSelectors.customStatementAccount, account);
        Utility.sendKeys(brw.driver, cpSelectors.fromDate, data.start_date);
        Utility.sendKeys(brw.driver, cpSelectors.toDate, toDate);
        Utility.sendKeys(brw.driver, cpSelectors.minimunTransactionAmount, data.min_transaction);
        Utility.sendKeys(brw.driver, cpSelectors.noOfTransaction, data.noOfTransactions);
        Utility.click(brw.driver, cpSelectors.customStatementSubmitBtn);
        Alert customStatement = brw.driver.switchTo().alert();
        System.out.println(customStatement.getText());
        Assert.assertNotNull(customStatement);
        customStatement.accept();
    }


    @AfterClass
    public void closeBrowser(){

        brw.driver.close();
        brw.driver.quit();

    }

    @DataProvider(name = "fundTransfer")
    public Object[][] fundTransferData(){

        Object[][] accountNos = new Object[3][2];
        //Unauthorised Payers Account
        accountNos[0][0] = data.payersAccountNo;
        accountNos[0][1] = data.payeesAccountNo;

        //Same Payer And Payee Account
        accountNos[1][0] = data.accountNO;
        accountNos[1][1] = data.accountNO;

        //Non-Existent Payee
        accountNos[2][0] = data.accountNO;
        accountNos[2][1] = data.wrongAccount;

        return accountNos;

    }

    @DataProvider(name = "customStatement")
    public Object[][] customStatementData(){

        Object[][] cSData = new Object[3][2];
        //Unauthorised Account
        cSData[0][0] = data.payersAccountNo;
        cSData[0][1] = data.end_date;

        //Inexisting Account
        cSData[1][0] = data.wrongAccount;
        cSData[1][1] = data.end_date;

        //To-Date Lower Than Fro-Date
        cSData[2][0] = data.accountNO;
        cSData[2][1] = "0105\t2008";

        return cSData;

    }

}
