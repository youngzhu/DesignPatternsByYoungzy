package sourcemaking.creational.builder;

/**
 * 核查清单 Step 2
 * 店员 Reader,Director
 */
public class Cashier {
    private RestaurantCrew crew;

    public Cashier(RestaurantCrew crew) {
        this.crew = crew;
    }

    public void service() {
        crew.mainItem();
        crew.sideItem();
        crew.drink();
        crew.toy();
    }

}
