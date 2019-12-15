import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

class Home extends Insurable {
    private String postalCode;
    private Date buildDate;
    private long value;

    static final String inputTag = "HOME";

    Home(HashMap<String, Tag> tags) throws ParseException {
        super(tags);
        postalCode = tags.get("POSTAL_CODE").getValue();
        buildDate = Utils.convertDate(tags.get("BUILD_DATE").getValue());
        value = Long.valueOf(tags.get("VALUE").getValue());
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public static String getInputTag() {
        return inputTag;
    }

    public long getHomeValue() { return value; }
}
