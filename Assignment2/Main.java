import java.util.*;
public class Main {
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        //put the carmodels, cars, outputs in lists
        List<CarModel> modelList = new ArrayList<CarModel>();
        List<Car> carList = new ArrayList<Car>();
        List<String> outputList = new ArrayList<String>();
        List<Trip> tripList = new ArrayList<Trip>();

        //check if "FINISH" is entered
        boolean endofinput = false;
        while (endofinput == false) {

            //get the input as string and split it by space into an array call parts
            String input = keyboard.nextLine();
            String[] parts = input.split(" ");

            if (parts[0].equals("MODEL")) {

                //get the name, fuelEconomy, gasTankSize from input array
                String name = parts[1];  //second element in array, index 1
                Double fuelEconomy = Double.parseDouble(parts[2]); //convert string to double
                Double gasTankSize = Double.parseDouble(parts[3]);
                
                //create new CarModel
                CarModel model1 = new CarModel(name, fuelEconomy, gasTankSize); 
                //add that carmodel to list
                modelList.add(model1); 

            }
            if (parts[0].equals("CAR")) {

                 //get the modelname and platenumber from input array
                String modelName = parts[1];
                int plateNumber = Integer.parseInt(parts[2]);  //convert string to int
                CarModel carModel = whichModel(modelName, modelList); //find the carmodel with inputted modelname
                Car car = new Car(carModel, plateNumber); //create new Car
                carList.add(car);

            }
            if (parts[0].equals("TRIP")) {

                //get the platenumber and distance from input array
                int plateNumber = Integer.parseInt(parts[1]);                
                float distance = Float.parseFloat(parts[2]);

                Car car = whichPlateNum(plateNumber, carList); //find the car with inputted platenumber
                String output;
                boolean result = car.trip(distance); //call the trip function defined in car
                
                if (result == true) {
                    output = "Trip completed successfully for #" + parts[1];
                    outputList.add(output);
                } else {
                    output = "Not enough fuel for #" + parts[1];
                    outputList.add(output);
                }
                Trip trip = new Trip(car, distance);
                tripList.add(trip);

            }
            if (parts[0].equals("REFILL")) {

                /*get the plate number of the car need to be refilled
                call it plateNumber1*/
                int plateNumber1 = Integer.parseInt(parts[1]);
                //find the car with that plate number
                Car car1 = whichPlateNum(plateNumber1, carList);
                //call the refill function defined in car
                car1.refill();
                
            }
            if (parts[0].equals("LONGTRIPS")) {

                /*get the platenumber and distance here need to be checked
                call it plateNumber2 and traceldistance*/
                int plateNumber2 = Integer.parseInt(parts[1]);
                float traveldistance = Float.parseFloat(parts[2]);

                String output;
                int length = tripList.size();
                int counter = 0;

                //loop through every element in tripList
                for (int i = 0; i <= length - 1; i = i + 1) {
                    
                    //get the trip distance of cars
                    double tripDistance = tripList.get(i).getTravelDistance();
                    
                    //function defined below, find the car with inputted platenumber
                    Car car = whichPlateNum(plateNumber2, carList);
                    
                    //call the trip function defined in car
                    boolean result = car.trip(tripDistance);
                    
                    //the platenumber in tripList
                    int platenumber3 = tripList.get(i).getPlateNumber();
                    
                    //travel distance in tripList
                    float traveldistance2 = tripList.get(i).getTravelDistance();

                    /*check if the input platenumber is same as the one in tripList 
                    and if distance travelled exceed input distance*/
                    if ((plateNumber2 == platenumber3) && (traveldistance2 >= traveldistance)) {
                        //check if the trip success
                        if (!outputList.get(i).equals("Not enough fuel for #" + platenumber3)) {
                                counter += 1;
                        }
                    }
                }
                output = "#" + plateNumber2 + " made " + counter + " trips longer than " + Math.round(traveldistance); //get rid of decimal
                outputList.add(output);
            }
            else if (parts[0].equals("FINISH")) { //reach the end of input
                endofinput = true;
                for (String output : outputList) {
                    System.out.println(output);
                }
            }
        }
    }


    //Methods defined
    //This one find the car model in car model list according to model name
    private static CarModel whichModel(String modelName, List<CarModel> modelList) {
        for (CarModel model : modelList) {
            String name = model.getName();
            if (modelName.equals(name)) {
                return model;
            }
        }
        return null; //this wont happen
    }

    //This function find the car in car list according to plate number
    private static Car whichPlateNum(int plateNumber, List<Car> carList) {
        for (Car model : carList) {
            int number = model.getPlateNumber();
            if (plateNumber == number) {
                return model;
            }
        }
        return null;
    }
}

//test cases
//MODEL Camry 6.5 58
//MODEL Civic 7.5 52
//CAR Camry 1111
//CAR Civic 4444
//TRIP 1111 50
//TRIP 4444 50
//TRIP 1111 350
//TRIP 4444 340
//TRIP 1111 330
//TRIP 4444 320
//LONGTRIPS 1111 300
//LONGTRIPS 4444 300
//FINISH