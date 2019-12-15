import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

class Claim {
    private String contractName;
    private long amount;
    private Date date;
    private boolean successful;

    static final String inputTag = "CLAIM";

    //get these variables
    Claim(HashMap<String, Tag> tags) throws ParseException {
        contractName = tags.get("CONTRACT_NAME").getValue();
        date = Utils.convertDate(tags.get("DATE").getValue());
        amount = Long.parseLong(tags.get("AMOUNT").getValue());
    }

    /*
    methods to return this variables
     */
    public String getContractName() {
        return contractName;
    }

    public long getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public boolean wasSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
