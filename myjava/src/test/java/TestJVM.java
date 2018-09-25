import org.junit.Test;

public class TestJVM {
    @Test
    public void testStack(){

    }

    private void callSelf(int n) {
        System.out.println(n);
        n++;
        callSelf(n);
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Thread.sleep(1000);
        }

    }
}
