package sourcemaking.behavioral.strategy;

public class PersonalCar implements TransportationToAirportStrategy {
    @Override
    public void transportToAirport() {
        System.out.println("乘私家车去机场");
    }
}