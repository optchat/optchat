package integration;

import org.junit.Test;
import play.i18n.Messages;
import utils.WithBrowserTest;

import static org.fest.assertions.Assertions.assertThat;

public class LoginBrowserTest extends WithBrowserTest {

    @Test
    public void ログイン画面キャプチャ() throws Exception {
        running(browser -> {
            goTo(browser, "/login");
            takeScreenShot(browser, "01.ログイン画面");
        });
    }

    @Test
    public void ログイン失敗フロー() throws Exception {
        running(browser -> {
            goTo(browser, "/login");
            browser.$("#mail").text("mail@exapmle.com");
            browser.$("#password").text("password");
            takeScreenShot(browser, "02.ログイン失敗前画面");
            browser.$("form").first().submit();
            assertThat(browser.pageSource()).contains(Messages.get("validation.error.password.unmatch"));
            takeScreenShot(browser, "03.ログイン失敗後画面");
        });
    }

    @Test
    public void ログイン成功フロー() throws Exception {
        running(browser -> {
            // ユーザ作成
            goTo(browser, "/user/create");
            browser.$("#name").text("test");
            browser.$("#mail").text("test@example.com");
            browser.$("#password").text("test12345");
            takeScreenShot(browser, "04.ユーザ登録前画面");
            browser.$("form").first().submit();
            takeScreenShot(browser, "05.ユーザ登録後画面");

            goTo(browser, "/login");
            browser.$("#mail").text("test@example.com");
            browser.$("#password").text("test12345");
            takeScreenShot(browser, "06.ログイン成功前画面");
            browser.$("form").first().submit();
            assertThat(browser.url()).contains("/chat");
            takeScreenShot(browser, "07.ログイン成功後画面");
        });
    }
}
