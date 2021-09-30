import Utilities.Utility;
import common_lib.browser;
import element.CustomerAndAccountsSelector;
import input.data;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Objects;

public class CustomerAndAccounts {

    data data = new data();
    CustomerAndAccountsSelector CustomerAndAccountsSelector = new CustomerAndAccountsSelector();
    browser brw = new browser();
    public String cust_id;
    public String acc_no;

    @BeforeClass
    public void openBrowser() {

        //BrowserSetup
        brw.chromeBrowser(data.baseUrl);

    }

    @Test(priority = 2, description ="Add new Customer")
    public void Add_New_Customer(){
        //LOGIN
        Utility.LoginFn(brw.driver);

        //Add_New_Customer
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.new_cstmr_buttn)).click();
        Assert.assertEquals(brw.driver.getTitle(),"Guru99 Bank New Customer Entry Page");
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.icust_name)).sendKeys(data.customer_name);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.igender)).click();
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.idob)).sendKeys(data.dob);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.iaddress)).sendKeys(data.address);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.icity)).sendKeys(data.city);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.istate)).sendKeys(data.state);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.ipin)).sendKeys(data.pin);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.iphone)).sendKeys(data.phone);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.ie_mail)).sendKeys(data.e_mail);//CHANGE THIS VALUE BEFORE EVERY EXECUTION
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.icust_passwd)).sendKeys(data.customer_passwd);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.icust_submit)).click();
        //ASSERT
        Assert.assertTrue(brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.ocust_id)).isDisplayed());
        String new_cust_id = brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.ocust_id)).getText();
        System.out.println("New Customer ID :: " + new_cust_id);
        Utility.screenShot(brw.driver, "New Cust " +new_cust_id);
        cust_id = new_cust_id;

