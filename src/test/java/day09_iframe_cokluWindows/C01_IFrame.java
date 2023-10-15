package day09_iframe_cokluWindows;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.TestBase;

public class C01_IFrame extends TestBase {

    @Test
    public void test01(){
        // 1 ) https://the-internet.herokuapp.com/iframe adresine gidin.
        driver.get("https://the-internet.herokuapp.com/iframe");

        // 2 ) Bir metod olusturun: iframeTest
        //	- “An IFrame containing….” textinin erisilebilir oldugunu test edin
        //	ve  konsolda yazdirin.
        WebElement iFrameYaziElementi= driver.findElement(By.tagName("h3"));
        Assert.assertTrue(iFrameYaziElementi.isEnabled());


        /*
            Locate dogru oldugu halde webelement'i kullanamiyorsak
            webelement bir iframe icinde olabilir

            Eger iframe icinde ise
            - once o iframe'i locate etmeli
            - locate ettigimiz iframe'e gecis yapmali
            - sonra istenen islemi gerceklestirmeliyiz
             (41.satir ve 43.satir arasinda bunu yaptim)

            Iframe'e gecis yapinca
            driver'imiz artik o sayfaya gecmis olur
            anasayfadaki webelementleri goremez

            *Eger testimizde iframe disindan element kullanmamiz gerekirse
            once iframe'den disari cikmamiz lazim (55.satir)
         */

        WebElement iframe = driver.findElement(By.xpath("//*[@id='mce_0_ifr']"));
        // //*[@id='mce_0_ifr'] tag'i ne olursa olsun id'si mce_0_ifr olsun
        driver.switchTo().frame(iframe);

        //	- Text Box’a “Merhaba Dunya!” yazin.
        WebElement textBox= driver.findElement(By.tagName("p"));
        textBox.clear();
        textBox.sendKeys("Merhaba Dunya");


        //	- TextBox’in altinda bulunan “Elemental Selenium”
        //    linkini textinin gorunur oldugunu dogrulayin ve konsolda yazdirin.

        //iframe'e gecen driver'i yeniden anasayfaya dondurmemiz gerekir(UNUTMA)
        driver.switchTo().defaultContent(); // direkt anasayfaya gecis yapmak icin

        /*
        not:
        eğer ic ice birden fazla iframe varsa bir ust iframe'e cıkmak icin
        driver.switchTo().parentFrame(); kullaniriz.
         */

        WebElement elementalLinki= driver.findElement(By.linkText("Elemental Selenium"));
        Assert.assertTrue(elementalLinki.isDisplayed()); //gorunur oldugunu dogrulayin
        System.out.println(elementalLinki.getText()); //konsolda yazdirin

    }
}
