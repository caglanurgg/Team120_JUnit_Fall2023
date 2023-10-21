package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReusableMethods {

    public static void bekle(int saniye) { // statik bir methodum var


        try {
            Thread.sleep(saniye * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void tumSayfaFotografCek(WebDriver driver,String resimAdi){
    //her screenshot'in benzersiz bir isme sahip olmasi icin
        //1- method'un cagrildigi yerden resim adi yollanacak
        //2- sonuna tarih etiketi ekleyelim 2310062013

        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmm");
        String tarihEtiketi= ldt.format(formatter); // bu bana bir String getiriyor

        // target\screenShots\tumSayfaScreenshot.jpg
        String dinamikDosyaYolu ="target\\screenShots\\"+ resimAdi + tarihEtiketi + "jpg";

        TakesScreenshot tss =(TakesScreenshot) driver;
        //1.adim TakesScreenshot objesi olusturduk
        // ve driver'imizi TakesScreenshot'a cast etmek

        File  tumSayfaSS = new File(dinamikDosyaYolu);
        //bundan sonra hicbir resim otekinin ustune kaydedilmeyecek
        // hepsi bagimsiz resim olacak
        //2.adim kaydedecegimiz yeri hazirladik

        // 3.adim gecici bir dosyaya kaydetmemiz gerekiyor
        File geciciDosya = tss.getScreenshotAs(OutputType.FILE); // fotograf cekildi

        try {
            FileUtils.copyFile(geciciDosya,tumSayfaSS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void webElementFotografCek(WebElement webElement, String resimAdi){
        //her screenshot'in benzersiz bir isme sahip olmasi icin
        //1- method'un cagrildigi yerden resim adi yollanacak
        //2- sonuna tarih etiketi ekleyelim 2310062013

        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmm");
        String tarihEtiketi= ldt.format(formatter); // bu bana bir String getiriyor

        // target\screenShots\tumSayfaScreenshot.jpg
        String dinamikDosyaYolu ="target\\screenShots\\"+ resimAdi + tarihEtiketi + "jpg";


        File  tumSayfaSS = new File(dinamikDosyaYolu);
        //bundan sonra hicbir resim otekinin ustune kaydedilmeyecek
        // hepsi bagimsiz resim olacak
        //2.adim kaydedecegimiz yeri hazirladik

        // 3.adim gecici bir dosyaya kaydetmemiz gerekiyor
        File geciciDosya = webElement.getScreenshotAs(OutputType.FILE); // fotograf cekildi

        try {
            FileUtils.copyFile(geciciDosya,tumSayfaSS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
