import java.util.ArrayList;

class Database {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Home> homes = new ArrayList<>();
    private static ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Plan> plans = new ArrayList<>();
    private ArrayList<Contract> contracts = new ArrayList<>();
    private ArrayList<Claim> claims = new ArrayList<>();
    private static ArrayList<Company> companys = new ArrayList<>();//create company ArrayList

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

    void insertCompany(Company company) { companys.add(company); }//added company to that arraylist

    /*
    This method update wealth, given a name and value.
    base case is checking if the name is a customer,
    if so use the update method in customer class to update the wealth
    if the name is a company,
    find the owner of the company and call updateWealth function to recursion again.
     */
    static void updateWealth(String customerName, long value) {
        if (getCustomer(customerName) != null) {
            getCustomer(customerName).updateWealth(value);
        }
        if (getCustomer(customerName) == null) {
            Company company = getCompany(customerName);
            String newname = company.getOwner_name();
            updateWealth(newname,value);
        }
    }

    //get the company object
    static Company getCompany(String input) {
        for (Company company :companys) {
            if (company.getCompany_name().equals(input))
                return company;
        }
        return null;
    }

    Plan getPlan(String name) {
        for (Plan plan : plans) {
            if (plan.name.equals(name))
                return plan;
        }
        return null;
    }

    static Customer getCustomer(String name) {
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

    /* This function has been updated to output a list
    of homes rather than a single home. In other words,
    an owner may own multiple homes.
     */
    static ArrayList<Home> getHomesByOwnerName(String ownerName) {
        ArrayList<Home> result = new ArrayList<>();
        for (Home home : homes) {
            if (home.getOwnerName().equals(ownerName))
                result.add(home);
        }
        return result;
    }


    /* This function has been updated to output a list
    of homes rather than a single home. In other words,
    an owner may own multiple homes.
     */
    static ArrayList<Car> getCarsByOwnerName(String ownerName) {
        ArrayList<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getOwnerName().equals(ownerName))
                result.add(car);
        }
        return result;
    }


    /* This function has been updated to output a list
    of companies rather than a single companies. In other words,
    an own may own multiple companies.
     */
    static ArrayList<Company> getCompaniesByOwnerName(String ownerName) {
        ArrayList<Company> result = new ArrayList<>();
        for (Company company : companys) {
            if (company.getOwner_name().equals(ownerName))
                result.add(company);
        }
        return result;
    }




    long totalClaimAmountByCustomer(String customerName) {
        long totalClaimed = 0;
        for (Claim claim : claims) {
            if (getContract(claim.getContractName()).getCustomerName().equals(customerName))
                totalClaimed += claim.getAmount();
        }
        return totalClaimed;
    }

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

    Insurable getCarByPlateNumber(String insurableID) {
        for (Car car : cars) {
            if (car.getPlateNumber().equals(insurableID))
                return car;
        }
        return null;
    }

    Insurable getHomeByPostalCode(String insurableID) {
        for (Home home : homes) {
            if (home.getPostalCode().equals(insurableID))
                return home;
        }
        return null;
    }
}
