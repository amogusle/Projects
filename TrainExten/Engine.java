public class Engine extends TrainCar {
    protected TrainCar itsnextconnectedCar;

    public Engine (TrainCar nextCar, double initialDist) {
        super(nextCar, initialDist);
        this.itsnextconnectedCar= nextCar;
    }

    @Override
    public void advance(double howFar) {
        itsDistanceFromHome += howFar;
        if (itsNextConnectedCar != null) {
            itsNextConnectedCar.advance(howFar);
        }
    }

    @Override
    public Boolean isMemberOfValidTrain() {
        if (itsNextConnectedCar instanceof BoxCar) {
            return itsNextConnectedCar.isMemberOfValidTrain();
        }
        else {
            return false;
        }

        
    }
}
