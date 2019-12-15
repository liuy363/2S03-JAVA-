public class Trip {

    //Car and its details.
    private Car car;

    // Plate number, which is supposed to be unique among all cars.
    private int plateNumber;

    //Travel distance
    private float travelDistance;

    Trip(Car car, float travelDistance){
        this.car = car;
        this.travelDistance = travelDistance;
    }

    int getPlateNumber() {
        return car.getPlateNumber();
    }

    float getTravelDistance(){
        return travelDistance;
    }
}
