import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class CarPlan extends Plan {
    static final String inputTag = "CAR_PLAN";
    RangeCriterion mileageCriterion = new RangeCriterion();

    CarPlan(HashMap<String, Tag> tags) {
        super(tags);
        /*
        check if there is a maximum/minimum bound of mileage
        if there is, add max/min to the range criterion
         */
        if (tags.get("CAR.MILEAGE.MAX") != null) {
            mileageCriterion.addCriterion(tags.get("CAR.MILEAGE.MAX"));
        }
        if (tags.get("CAR.MILEAGE.MIN") != null) {
            mileageCriterion.addCriterion(tags.get("CAR.MILEAGE.MIN"));
        }
    }

    @Override
    boolean isEligible(Insurable insurable, Date date) {
        if (!(insurable instanceof Car))
            return false;
        Car car = (Car) insurable;
        return (mileageCriterion.isInRange(car.getMileage()) && customerWealthCriterion.isInRange(Database.getCustomer(car.getOwner()).getWealth()));
    }

    @Override
    ArrayList<? extends Insurable> getInsuredItems(Customer customer, Database database) {
        return database.getCarsByOwnerName(customer.getName());
    }

    @Override
    Insurable getInsuredItem(String insurableID, Database database) {
        return database.getCarByPlateNumber(insurableID);
    }
}
