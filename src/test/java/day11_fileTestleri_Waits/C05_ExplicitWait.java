package day11_fileTestleri_Waits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class C05_ExplicitWait {


    @Test
    public void test01(){
        //1. webdriver ayarlarini implicitly wait OLMADAN yapin
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //2. https://the-internet.herokuapp.com/dynamic_controls adresine gidin.
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        //3. Remove butonuna basin.
        driver.findElement(By.xpath("//*[text()='Remove']")).click();
        // //*[text()='Remove'] tagi ne olursa olsun yazısı Remove olsun



        //4. “It’s gone!” mesajinin goruntulendigini dogrulayin.

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        //  wait objesi olusturalim

        /*
            Gorunmeyen bir elementi locate edemeyiz
            locate edilmeyen bir elementi bekleyemeyiz
            gorunur olmasi icin de beklememiz lazim (paradoks)

            Explicit Wait bekleme ve locate islemini birlikte yapabilir
         */

        WebElement itsGoneElementi =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='message']")));

        // Locate etme ile elemente kaydetmeyi ve beklemeyi bir arada yapabiliyoruz.
        // (47.satir ve 48.satir )
        // boylece o element gorunur oluncaya kadar bekleyecek bende test edebilecegim

        // WebElement itsGoneElementi = driver.findElement(By.xpath("//p[@id='message']"));
        Assert.assertTrue(itsGoneElementi.isDisplayed());

        //5. Add buttonuna basin
        driver.findElement(By.xpath("//*[text()='Add']")).click();


        //6. It’s back mesajinin gorundugunu test edin
        // it's back yazisinin cikmasi ve bizim locate etmemiz icin beklememiz gerekiyor

        WebElement itsBackElementi =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()=\"It's back!\"]")));

        Assert.assertTrue(itsBackElementi.isDisplayed());
        driver.close();
    }
}
