import io.github.nosequel.schematic.util.ThreadUtil;
import org.junit.Test;

public class ThreadingTest {

    @Test
    public void callThreaded() {
        ThreadUtil.execute(() -> System.out.println("hi"));
        ThreadUtil.execute(() -> System.out.println("bye"));
    }
}
