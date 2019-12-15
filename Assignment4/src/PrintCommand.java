import java.text.ParseException;

class PrintCommand extends Command {
    private String entityType;
    private String queryType;
    private String queryValue;

    //let those be the second, third and forth element
    PrintCommand(String[] tokens) {
        super();
        entityType = tokens[1];
        queryType = tokens[2];
        queryValue = tokens[3];
    }

    @Override
    //check what is the entityType and call corresponding function to print
    //override override the function if it is define elsewhere
    void run(Database database) {
        if (entityType.equals("CUSTOMER"))
            runPrintCustomer(database);
        else if (entityType.equals("PLAN"))
            runPrintPlan(database);
        else {
            throw new BadCommandException("Bad print command.");
        }
    }

    //check the queryType and print corresponding sentence
    private void runPrintCustomer(Database database) {
        if (queryType.equals("TOTAL_CLAIMED")) {
            System.out.println("Total amount claimed by " + database.getCustomer(queryValue).getName() +
                    " is " + database.totalClaimAmountByCustomer(queryValue));
        } else if (queryType.equals("TOTAL_RECEIVED")) {
            System.out.println("Total amount received by " + database.getCustomer(queryValue).getName() +
                            " is " + database.totalReceivedAmountByCustomer(queryValue));
        }
    }

    //TODO
    //countContract()and CountPay() are defined in database, they are use to count the contract number and paid value
    private void runPrintPlan(Database database) {
        if (queryType.equals("NUM_CUSTOMERS")) {

            System.out.println("Number of customers under " + database.getPlan(queryValue).getName() +
                    " is " + database.countContract(queryValue));

        } else if (queryType.equals("TOTAL_PAYED_TO_CUSTOMERS")) {
            System.out.println("Total amount payed under " + database.getPlan(queryValue).getName() +
                    " is " + database.countPay(queryValue));
        }
    }
}
