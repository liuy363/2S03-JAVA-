import java.text.ParseException;
import java.util.HashMap;

/*
This is the company class and it extends the insurable class
 */

public class Company extends Insurable {

    private String company_name;

    static final String inputTag = "COMPANY";

    Company(HashMap<String, Tag> tags) throws ParseException {
        super(tags);
        company_name = tags.get("COMPANY_NAME").getValue();
    }

    public String getCompany_name(){ return company_name; }

    public String getOwner_name(){ return ownerName; }

    public long getValue(){ return value; }

    public static String getInputTag() { return inputTag; }
}
