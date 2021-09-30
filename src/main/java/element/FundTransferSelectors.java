package element;

public class FundTransferSelectors {

    //FUND TRANSFER
    public String fundTransferBtn = "//a[@href=\"FundTransInput.php\"]";
    public String payersAccount = "//input[@name=\"payersaccount\"]";
    public String payeeAccount = "//input[@name=\"payeeaccount\"]";
    public String paymentAmount = "//input[@name=\"ammount\"]";
    public String fundTransferDescription = "//input[@name=\"desc\"]";
    public String fundTransferSubmit = "//input[@name=\"AccSubmit\"]";
    public String payeeAssert = "/html/body/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]";

    //DEPOSIT FUNDS
    public String depositBtn = "//a[@href=\"DepositInput.php\"]";
    public String depositAccountNumber = "//input[@name=\"accountno\"]";
    public String depositAmount = "//input[@name=\"ammount\"]";
    public String depositDescription = "//input[@name=\"desc\"]";
    public String depositSubmitBtn = "//input[@name=\"AccSubmit\"]";
    public String depositAssert = "//p[@class=\"heading3\"]";

}
