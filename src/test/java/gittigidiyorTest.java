import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;


public class gittigidiyorTest extends gittigidiyorPage {

    @Test
    public void startedTest() {

        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        actions = new Actions(driver);
        driver.manage().window().maximize();
        //driver.manage().window().fullscreen();
        driver.get(url);
        logger.info("Anasayfaya gidildi");

        anasayfa();
        giris();
        kullaniciKontrol();
        urunArama();
        urunlerSayfasi();
        urunSayfasi();
        sepet();
    }
}
