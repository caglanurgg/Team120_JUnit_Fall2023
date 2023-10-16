package day11_fileTestleri_Waits;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

public class C02_DinamikDosyaYoluOlusturma {


    @Test
    public void test01(){

        /*
            Testlerimizin farkli bilgisayarlarda da calismasi gerekiyorsa
            file testleri icin gerekli olan DOSYA YOLU dinamik yapilmalidir

            Java'da dosya yolunu kodun calistigi bilgisayardan
            otomatik olarak almak icin 2 secenek vardir

            System.getProperty("user.home") ===> bilgisayarin ana dosya yolunu verir
            System.getProperty("user.dir")  ===>

         */

        // masaustumuzde bulunan image.jpg dosyasinin varligini
        // dinamik dosya yolu kullanarak test edelim

        //     C:\Users\Cagla\Desktop\image.jpg

        //     "C:\Users\Cagla" +    "\Desktop\image.jpg"
        //         Ana dosya yolu        Herkeste ayni olan kisim

        String dinamikDosyaYolu = System.getProperty("user.home") + "\\Desktop\\image.jpg" ;

        System.out.println(dinamikDosyaYolu); // C:\Users\Cagla\Desktop\image.jpg

        Assert.assertTrue(Files.exists(Paths.get(dinamikDosyaYolu)));

        System.out.println(System.getProperty("user.dir"));
        //C:\Users\Cagla\IdeaProjects\Team120_JUnit_Fall2023


        // projemizde var olan text.txt'nin varligini
        // dinamik dosya yolu ile test edelim

        //C:\Users\Cagla\IdeaProjects\Team120_JUnit_Fall2023
        //    \src\test\java\day11_fileTestleri_Waits\text.txt
        //49.satir hepimiz icin ayni



        String dinamikTextDosyayolu = System.getProperty("user.dir") +
               "/src/test/java/day11_fileTestleri_Waits/text.txt";

        Assert.assertTrue(Files.exists(Paths.get(dinamikTextDosyayolu)));
    }
}
