package sourcemaking.creational.builder;

/**
 * client
 */
public class Customer {
    public static void main(String[] args) {
        Customer c1 = new Customer("A");
        c1.order();

        Customer c2 = new Customer("B");
        c2.order();
    }

    private String orderCrewType;

    public Customer(String orderCrewType) {
        this.orderCrewType = orderCrewType;
    }

    public void order() {
        // Step 5
        Cashier cashier;
        RestaurantCrew crew;

        if (orderCrewType.equals("A")) {
            crew = new CrewA();
        } else {
            crew = new CrewB();
        }

        cashier = new Cashier(crew);

        // step 6
        cashier.service();

        // step 7
        System.out.println("顾客点了套餐" + orderCrewType);
        System.out.println("\t" + crew.getResult());
    }
}
