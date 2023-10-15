package day09_iframe_cokluWindows;

import org.bouncycastle.math.ec.custom.sec.SecT113Field;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.TestBase;

import java.util.Set;

public class C04_KontrolsuzCokluWindow extends TestBase {


    @Test
    public void cokluPencereTesti(){

        //●1- https://the-internet.herokuapp.com/windows adresine gidin.
        driver.get("https://the-internet.herokuapp.com/windows");
        String ilkSayfaWhd= driver.getWindowHandle(); // windowHandleDegeri


        //●2- Sayfadaki textin “Opening a new window” olduğunu doğrulayın.
        String expectedYazi = "Opening a new window";
        WebElement yaziElementi = driver.findElement(By.tagName("h3"));
        String actualYazi= yaziElementi.getText();
        Assert.assertEquals(expectedYazi,actualYazi);
        bekle(2);

        //●3- Sayfa başlığının(title) “The Internet” olduğunu doğrulayın.
        String expectedTitle = "The Internet";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);

        //●4- Click Here butonuna basın.
        driver.findElement(By.linkText("Click Here")).click();
        bekle(2);

        //●5-Acilan yeni pencerenin sayfa başlığının (title) “New Window” oldugunu dogrulayin.

        /*
            Bir linke tikladigimizda
            biz newWindow() kullanmadigimiz halde yeni sayfa aciliyorsa
            driver eski sayfada kalir
            yeni sayfada islem yapabilmek icin
            bizim driver'i yeni sayfaya gecirmemiz gerekir.
         */

         // onemli: driver'in ikinci sayfaya gecmesi icin
        // o sayfanin windowHandleDegeri'ne ihtiyac var
        Set<String> whdSeti = driver.getWindowHandles();
        // bir Set olusturayim String'lerden olussun adi da whdSeti olsun
        // degerleri driver.getWindowHandles()'dan alsin
        // driver.getWindowHandles() -> [ilkSayfaWhd,whdSeti]

        System.out.println("ilk sayfa whd : " + ilkSayfaWhd);
        System.out.println("window handles seti : " + whdSeti);

        String ikinciSayfaWhd="";

        for (String each: whdSeti
        ) {

            if (!each.equals(ilkSayfaWhd)){
                ikinciSayfaWhd = each;
            }
        }

        //artik 2.sayfanin whd'ini bildigimiz icin (56.satir ve 67.satir arasi sayesinde)
        //ikinci sayfaya gecis yapabiliriz

        driver.switchTo().window(ikinciSayfaWhd);
        String expected2Title = "New Window";
        String actual2Title = driver.getTitle();
        Assert.assertEquals(expected2Title,actual2Title);

        //●5- Sayfadaki textin “New Window” olduğunu doğrulayın.
        String expected2Yazi = "New Window";
        WebElement yazi2Elementi = driver.findElement(By.tagName("h3"));
        String actual2Yazi= yazi2Elementi.getText();
        Assert.assertEquals(expected2Yazi,actual2Yazi);


        //●6-Bir önceki pencereye geri döndükten sonra
        //  sayfa başlığının “The Internet” olduğunu doğrulayın.

        driver.switchTo().window(ilkSayfaWhd);

        String expected3Title = "The Internet";
        String actual3Title = driver.getTitle();

        Assert.assertEquals(expected3Title,actual3Title);
    }

}
