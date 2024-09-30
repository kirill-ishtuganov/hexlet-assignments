package exercise;

class App {

    public static void main(String[] args) {
        // BEGIN
        var list = new SafetyList();

        var thred1 = new ListThread(list);
        var thred2 = new ListThread(list);

        thred1.start();
        thred2.start();
        try {
            thred1.join();
            thred2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(list.getSize());
        // END
    }
}

