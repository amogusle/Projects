public class Caboose extends TrainCar {
    protected TrainCar itsnextconnectedCar;

    public Caboose(TrainCar nextCar, double initialDist) {
        super(nextCar, initialDist);
        this.itsnextconnectedCar= nextCar;
    }
   
    @Override
    public void advance(double howFar) {
        itsDistanceFromHome += howFar;
    }
   
    @Override
    public Boolean isMemberOfValidTrain() {
        if (itsnextconnectedCar== null) {
            return true;
        }
        else {
            return false;
        }
    }
}