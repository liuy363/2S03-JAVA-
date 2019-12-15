import java.text.ParseException;
import java.util.HashMap;

class BlockCommand extends Command {
    private String blockType;
    private HashMap<String, Tag> tags = new HashMap<>();  //create new hashmap name tags

    BlockCommand(String blockType) {
        this.blockType = blockType;
    }

    //inserting key name (tag.getName()) with new value tag
    void addTag(Tag tag) {
        tags.put(tag.getName(), tag);
    }

    String getBlockType() {
        return blockType;
    }

    @Override
    //throws get rid of parse exception
    //check the blocktype and call the methods in databse which add them to corresponding arraylist
    void run(Database database) throws ParseException {
        if (blockType.equals(Customer.inputTag)) {
            database.insertCustomer(new Customer(tags));
        } if (blockType.equals(Home.inputTag)) {
            database.insertHome(new Home(tags));
        } if (blockType.equals(Car.inputTag)) {
            database.insertCar(new Car(tags));
        } if (blockType.equals(Claim.inputTag)) {
            Claim claim = new Claim(tags);
            database.insertClaim(claim);

            //if income and age both in range
            if (processClaim(claim, database)) {
                claim.setSuccessful(true);   //mark it as successful
                //print the day and name
                System.out.println("Claim on " + Utils.formattedDate(claim.getDate())
                        + " for contract " + claim.getContractName() + " was successful.");
            } else {
                claim.setSuccessful(false);
                System.out.println("Claim on " + Utils.formattedDate(claim.getDate())
                        + " for contract " + claim.getContractName() + " was not successful.");
            }
        } if (blockType.equals(Contract.inputTag)) {
            database.insertContract(new Contract(tags));
        } if (blockType.equals(HomePlan.inputTag)) {
            database.insertPlan(new HomePlan(tags));
        } if (blockType.equals(CarPlan.inputTag)) {
            database.insertPlan(new CarPlan(tags));
        }
    }


    private boolean processClaim(Claim claim, Database database) {
        //create new objects by using the methods in database/plan
        Contract contract = database.getContract(claim.getContractName());
        Customer customer = database.getCustomer(contract.getCustomerName());
        Plan plan = database.getPlan(contract.getPlanName());
        Insurable insurable = plan.getInsuredItem(customer, database);

        // If the claimed amount is higher than covered by the plan.
        if (plan.getMaxCoveragePerClaim() < claim.getAmount())
            return false;

        // If the claim date is not in the contract period.
        if (claim.getDate().after(contract.getEndDate()) || claim.getDate().before(contract.getStartDate()))
            return false;

        // If the customer was not eligible.
        if (!plan.isEligible(customer, claim.getDate()))
            return false;

        //return if age and income are in the range
        return plan.isEligible(insurable, claim.getDate());
    }
}
