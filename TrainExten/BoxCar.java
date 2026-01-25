public class BoxCar extends TrainCar {
    protected TrainCar itsnextconnectedCar;

    public BoxCar(TrainCar nextCar, double initialDist) {
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
        if (itsNextConnectedCar instanceof BoxCar|| itsNextConnectedCar instanceof Caboose) {
            return itsNextConnectedCar.isMemberOfValidTrain();
        }
        else {
            return false;
        }
    }
}