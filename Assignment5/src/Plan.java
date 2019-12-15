import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

abstract class Plan {
    String name;
    long premium;
    long maxCoveragePerClaim;
    long deductible;
    RangeCriterion customerAgeCriterion = new RangeCriterion();
    RangeCriterion customerIncomeCriterion = new RangeCriterion();
    RangeCriterion customerWealthCriterion = new RangeCriterion();

    Plan(HashMap<String, Tag> tags) {
        name = tags.get("NAME").getValue();
        premium = Integer.parseInt(tags.get("PREMIUM").getValue());
        maxCoveragePerClaim = Integer.parseInt(tags.get("MAX_COVERAGE_PER_CLAIM").getValue());
        deductible = Integer.parseInt(tags.get("DEDUCTIBLE").getValue());

        /*
        check if there is a maximum/minimum bound of customer age and wealth,
        if there is, add max/min to the range criterion
         */
        if (tags.get("CUSTOMER.AGE.MAX") != null) {
            customerAgeCriterion.addCriterion(tags.get("CUSTOMER.AGE.MAX"));
        }
        if (tags.get("CUSTOMER.AGE.MIN") != null) {
            customerAgeCriterion.addCriterion(tags.get("CUSTOMER.AGE.MIN"));
        }
        if (tags.get("CUSTOMER.INCOME") != null) {
            customerIncomeCriterion.addCriterion(tags.get("CUSTOMER.INCOME"));
        }
        if (tags.get("CUSTOMER.WEALTH.MIN") != null) {
            customerWealthCriterion.addCriterion(tags.get("CUSTOMER.WEALTH.MIN"));
        }
        if (tags.get("CUSTOMER.WEALTH.MAX") != null) {
            customerWealthCriterion.addCriterion(tags.get("CUSTOMER.WEALTH.MAX"));
        }

    }

    abstract boolean isEligible(Insurable insurable, Date date);

    abstract ArrayList<? extends Insurable> getInsuredItems(Customer customer, Database database);

    abstract Insurable getInsuredItem(String insurableID, Database database);

    boolean isEligible(Customer customer, Date currentDate) throws ParseException {
        // Extracting the age of the customer
        LocalDate localCurrentDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localBirthDate = customer.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long age = localCurrentDate.getYear() - localBirthDate.getYear();
        //convert the yyyy-mm-dd format date to string
        String birthD = localBirthDate.toString();
        String currentD = localCurrentDate.toString();

        //get the separate birth year/month/day
        int birthYear = Integer.parseInt(birthD.substring(0,4));
        int birthMonth = Integer.parseInt(birthD.substring(5,7));
        int birthDay = Integer.parseInt(birthD.substring(8));

        //get the separate current year/month/day
        int currentYear = Integer.parseInt(currentD.substring(0,4));
        int currentMonth = Integer.parseInt(currentD.substring(5,7));
        int currentDay = Integer.parseInt(currentD.substring(8));

        //when the age is smaller than 18
        if (currentYear - birthYear < 18) {
            return false;
        }
        //when he is in the year of 18 but haven't reach birthday
        if  (currentYear - birthYear == 18 && (currentMonth < birthMonth || currentDay < birthDay)) {
            return false;
        }
        else {
            //go to check if the age in range
            if (!customerAgeCriterion.isInRange(age)) {
                return false;
            }
            return customerIncomeCriterion.isInRange(customer.getIncome());

        }


    }

    String getName() {
        return name;
    }

    long getPremium() {
        return premium;
    }

    long getMaxCoveragePerClaim() {
        return maxCoveragePerClaim;
    }

    long getDeductible() {
        return deductible;
    }
}
