package sourcemaking.behavioral.strategy;

public class Client {
    public static void main(String[] args) {
        /*
        Traveler 与具体的交通方式解耦。
        增加新的交通方式，不会影响Traveler类。
         */
        Traveler traveler = new Traveler("张三", "学生");
        traveler.chooseStrategy(new CityBus());
        traveler.toAirport();

        traveler.setStatus("打工人");
        traveler.chooseStrategy(new Taxi());
        traveler.toAirport();

        traveler.setStatus("老板");
        traveler.chooseStrategy(new PersonalCar());
        traveler.toAirport();
    }
}
