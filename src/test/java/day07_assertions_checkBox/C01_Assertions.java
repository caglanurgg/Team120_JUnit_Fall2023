package day07_assertions_checkBox;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class C01_Assertions {

    //2) https://www.bestbuy.com/ Adresine gidin
    //   farkli test method’lari olusturarak asagidaki testleri yapin
    //		○ Sayfa URL’inin https://www.bestbuy.com/ ‘a esit oldugunu test edin
    //		○ titleTest => Sayfa başlığının “Rest” içermediğini(contains) test edin
    //		○ logoTest => BestBuy logosunun görüntülendigini test edin
    //		○ FrancaisLinkTest => Fransizca Linkin görüntülendiğini test edin
    static WebDriver driver; // static methodlarda kullanilsin diye

    @BeforeClass // static olmasi mecburi
    public static void setup(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.bestbuy.com/");
    }

    /*
    WebDriverManager.firefoxdriver().setup(); kodu,
    Firefox tarayıcısını otomatik olarak yapılandırmak ve
    kullanıma hazır hale getirmek için kullanılır.
    Bu kod parçası, Firefox sürücüsünün bilgisayarınıza otomatik olarak indirilmesini ve
    yüklenmesini sağlar. Bu, tarayıcının sürümü ile WebDriver'ın
    uyumlu olduğundan emin olmanıza yardımcı olur.

    driver = new FirefoxDriver(); kodu, WebDriver ile Firefox tarayıcısını kullanmak için
    bir FirefoxDriver nesnesi oluşturur. Bu nesne, Selenium'un Firefox tarayıcısını
    açmasına ve kontrol etmesine olanak tanır.
     */
    @AfterClass // static olmasi mecburi
    public static void teardown(){

        driver.close();
    }

    @Test
    public void urlTest(){
        //○ Sayfa URL’inin https://www.bestbuy.com/ ‘a esit oldugunu TEST EDIN
        String expectedUrl = "https://www.bestbuy.com/";
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(expectedUrl,actualUrl);

    }

    @Test
    public void titleTesti(){
        //   titleTest => Sayfa baslıgının “Rest” icermediğini(contains) TEST EDIN

        String unexpectedIcerik = "Rest";
        String actualTitle = driver.getTitle();

        Assert.assertFalse(actualTitle.contains(unexpectedIcerik));
    }

    @Test
    public void logoTesti(){
        //	logoTest => BestBuy logosunun goruntulendigini TEST EDIN
        WebElement logoElementi = driver.findElement(By.xpath("(//img[@alt = 'Best Buy Logo'])[1]"));

        Assert.assertTrue(logoElementi.isDisplayed());
    }

    @Test
    public void francaisTesti(){
        // FrancaisLinkTest => Fransizca Linkin goruntulendigini TEST EDIN
        WebElement francaisLinkElementi = driver.findElement(By.xpath("//button[normalize-space()='Français']"));

        Assert.assertTrue(francaisLinkElementi.isDisplayed());
    }
}
