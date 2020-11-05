package sourcemaking.creational.builder;

/**
 * 核查清单 Step 3
 * 套餐
 */
abstract class RestaurantCrew {
    /**主食*/
    abstract String mainItem();
    /**辅食*/
    abstract String sideItem();
    /**饮料*/
    abstract String drink();
    /**玩具*/
    abstract String toy();

    String getResult() {
        String result = "主食：" + mainItem()
                + "；辅食：" + sideItem()
                + "；饮料：" + drink()
                + "；玩具：" + toy()
                ;
//        System.out.println(result);
        return result;
    }
}

/*
 * 核查清单 Step 4
 */

/**
 * 套餐A
 */
class CrewA extends RestaurantCrew {

    @Override
    public String mainItem() {
        return "牛肉汉堡";
    }

    @Override
    public String sideItem() {
        return "薯条";
    }

    @Override
    public String drink() {
        return "可乐";
    }

    @Override
    public String toy() {
        return "恐龙";
    }
}

/**
 * 套餐B
 */
class CrewB extends RestaurantCrew {

    @Override
    public String mainItem() {
        return "鸡腿汉堡";
    }

    @Override
    public String sideItem() {
        return "甜甜圈";
    }

    @Override
    public String drink() {
        return "豆浆";
    }

    @Override
    public String toy() {
        return "小汽车";
    }
}