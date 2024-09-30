package exercise;

// BEGIN
public class ListThread extends Thread{

    public ListThread(SafetyList list) {
        this.list = list;
    }

    private SafetyList list;

    @Override
    public void run(){
        for (int i = 0; i < 1000 ; i++) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list.add(i);
        }
    }
}
// END