//      ****************************************************************
//                  SAVE THE CUSTOMER ID FOR LATER USE
//      ****************************************************************
//            Thread.sleep(3000);

    }

    @Test(priority = 3, description ="Edit Customer Details")
    public void editCustomerDetails() {

//        cust_id = "61232";
//        Utility.LoginFn(brw.driver);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.customer_edit_btn)).click();
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.edit_customer_id_input)).sendKeys(cust_id);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.edit_customer_submit)).click();
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.editAddress)).sendKeys(Keys.CONTROL +"a" +Keys.DELETE );
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.editAddress)).sendKeys(data.newAddress);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.editCity)).sendKeys(Keys.CONTROL +"a" +Keys.DELETE );
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.editCity)).sendKeys(data.newCity);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.editState)).sendKeys(Keys.CONTROL +"a" +Keys.DELETE );
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.editState)).sendKeys(data.newState);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.editPIN)).sendKeys(Keys.CONTROL +"a" +Keys.DELETE );
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.editPIN)).sendKeys(data.newPIN);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.editTeleNumber)).sendKeys(Keys.CONTROL +"a" +Keys.DELETE);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.editTeleNumber)).sendKeys(data.newPhone);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.editEMail)).sendKeys(Keys.CONTROL +"a" +Keys.DELETE );
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.editEMail)).sendKeys(data.newEMail);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.editSubmit)).click();
        String  editSuccess = brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.editSuccess)).getText();
        Assert.assertEquals(editSuccess,"Customer details updated Successfully!!!");

    }

    @Test(priority =4, description ="Add new Account Existing Customer")
    public void Add_New_Account_For_Existing_Customer() {
        //LOGIN
//        Utility.LoginFn(brw.driver);



//            ADD NEW ACCOUNT
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.add_account_btn)).click();
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.acust_id)).sendKeys(cust_id);
        Select acc_typ = new Select(brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.acc_typ)));
        acc_typ.selectByVisibleText("Current");
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.initial_deposit)).sendKeys(data.initial_deposit);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.new_acc_submit)).click();

        //ASSERT
        Assert.assertTrue(brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.acc_id)).isDisplayed());
        Assert.assertEquals(brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.acc_cust_id)).getText(),cust_id);
        String new_acc_id = brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.acc_id)).getText();
        System.out.println("New Account No :: " +new_acc_id );
        Utility.screenShot(brw.driver, "New Account " +new_acc_id);
        acc_no = new_acc_id;

    }

    @Test(priority = 5, description ="Delete Existing Account and Observe The Effect")
    public void Delete_Existing_Account() {

        //LOGIN
//        Utility.LoginFn(brw.driver);

        //DELETE ACCOUNT
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.acc_delete_btn)).click();
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.account_num_input)).sendKeys(acc_no); //CHANGE ACCOUNT NO IF NEEDED
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.account_dlt_submit)).click();
        //Confirmation
        Alert acc_del_alert = brw.driver.switchTo().alert();
        String acc_del_alert_msg = acc_del_alert.getText();
        System.out.println("Account Deletion Confirmation ::" + acc_del_alert_msg);
        acc_del_alert.accept();

        //Account Deleted
        Alert acc_dltd = brw.driver.switchTo().alert();
        String acc_deltd_msg = acc_dltd.getText();
        System.out.println("Account Deletion OP ::" + acc_deltd_msg);
        acc_dltd.accept();
    }

    @Test(priority = 6, description ="Check Mini Statement")
    public void Check_Mini_Statement() {

        //CHECK MINI STATEMENT
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.mini_stmt_btn)).click();
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.macc_no_inp)).sendKeys(acc_no);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.msubmit)).click();
        Alert mini_stmt_alert = brw.driver.switchTo().alert();
        System.out.println("Mini Statement OP ::" + mini_stmt_alert.getText());
        mini_stmt_alert.accept();
    }

    @Test(priority = 7, description ="Check Account Balance")
    public void Check_Account_Balance() {
        //CHECK BALANCE
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.bal_enquiry_btn)).click();
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.bal_acc_no_inp)).sendKeys(acc_no);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.bal_submit)).click();
        Alert balance_alert = brw.driver.switchTo().alert();
        System.out.println("Balance Check OP ::" + balance_alert.getText());
        balance_alert.accept();

    }

    @Test(priority = 8, description ="Generate Custom Transaction Statement")
    public void Custom_Transaction_Statement() {
        //CHECK CUSTOM TRANSACTION
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.custom_stmt_btn)).click();
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.custom_acc_no_inp)).sendKeys(acc_no);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.from_date)).sendKeys(data.start_date);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.to_date)).sendKeys(data.end_date);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.min_trans)).sendKeys(data.min_transaction);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.noOfTrans)).sendKeys(data.noOfTransactions);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.custom_submit)).click();
        Alert custom_trans = brw.driver.switchTo().alert();
        System.out.println("Custom Transaction OP ::" +custom_trans.getText());
        custom_trans.accept();

    }


    @Test(priority = 9, description ="Deleting Customer and Checking Status", dataProvider = "custIDs")
    public void Deleting_Customer() {
        //LOGIN
//        Utility.LoginFn(brw.driver);
        //****************************************************************
        //CHANGE CUSTOMER ID ACCOURDING TO THE TEST CASE
        //****************************************************************
//        cust_id = 10293

        //DELETE CUSTOMER
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.delete_customer_btn)).click();
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.delete_customer_id)).sendKeys(cust_id);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.delete_submit_btn)).click();
        Alert delete_confirm = brw.driver.switchTo().alert();
        System.out.println("Account Deletion Confirmation :: " + delete_confirm.getText());
        Assert.assertEquals(delete_confirm.getText(), "Do you really want to delete this Customer?");
        delete_confirm.accept();
        Alert deletion_result = brw.driver.switchTo().alert();
        System.out.println("Deletion Alert Message :: " + deletion_result.getText());
        String account_deletion_result = deletion_result.getText();
        //CHECK DELETION STATUS
        if(Objects.equals(account_deletion_result, "Customer could not be deleted!!. First delete all accounts of this customer then delete the customer")) {
            Assert.assertEquals(account_deletion_result, "Customer could not be deleted!!. First delete all accounts of this customer then delete the customer");
            deletion_result.accept();
        }else if (Objects.equals(account_deletion_result, "Customer does not exist!!")) {

            Assert.assertEquals(account_deletion_result, "Customer does not exist!!");
            deletion_result.accept();
        }else {
            Assert.assertEquals(account_deletion_result, "Customer deleted Successfully");
            deletion_result.accept();
        }
    }

    @Test(priority = 10, description ="Editing Deleted Customer Details and Checking Status")
    public void Editing_Deleted_Customer_Details() {
//        Utility.Login(brw.driver);

        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.customer_edit_btn)).click();
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.edit_customer_id_input)).sendKeys(cust_id);
        brw.driver.findElement(By.xpath(CustomerAndAccountsSelector.edit_customer_submit)).click();
        Alert customer_edit_alert = brw.driver.switchTo().alert();
        Assert.assertEquals(customer_edit_alert.getText(),"Customer does not exist!!");

    }


    @DataProvider(name = "custIDs")
    public Object[] data() {

        Object[] customerID = new Object[3];
        //Valid User ID & Pass
        customerID[0] = "98292";
        customerID[1] = "102938";
        customerID[2] = cust_id;

        return customerID;
    }

    @AfterClass
    public void closeBrowser(){

        brw.driver.close();
        brw.driver.quit();

    }

}
