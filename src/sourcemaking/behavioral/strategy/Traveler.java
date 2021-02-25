package sourcemaking.behavioral.strategy;

public class Traveler {
    private String name;
    private String status;
    private TransportationToAirportStrategy transMode;

    public Traveler(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public void setStatus(String status) {
        System.out.println(name + " 变成了 " + status);
        this.status = status;
    }

    public void toAirport() {
        System.out.print(name + "（" + status + "）");
        transMode.transportToAirport();
    }

    public void chooseStrategy(TransportationToAirportStrategy strategy) {
        transMode = strategy;
    }
}
