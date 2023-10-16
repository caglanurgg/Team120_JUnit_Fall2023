package day11_fileTestleri_Waits;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.TestBase;

import java.nio.file.Files;
import java.nio.file.Paths;

public class C01_fileDownload extends TestBase {


    @Test
    public void indirmeTesti(){

        //  C:\Users\Cagla\Desktop\image.jpg

        //2. https://the-internet.herokuapp.com/download adresine gidelim.
        driver.get("https://the-internet.herokuapp.com/download");

        //3. image.jpg dosyasını indirelim

        bekle(1);
        driver.findElement(By.xpath("//a[text()='image.jpg']")).click();

        //4. Dosyanın başarıyla INDIRILIP INDIRILMEDIGINI  test edelim
        bekle(5);
        String dosyaYolu ="C:\\Users\\Cagla\\Downloads\\image.jpg";

       Assert.assertTrue(Files.exists(Paths.get(dosyaYolu)));
       //Paths.get(dosyaYolu) dosya yolundaki Files var mi

        bekle(2);
    }
}
