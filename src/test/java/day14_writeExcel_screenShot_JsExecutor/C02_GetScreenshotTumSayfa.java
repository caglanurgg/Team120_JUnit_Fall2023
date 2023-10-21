package day14_writeExcel_screenShot_JsExecutor;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import utilities.TestBase;

import java.io.File;
import java.io.IOException;

public class C02_GetScreenshotTumSayfa extends TestBase {

    @Test
    public void tumSayfaScreenshot() throws IOException {

        // *1)amazon anasayfaya gidin
        driver.get("https://www.amazon.com");

        // *2)Nutella icin arama yapin
        WebElement aramaKutusu = driver.findElement(By.id("twotabsearchtextbox"));
        aramaKutusu.sendKeys("Nutella"+ Keys.ENTER);

        // *3)Sonuclarin Nutella icerdigini test edin

        WebElement sonucYaziElementi = driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"));

        String expectedIcerik ="Nutella";
        String actualSonucYazisi = sonucYaziElementi.getText();
        Assert.assertTrue(actualSonucYazisi.contains(expectedIcerik));

        // *elimizde bir delil olmasini istiyorsak
        // *rapor icin sayfanin fotografini cekin

        // 1.adim TakeScreenshot objesi olusturma
        TakesScreenshot tss = (TakesScreenshot) driver;

        // 2.adim kaydedecegimiz fotografi projede istedigimiz yere kaydedebilmek icin
        // yeni bir file(dosya) olusturalim, dosya yolunu(path)yazalim

        File tumSayfaScreenshot = new File("target/screenShots/tumSayfaScreenshot.png");

        // 3.adim
        //Takescreenshot objesini kullanarak getScreenshotAs( ) methodunu
        // calistiralim ve gelen resmi gecici bir file’a assign edelim

        File geciciResim = tss.getScreenshotAs(OutputType.FILE);

        // 4.adim Kaydettigimiz goruntuyu, saklamak istedigimiz dosyaya kopyalayalim

        FileUtils.copyFile(geciciResim,tumSayfaScreenshot);

        bekle(5);


    }
}
