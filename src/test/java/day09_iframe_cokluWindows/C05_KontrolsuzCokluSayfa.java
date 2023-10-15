package day09_iframe_cokluWindows;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.TestBase;

import java.util.Set;

public class C05_KontrolsuzCokluSayfa extends TestBase {

    @Test
    public void test01(){
        //1- https://the-internet.herokuapp.com/iframe url'ine gidin
        driver.get("https://the-internet.herokuapp.com/iframe");
        String ilkSayfaWhd= driver.getWindowHandle();
        // ilk sayfanin WindowHandleDegeri'ni kaydettim

        //2- sayfa basliginin Internet icerdigini test edin
        String expectedTitleIcerik= "Internet";
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitleIcerik));

        //3- Elemental Selenium linkini tiklayin
        driver.findElement(By.linkText("Elemental Selenium")).click();

        //4-Yeni acilan tab'in Title'inin Selenium icerdigini test edin
        String ikinciSayfaWhd="";
        Set<String> whdSeti = driver.getWindowHandles();
        // bir Set olusturayim String'lerden olussun adi da whdSeti olsun
        // degerleri driver.getWindowHandles()'dan alsin
        // driver.getWindowHandles() -> [ilkSayfaWhd,whdSeti]

        for (String each : whdSeti
        ) {
            if (!each.equals(ilkSayfaWhd)){
                ikinciSayfaWhd = each;
            }
        }
        // 28.satir ve 39.satir arasini ikinciSayfaWhd'ni bulmak icin yaptik

        driver.switchTo().window(ikinciSayfaWhd); // ikinciSayfa'ya gecis yaptik
        expectedTitleIcerik = "Selenium"; // Title'inin Selenium icerdigini
        actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitleIcerik));

        //5- Ilk sayfaya donup url'in internet icerdigini test edin
        driver.switchTo().window(ilkSayfaWhd);

        String expectedUrlIcerik = "internet";
        String actualUrl= driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedUrlIcerik));
    }
}
