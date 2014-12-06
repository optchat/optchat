package utils;

import org.junit.BeforeClass;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import play.libs.F;
import play.test.Helpers;
import play.test.TestBrowser;

import java.io.IOException;

/**
 * ブラウザを使用した結合テスト用共通テストクラス
 * Created by raizu on 2014/12/06.
 */
public class WithBrowserTest {

    /** テストサーバのポート番号 */
    public static final int PORT = 3333;
    /** スクリーンショット出力先 */
    public static final String SCREENSHOT_PATH = "target/test-screenshots";
    /** ブラウザの画面サイズ */
    private static final Dimension DIMENSION = new Dimension(1920, 1080);

    @BeforeClass
    public static void startApp() throws IOException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/extra/chromedriver.exe");
    }

    /**
     * ブラウザを起動してテストを実行
     * @param callback テスト内容
     * @throws Exception
     */
    public void running(F.Callback<TestBrowser> callback) throws Exception{
        ChromeDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().setSize(DIMENSION);
        Helpers.running(Helpers.testServer(PORT), chromeDriver, callback);
    }

    /**
     * ブラウザを任意のURLに移動
     * @param browser ブラウザ
     * @param url [http://localhost:ポート番号]以降のURL
     */
    public void goTo(TestBrowser browser, String url) {
        browser.goTo("http://localhost:" + PORT + url);
    }

    /**
     * スクリーンショット
     * @param browser ブラウザ
     * @param title スクリーンショット名
     */
    public void takeScreenShot(TestBrowser browser, String title) {
        browser.takeScreenShot(SCREENSHOT_PATH + "/" + browser.getClass().getSimpleName() + "/" + title + ".png");
    }
}
