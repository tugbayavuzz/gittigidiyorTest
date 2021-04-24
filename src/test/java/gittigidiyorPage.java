import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class gittigidiyorPage {

    WebDriver driver;
    Actions actions;
    final static Logger logger = Logger.getLogger(gittigidiyorTest.class.getName());
    public String url = "https://www.gittigidiyor.com/";
    String productPrice;

    public void anasayfa() {

        String controlUrl = driver.getCurrentUrl();
        assertEquals(url, controlUrl);

        System.out.println("anasayfa kontrolu saglandi");

        WebElement waitElement = driver.findElement(By.xpath("//*[@class='gekhq4-5 grTfZj']/div[1]"));
        actions.moveToElement(waitElement).build().perform();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='main-header']/div[3]/div/div/div/div[3]/div/div[1]/div[2]/div/div/div/a")).click();

        System.out.println("giris sayfasina gidildi");
    }

    public void giris() {
        driver.findElement(By.cssSelector("[id='L-UserNameField']")).sendKeys("tugbayavuz60@gmail.com");
        driver.findElement(By.cssSelector("[id='L-PasswordField']")).sendKeys("Tugba6");
        driver.findElement(By.xpath("//*[@title='Giriş Yap']")).click();

        System.out.println("kullanici bilgileri girildikten sonra giris yap butonuna tiklandi");
    }

    public void kullaniciKontrol() {
        String username = driver.findElement(By.cssSelector("[class='gekhq4-4 egoSnI'] span")).getText();
        assertEquals(username, "tugbayavuz489550");

        System.out.println("kullanici dogrulamasi yapildi");
    }

    public void urunArama() {
        driver.findElement(By.cssSelector("[name='k']")).sendKeys("Bilgisayar");
        driver.findElement(By.cssSelector("[data-cy='search-find-button']")).click();

        System.out.println("ürün aramasi yapildi");
    }

    public void urunlerSayfasi() {
        WebElement webElement = driver.findElement(By.xpath("//*[@id='best-match-right']/div[5]/ul/li[2]/a"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", webElement);

        System.out.println("2.sayfaya gidildi");

        String page = "Bilgisayar - GittiGidiyor - 2/100";
        String pageControl = driver.getTitle();
        assertEquals(page, pageControl);

        System.out.println("2.sayfada oldugu kontrol edildi");
    }

    public void urunSayfasi() {
        driver.findElement(By.cssSelector("[product-id='678402322']")).click();

        System.out.println("rastgele bir ürüne tiklandi");

        String productPrice = driver.findElement(By.xpath("//*[@id='sp-price-highPrice']")).getText();
        this.productPrice = productPrice;
        WebElement webElementBasket = driver.findElement(By.cssSelector("[id='add-to-basket']"));
        JavascriptExecutor jsBasket = (JavascriptExecutor) driver;
        jsBasket.executeScript("arguments[0].click();", webElementBasket);

        System.out.println("secilen ürün sepete ekleme islemi gerceklestirildi");

        WebElement basket = driver.findElement(By.xpath("//*[@id='header_wrapper']/div[4]/div[3]/a"));
        actions.moveToElement(basket).build().perform();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='header_wrapper']/div[4]/div[3]/div/div/div/div[2]/div[4]/div[1]/a")).click();

        System.out.println("sepete gidildi");
    }

    public void sepet() {

        String basketPrice = driver.findElement(By.cssSelector("[class='total-price']")).getText();
        assertEquals("fiyatlar eşit değil", productPrice, basketPrice);

        System.out.println("ürün fiyat karsilastirilmasi yapildi");

        driver.findElement(By.xpath("//*[@class='amount']")).click();
        driver.findElement(By.xpath("//*[@class='amount']/option[2]")).click();
        String basketPiece = driver.findElement(By.xpath("//*[@id='submit-cart']/div/div[2]/div[3]/div/div[1]/div/div[5]/div[1]/div/ul/li[1]/div[1]")).getText();
        assertEquals(basketPiece, "Ürün Toplamı (1 Adet)");

        System.out.println("ürün adetinin karsilastrilmasi yapildi");

        driver.findElement(By.xpath("//*[@class='gg-icon gg-icon-bin-medium']")).click();
        String basketEmpty = driver.findElement(By.cssSelector("[class='cart-title']")).getText();
        assertEquals("Alışveriş Sepetim", basketEmpty);

        System.out.println("ürün silindi, sepet bos mu kontrolu saglandi");
    }

}
