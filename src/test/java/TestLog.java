import org.apache.log4j.Logger;

public class TestLog {
    /* Get actual class name to be printed on */
    static Logger log = Logger.getLogger(TestLog.class.getName());

    public static void main(String[] args) {
        log.debug("Hello this is a debug message");
        log.info("Hello this is an info message");
    }
}
