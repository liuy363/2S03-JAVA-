import java.util.ArrayList;
//this is the database that contain multi arraylist
class Database {
    //creating arraylist
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Home> homes = new ArrayList<>();
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Plan> plans = new ArrayList<>();
    private ArrayList<Contract> contracts = new ArrayList<>();
    private ArrayList<Claim> claims = new ArrayList<>();

    /*
    these methods  add new element to arraylist
     */
    void insertHome(Home home) {
        homes.add(home);
    }

    void insertCar(Car car) {
        cars.add(car);
    }

    void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    void insertPlan(Plan plan) {
        plans.add(plan);
    }

    void insertClaim(Claim claim) {
        claims.add(claim);
    }

    void insertContract(Contract contract) {
        contracts.add(contract);
    }


    /*
    this method loop through every element in contract arraylist to check
    if the contract's plan match the plan we are looking for counter + 1
    and add the contract to countedPpl arraylist to avoid multiple count
     */
    ArrayList<Contract> countedPpl = new ArrayList<>();
    int countContract(String plan) {
        int counter = 0;
        for (Contract contract: contracts) {
            if (contract.getPlanName().equals(plan) && !(countedPpl.contains(contract))){
                countedPpl.add(contract);
                counter+=1;
            }
        }
        return counter;
    }

    /*
    this method loop through every element in claim arraylist and use
    getcontract function to get contract class he is in
    if the contract's plan match the plan we are looking for counter + 1
    and add the contract to countedPpl arraylist to avoid multiple count
     */
    ArrayList<Claim> countedPay = new ArrayList<>();
    int countPay(String plan) {
        int received = 0;
        for (Claim claim: claims) {
            if (getContract(claim.getContractName()).getPlanName().equals(plan) && !(countedPay.contains(claim))){
                if (claim.wasSuccessful()) {
                    long deductible = getPlan(getContract(claim.getContractName()).getPlanName()).getDeductible();
                    received += Math.max(0, claim.getAmount() - deductible);
                }
                countedPay.add(claim);
            }
        }
        return received;
    }


    /*
    these functins get the plan,customer and contract according to input name if it exist
     */
    Plan getPlan(String name) {
        for (Plan plan : plans) {
            if (plan.name.equals(name))
                return plan;
        }
        return null;
    }

    Customer getCustomer(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name))
                return customer;
        }
        return null;
    }

    Contract getContract(String name) {
        for (Contract contract : contracts) {
            if (contract.getContractName().equals(name))
                return contract;
        }
        return null;
    }

    /**
     * There is at most one home owned by each person.
     */
    Home getHome(String ownnerName) {
        for (Home home : homes) {
            if (home.getOwnerName().equals(ownnerName))
                return home;
        }
        return null;
    }

    /**
     * There is at most one car owned by each person.
     */
    Car getCar(String ownnerName) {
        for (Car car : cars) {
            if (car.getOwnerName().equals(ownnerName))
                return car;
        }
        return null;
    }

    //if the contract name is the same as customer name add total claimed
    long totalClaimAmountByCustomer(String customerName) {
        long totalClaimed = 0;
        for (Claim claim : claims) {
            if (getContract(claim.getContractName()).getCustomerName().equals(customerName))
                totalClaimed += claim.getAmount();
        }
        return totalClaimed;
    }

    //calculate the amount received by total claim-dedutible
    long totalReceivedAmountByCustomer(String customerName) {
        long totalReceived = 0;
        for (Claim claim : claims) {
            Contract contract = getContract(claim.getContractName());
            if (contract.getCustomerName().equals(customerName)) {
                if (claim.wasSuccessful()) {
                    long deductible = getPlan(contract.getPlanName()).getDeductible();
                    totalReceived += Math.max(0, claim.getAmount() - deductible);
                }
            }
        }
        return totalReceived;
    }
}
