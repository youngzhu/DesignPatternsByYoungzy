package sourcemaking.behavioral.templatemethod;

/**
 * 打工人
 */
abstract class Worker {
    private String title;

    public Worker(String title) {
        this.title = title;
    }

    final void dailyRoutine() {
        System.out.println("*********************************");
        System.out.println(title + " 的一天：");
        getUp();
        eatBreakfast();
        goToWork();
        work();
        returnHome();
        relax();
        sleep();
    }

    void getUp() {
        System.out.println("  起床");
    }

    void eatBreakfast() {
        System.out.println("  吃早饭");
    }

    void goToWork() {
        System.out.println("  去上班");
    }

    abstract void work();

    void returnHome() {
        System.out.println("  回家");
    }

    void relax() {
        System.out.println("  放松");
    }

    void sleep() {
        System.out.println("  睡觉");
    }
}
