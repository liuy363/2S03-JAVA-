import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

class Car extends Insurable {

    private String make;
    private String model;
    private Date purchaseDate;
    private String plateNumber;
    private long mileage;
    private long value;
    private String owner;

    static final String inputTag = "CAR";

    Car(HashMap<String, Tag> tags) throws ParseException {
        super(tags);
        make = tags.get("MAKE").getValue();
        model = tags.get("MODEL").getValue();
        plateNumber = tags.get("PLATE_NUMBER").getValue();
        purchaseDate = Utils.convertDate(tags.get("PURCHASE_DATE").getValue());
        mileage = Long.parseLong(tags.get("MILEAGE").getValue());
        value = Long.valueOf(tags.get("VALUE").getValue());
        owner = tags.get("OWNER_NAME").getValue();
    }

    public String getOwner() { return owner; }

    public String getOwnerName() {
        return ownerName;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public long getMileage() {
        return mileage;
    }

    public static String getInputTag() {
        return inputTag;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public long getCarValue() { return value; }
}

