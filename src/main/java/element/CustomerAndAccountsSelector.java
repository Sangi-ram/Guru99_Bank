package element;

public class CustomerAndAccountsSelector {

    public String uid = "/html/body/form/table/tbody/tr[1]/td[2]/input";
    public String passd = "/html/body/form/table/tbody/tr[2]/td[2]/input";
    public String login_btn = "/html/body/form/table/tbody/tr[3]/td[2]/input[1]";
    public String logout_btn = "/html/body/div[3]/div/ul/li[15]/a";
    public String manager_id = "/html/body/table/tbody/tr/td/table/tbody/tr[3]/td";

    //CUSTOMER INPUT FORM
    public String new_cstmr_buttn = "/html/body/div[3]/div/ul/li[2]/a";
    public String icust_name = "/html/body/table/tbody/tr/td/table/tbody/tr[4]/td[2]/input";
    public String igender = "/html/body/table/tbody/tr/td/table/tbody/tr[5]/td[2]/input[2]";
    public String idob = "//*[@id=\"dob\"]";
    public String iaddress = "/html/body/table/tbody/tr/td/table/tbody/tr[7]/td[2]/textarea";
    public String icity = "/html/body/table/tbody/tr/td/table/tbody/tr[8]/td[2]/input";
    public String istate = "/html/body/table/tbody/tr/td/table/tbody/tr[9]/td[2]/input";
    public String ipin = "/html/body/table/tbody/tr/td/table/tbody/tr[10]/td[2]/input";
    public String iphone = "/html/body/table/tbody/tr/td/table/tbody/tr[11]/td[2]/input";
    public String ie_mail = "/html/body/table/tbody/tr/td/table/tbody/tr[12]/td[2]/input";
    public String icust_passwd = "/html/body/table/tbody/tr/td/table/tbody/tr[13]/td[2]/input";
    public String icust_submit = "/html/body/table/tbody/tr/td/table/tbody/tr[14]/td[2]/input[1]";

    //CUSTOMER DISPLAY FORM
    public String ocust_id = "//*[@id=\"customer\"]/tbody/tr[4]/td[2]";

    //NEW ACCOUNT
    public String add_account_btn = "/html/body/div[3]/div/ul/li[5]/a";
    public String acust_id = "/html/body/table/tbody/tr/td/table/tbody/tr[2]/td[2]/input";
    public String acc_typ = "/html/body/table/tbody/tr/td/table/tbody/tr[3]/td[2]/select";
    public String initial_deposit = "/html/body/table/tbody/tr/td/table/tbody/tr[4]/td[2]/input";
    public String new_acc_submit = "/html/body/table/tbody/tr/td/table/tbody/tr[5]/td[2]/input[1]";
    //ACCOUNT DISPLAY FORM
    public String acc_id = "//*[@id=\"account\"]/tbody/tr[4]/td[2]";
    public String acc_cust_id = "//*[@id=\"account\"]/tbody/tr[5]/td[2]";

    //DELETE ACCOUNT
    public String acc_delete_btn = "/html/body/div[3]/div/ul/li[7]/a";
    public String account_num_input = "/html/body/table/tbody/tr/td/table/tbody/tr[6]/td[2]/input";
    public String account_dlt_submit = "/html/body/table/tbody/tr/td/table/tbody/tr[11]/td[2]/input[1]";

    //MINI STATEMENT
    public String mini_stmt_btn = "/html/body/div[3]/div/ul/li[13]/a";
    public String macc_no_inp = "/html/body/table/tbody/tr/td/table/tbody/tr[6]/td[2]/input";
    public String msubmit = "/html/body/table/tbody/tr/td/table/tbody/tr[11]/td[2]/input[1]";

    //BALANCE ENQUIRY
    public String bal_enquiry_btn = "/html/body/div[3]/div/ul/li[12]/a";
    public String bal_acc_no_inp = "/html/body/table/tbody/tr/td/table/tbody/tr[6]/td[2]/input";
    public String bal_submit = "/html/body/table/tbody/tr/td/table/tbody/tr[11]/td[2]/input[1]";

    //CUSTOM STATEMENT
    public String custom_stmt_btn = "/html/body/div[3]/div/ul/li[14]/a";
    public String custom_acc_no_inp = "/html/body/table/tbody/tr/td/table/tbody/tr[6]/td[2]/input";
    public String from_date = "/html/body/table/tbody/tr/td/table/tbody/tr[7]/td[2]/input";
    public String to_date = "/html/body/table/tbody/tr/td/table/tbody/tr[8]/td[2]/input";
    public String min_trans = "/html/body/table/tbody/tr/td/table/tbody/tr[9]/td[2]/input";
    public String noOfTrans = "/html/body/table/tbody/tr/td/table/tbody/tr[10]/td[2]/input";
    public String custom_submit = "/html/body/table/tbody/tr/td/table/tbody/tr[13]/td[2]/input[1]";

    //DELETE CUSTOMER
    public String delete_customer_btn = "//a[@href=\"DeleteCustomerInput.php\"]";
    public String delete_customer_id = "//input[@onkeyup=\"validatecustomerid();\"]";
    public String delete_submit_btn = "//input[@name=\"AccSubmit\"]";

    //EDITING CUSTOMER
    public String customer_edit_btn = "//a[@href=\"EditCustomer.php\"]";
    public String edit_customer_id_input = "//input[@name=\"cusid\"]";
    public String edit_customer_submit = "//input[@name=\"AccSubmit\"]";
    public String editAddress = "//textarea[@name=\"addr\"]";
    public String editCity = "//input[@name=\"city\"]";
    public String editState = "//input[@name=\"state\"]";
    public String editPIN = "//input[@name=\"pinno\"]";
    public String editTeleNumber = "//input[@name=\"telephoneno\"]";
    public String editEMail = "//input[@name=\"emailid\"]";
    public String editSubmit = "//input[@value=\"Submit\"]";
    public String editSuccess = "//p[@class=\"heading3\"]";

}
