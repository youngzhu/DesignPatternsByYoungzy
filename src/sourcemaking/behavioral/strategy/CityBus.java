package sourcemaking.behavioral.strategy;

public class CityBus implements TransportationToAirportStrategy {
    @Override
    public void transportToAirport() {
        System.out.println("乘公交车去机场");
    }
}
