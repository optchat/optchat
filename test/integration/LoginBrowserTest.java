package integration;

import org.junit.Test;
import utils.WithBrowserTest;

public class LoginBrowserTest extends WithBrowserTest {

    @Test
    public void サンプル() throws Exception {
        running(browser -> {
            goTo(browser, "/login");
            takeScreenShot(browser, "01.ログイン画面");
        });
    }
}
