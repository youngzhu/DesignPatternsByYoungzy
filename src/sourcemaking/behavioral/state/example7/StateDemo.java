package sourcemaking.behavioral.state.example7;

/*
条件判断语句被用在了 loadOnBy 方法里。
这可以通过状态模式来改进。


这好像是个没做完的例子。。。
 */

class Player {
    private String name;
    private int money;

    Player(String name, int money) {
        this.name = name;
        this.money = money;
    }

    String getName() {
        return name;
    }

    int getBalance() {
        return money;
    }

    void debit(int amount) {
        money -= amount;
    }

    void credit(int amount) {
        money += amount;
    }
}

class Property {
    private String name;
    private int price;
    private int rent;
    private Player owner;

    Property(String name) {
        this.name = name;
        price = 100;
        rent = 10;
    }

    String getName() {
        return name;
    }

    int getPrice() {
        return price;
    }

    int getRent() {
        return rent;
    }

    Player getOwner() {
        return owner;
    }
    void setOwner(Player player) {
        owner = player;
    }

    void landOnBy(Player player) {
        System.out.print(player.getName() + " landed on " + name);
        if (getOwner() == null) {
            System.out.print(" - not owned\n" + player.getName());
            if (player.getBalance() < getPrice())
                System.out.println(" does not have enough money to purchase");
            else {
                player.debit(getPrice());
                setOwner(player);
                System.out.println(" bought " + getName());
            }
        } else {
            System.out.println(" - owned by " + getOwner().getName());
            if (player != getOwner()) {
                player.debit(getRent());
                getOwner().credit(getRent());
                System.out.println(getOwner().getName() + " now has "
                        + getOwner().getBalance() + " dollars");
            }
        }
        System.out.println(player.getName() + " has " + player.getBalance() + " dollars");

    }
}

public class StateDemo {
    public static void main(String[] args) {
        Player p1 = new Player("Tom", 50);
        Player p2 = new Player("    Dick", 500);
        Property prop = new Property("Boardwalk");
        prop.landOnBy(p1);
        prop.landOnBy(p2);
        prop.landOnBy(p1);
        prop.landOnBy(p2);
        prop.landOnBy(p1);
    }
}
