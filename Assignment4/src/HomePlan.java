import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;


class HomePlan extends Plan {
    static final String inputTag = "HOME_PLAN";
    //create new object
    private RangeCriterion homeValueCriterion = new RangeCriterion();
    private RangeCriterion homeAgeCriterion = new RangeCriterion();

    //if home.value/age exist in tags add them to object homevalueCriterion
    HomePlan(HashMap<String, Tag> tags) {
        super(tags);

        if (tags.get("HOME.VALUE") != null) {
            homeValueCriterion.addCriterion(tags.get("HOME.VALUE"));
        }
        if (tags.get("HOME.AGE") != null) {
            homeAgeCriterion.addCriterion(tags.get("HOME.AGE"));
        }
    }

    @Override
    //check if home is insurable, if not make it insurable, also if value not in range return false
    boolean isEligible(Insurable insurable, Date date) {
        if (!(insurable instanceof Home))
            return false;
        Home home = (Home) insurable;
        if (!homeValueCriterion.isInRange(home.getValue()))
            return false;

        // Extracting the age of the house
        LocalDate localCurrentDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localBuiltDate = home.getBuildDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long age = localCurrentDate.getYear() - localBuiltDate.getYear();;
        // Checking if the age is in the range.
        return homeAgeCriterion.isInRange(age);
    }

    @Override
    Insurable getInsuredItem(Customer customer, Database database) {
        return database.getHome(customer.getName());
    }

}
