import Utilities.Utility;
import common_lib.browser;
import element.CustomerAndAccountsSelector;
import element.FundTransferSelectors;
import input.data;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FundTransferAndDeposits {

    input.data data = new data();
    FundTransferSelectors FSelectors = new FundTransferSelectors();
    CustomerAndAccountsSelector CSelectors = new CustomerAndAccountsSelector();
    browser brw = new browser();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.now();

    @BeforeClass
    public void openBrowser() {

        //BrowserSetup
        brw.chromeBrowser(data.baseUrl);

    }

    @Test(priority = 11, description ="Verify Fund Transfer")
    public void fundTransfer() {
        Utility.LoginFn(brw.driver);

        brw.driver.findElement(By.xpath(FSelectors.fundTransferBtn)).click();
        brw.driver.findElement(By.xpath(FSelectors.payersAccount)).sendKeys(data.payersAccountNo);
        brw.driver.findElement(By.xpath(FSelectors.payeeAccount)).sendKeys(data.payeesAccountNo);
        brw.driver.findElement(By.xpath(FSelectors.paymentAmount)).sendKeys(data.fundTransferAmount);
        brw.driver.findElement(By.xpath(FSelectors.fundTransferDescription)).sendKeys(data.fundDescription);
        brw.driver.findElement(By.xpath(FSelectors.fundTransferSubmit)).click();
        String fundAssert = brw.driver.findElement(By.xpath(FSelectors.payeeAssert)).getText();
        Assert.assertEquals(fundAssert,data.payeesAccountNo);

    }

    @Test(priority = 12, description ="Verify Fund Transfer is Not Done Again After Refreshing Web Page")
    public void verifyFundTransferRefresh() {

        brw.driver.navigate().refresh();
        String fundTransferRefresh = brw.driver.findElement(By.xpath(FSelectors.payersAccount)).getText();
        Assert.assertNull(fundTransferRefresh);

    }

    @Test(priority = 13, description ="Verify Fund Transfer Details On Customised Statements")
    public void verifyCustomStatement(){
//        Utility.LoginFn(brw.driver);

        brw.driver.findElement(By.xpath(CSelectors.custom_stmt_btn)).click();
        brw.driver.findElement(By.xpath(CSelectors.custom_acc_no_inp)).sendKeys(data.payeesAccountNo);
        brw.driver.findElement(By.xpath(CSelectors.from_date)).sendKeys(data.start_date);
        brw.driver.findElement(By.xpath(CSelectors.to_date)).sendKeys(data.end_date);
        brw.driver.findElement(By.xpath(CSelectors.min_trans)).sendKeys(data.min_transaction);
        brw.driver.findElement(By.xpath(CSelectors.noOfTrans)).sendKeys(data.noOfTransactions);
        brw.driver.findElement(By.xpath(CSelectors.custom_submit)).click();
        Assert.assertTrue(brw.driver.getPageSource().contains(dtf.format(localDate)));

    }

    @Test(priority = 14, description ="Verify Fund Transfer While Transferring Fund to Wrong Account")
    public void wrongAccount(){

//        Utility.LoginFn(brw.driver);
        brw.driver.findElement(By.xpath(FSelectors.fundTransferBtn)).click();
        brw.driver.findElement(By.xpath(FSelectors.payersAccount)).sendKeys(data.payersAccountNo);
        brw.driver.findElement(By.xpath(FSelectors.payeeAccount)).sendKeys(data.wrongAccount);
        brw.driver.findElement(By.xpath(FSelectors.paymentAmount)).sendKeys(data.fundTransferAmount);
        brw.driver.findElement(By.xpath(FSelectors.fundTransferDescription)).sendKeys(data.fundDescription);
        brw.driver.findElement(By.xpath(FSelectors.fundTransferSubmit)).click();
        Alert wrongAccountAlert = brw.driver.switchTo().alert();
        String wrongAccountAlertMsg = wrongAccountAlert.getText();
        Assert.assertEquals(wrongAccountAlertMsg,"Account 108374838does not exist!!!");
        wrongAccountAlert.accept();

    }

    @Test(priority = 15, description ="Verify Fund Transfer While Transferring Fund to Same Account")
    public void sameAccount(){

//        Utility.LoginFn(brw.driver);
        brw.driver.findElement(By.xpath(FSelectors.fundTransferBtn)).click();
        brw.driver.findElement(By.xpath(FSelectors.payersAccount)).sendKeys(data.payersAccountNo);
        brw.driver.findElement(By.xpath(FSelectors.payeeAccount)).sendKeys(data.payersAccountNo);
        brw.driver.findElement(By.xpath(FSelectors.paymentAmount)).sendKeys(data.fundTransferAmount);
        brw.driver.findElement(By.xpath(FSelectors.fundTransferDescription)).sendKeys(data.fundDescription);
        brw.driver.findElement(By.xpath(FSelectors.fundTransferSubmit)).click();
        Alert sameAccountAlert = brw.driver.switchTo().alert();
        String sameAccountAlertMsg = sameAccountAlert.getText();
        Assert.assertEquals(sameAccountAlertMsg,"Payers account No and Payees account No Must Not be Same!!!");
        sameAccountAlert.accept();

    }

    @Test(priority = 16, description ="Verify Fund Transfer While Transferring Fund to Account with low balance")
    public void lowBalanceAccount(){

//        Utility.LoginFn(brw.driver);
        brw.driver.findElement(By.xpath(FSelectors.fundTransferBtn)).click();
        brw.driver.findElement(By.xpath(FSelectors.payersAccount)).sendKeys(data.payersAccountNo);
        brw.driver.findElement(By.xpath(FSelectors.payeeAccount)).sendKeys(data.payeesAccountNo);
        brw.driver.findElement(By.xpath(FSelectors.paymentAmount)).sendKeys(data.lowBalance);
        brw.driver.findElement(By.xpath(FSelectors.fundTransferDescription)).sendKeys(data.fundDescription);
        brw.driver.findElement(By.xpath(FSelectors.fundTransferSubmit)).click();
        Alert lowBalanceAccountAlert = brw.driver.switchTo().alert();
        String lowBalanceAccountAlertMsg = lowBalanceAccountAlert.getText();
        Assert.assertEquals(lowBalanceAccountAlertMsg,"Transfer Failed. Account Balance low!!");
        lowBalanceAccountAlert.accept();

    }

    @Test(priority = 17, description ="Verify Deposit Functionality")
    public void deposit(){

//        Utility.LoginFn(brw.driver);
        brw.driver.findElement(By.xpath(FSelectors.depositBtn)).click();
        brw.driver.findElement(By.xpath(FSelectors.depositAccountNumber)).sendKeys(data.payersAccountNo);
        brw.driver.findElement(By.xpath(FSelectors.depositAmount)).sendKeys(data.fundTransferAmount);
        brw.driver.findElement(By.xpath(FSelectors.depositDescription)).sendKeys(data.fundDescription);
        brw.driver.findElement(By.xpath(FSelectors.depositSubmitBtn)).click();
        Boolean deposit = brw.driver.findElement(By.xpath(FSelectors.depositAssert)).isDisplayed();
        Assert.assertTrue(deposit);

    }

    @Test(priority = 18, description ="Verify Deposit is Not Done Again After Refreshing Web Page")
    public void verifyDepositRefresh() {

        brw.driver.navigate().refresh();
        String depositRefresh = brw.driver.findElement(By.xpath(FSelectors.depositAccountNumber)).getText();
        Assert.assertNull(depositRefresh);

    }

    @AfterClass
    public void closeBrowser(){

        brw.driver.close();
        brw.driver.quit();

    }

}
