package sourcemaking.behavioral.strategy;

public class Taxi implements TransportationToAirportStrategy {
    @Override
    public void transportToAirport() {
        System.out.println("打车去机场");
    }
}