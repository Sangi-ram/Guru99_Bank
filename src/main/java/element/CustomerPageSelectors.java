package element;

public class CustomerPageSelectors {

    //BALANCE CHECK
    public String balanceEnquiryBtn = "//a[@href=\"BalEnqInput.php\"]";
    public String accountNOSelect = "//select[@name=\"accountno\"]";
    public String balanceSubmit = "//input[@name=\"AccSubmit\"]";
    public String balanceAssert = "//p[@class=\"heading3\"]";

    //MINI STATEMENT
    public String miniStatementBtn = "//a[@href=\"MiniStatementInput.php\"]";
    public String miniStatementAccount = "//input[@name=\"accountno\"]";
    public String miniStatementSubmitBtn = "//input[@name=\"AccSubmit\"]";
    public String miniStatementAssert = "//p[@class=\"heading3\"]";

    //FUND TRANSFER
    public String fundTransferBtn = "//a[@href=\"customerfundinput.php\"]";
    public String payersAccountNO = "//input[@name=\"payersaccount\"]";
    public String payeesAccountNO = "//input[@name=\"payeeaccount\"]";
    public String amount = "//input[@name=\"ammount\"]";
    public String description = "//input[@name=\"desc\"]";
    public String fundTransferSubmitBtn = "//input[@name=\"AccSubmit\"]";
    public String fundTransferAssert = "//p[@class=\"heading3\"]";

    //CUSTOM STATEMENT
    public String customStatementBtn = "//a[@href=\"CustomisedStatementInput.php\"]";
    public String customStatementAccount = "//input[@name=\"accountno\"]";
    public String fromDate = "//input[@name=\"fdate\"]";
    public String toDate = "//input[@name=\"tdate\"]";
    public String minimunTransactionAmount = "//input[@name=\"amountlowerlimit\"]";
    public String noOfTransaction = "//input[@name=\"numtransaction\"]";
    public String customStatementSubmitBtn = "//input[@name=\"AccSubmit\"]";


}
