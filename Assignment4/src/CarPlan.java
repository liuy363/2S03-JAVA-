
import java.util.Date;
import java.util.HashMap;;

//this class is base on Plan class
class CarPlan extends Plan {
    //this is unchange
    static final String inputTag = "CAR_PLAN";
    //create new object
    RangeCriterion mileageCriterion = new RangeCriterion();


    CarPlan(HashMap<String, Tag> tags) {
        super(tags); //call super class tags constructor

        //if the mileage of car hashmap exist then add it to criterion
        if (tags.get("CAR.MILEAGE") != null) {
            mileageCriterion.addCriterion(tags.get("CAR.MILEAGE"));
        }
    }

    @Override
    //check if it is eligible by checking if it is an instance of Car
    boolean isEligible(Insurable insurable, Date date) {
        if (!(insurable instanceof Car))
            return false;
        Car car = (Car) insurable;
        return mileageCriterion.isInRange(car.getMileague());
    }

    @Override
    //return the name of customer
    Insurable getInsuredItem(Customer customer, Database database) {
        return database.getCar(customer.getName());
    }
}
