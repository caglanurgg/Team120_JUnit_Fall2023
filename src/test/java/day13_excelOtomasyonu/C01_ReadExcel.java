package day13_excelOtomasyonu;

import org.apache.poi.ss.usermodel.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class C01_ReadExcel {

    @Test
    public void readExcelTestleri() throws IOException {

        String dosyaYolu="src/test/java/day13_excelOtomasyonu/ulkeler.xlsx";

        FileInputStream fis= new FileInputStream(dosyaYolu);

        Workbook workbook= WorkbookFactory.create(fis);
        // olusturdugumuz workbook, online olarak excel dosyasina erismez
        // 20.satirda FileInputStream kullanarak excel'deki bilgileri aldik
        // 22.satirda fis'in aldigi bilgileri kullanarak kodlarimizin icinde
        // fiziki excel dosyasinin bir kopyasini olusturmus olduk.

        /*
        ulkeler.xlsx bir excel dosyasidir.
        1-once excel dosyasi olarak olusturmalıyım workbook
        2- sayfaya ulasmaliyim
        3- satira ulasmaliyim
        4- cell'e yani hucre bilgisine ulasmaliyim
         */
        /*
        *onemli*
        BIZ EXCEL'IN BILGILERINI ALARAK OLUSTURDUGUMUZ KOPYA WORKBOOK
        UZERINDE CALISIYORUZ
         */

        /*
        20.satir
        FileInputStream ile excel'deki bilgileri okudum o excel'deki okudugum bilgileri
        kullanarak gelip kodlarımın icerisinde kopya dosya olusturdum
         */

        Sheet sheet=workbook.getSheet("Sayfa1"); //2- sayfaya ulasmaliyim
        Row row=sheet.getRow(3); // 3- satira ulasmaliyim
        Cell cell=row.getCell(3);//  4- cell'e yani hucre bilgisine ulasmaliyim
        System.out.println(cell); // Cezayir

        //	- 1.satirdaki 2.hucreye gidelim ve yazdiralim
        System.out.println(sheet.getRow(0).getCell(1).toString()); // Başkent (İngilizce)
        // i:index

        //	- 1.satirdaki 2.hucreyi bir string degiskene atayalim ve yazdiralim
        String istenenData = sheet.getRow(0).getCell(1).toString();
        System.out.println(istenenData);

        //- 2.satir 4.cell’in afganistan’in baskenti Kabil oldugunu test edelim
        String expectedData= "Kabil";
        String  actualData = sheet.getRow(1).getCell(3).toString();
        Assert.assertEquals(expectedData,actualData);

        //	- Satir sayisini bulalim
        System.out.println(sheet.getLastRowNum());// 190 yani son satirin index'ini verir
        // biz satir sayisini bulmak icin 1 ekledik
        //sheet.getLastRowNum() method

        //	- Fiziki olarak kullanilan satir sayisini bulun
        System.out.println(sheet.getPhysicalNumberOfRows());
        //sheet.getPhysicalNumberOfRows() method
        // direkt fiziki olarak kullanilan satir sayisini yazdirir 191

        //	- Ingilizce Ulke isimleri ve baskentleri bir MAP OLARAK KAYDEDELIM

        String isim;
        String baskent;
        Map<String,String> ulkelerMap=new TreeMap<>();

        for (int i = 1; i <= sheet.getLastRowNum() ; i++) {

            isim = sheet.getRow(i).getCell(0).toString();
            baskent = sheet.getRow(i).getCell(1).toString();

            ulkelerMap.put(isim,baskent);
        }
        // Ulkeler excel'inde Canada isimli bir ulke oldugunu test edin
        Assert.assertTrue(ulkelerMap.containsKey("Canada"));

        // Ulkeler excel'inde Cuba'nin baskentinin Havana oldugunu test edin

        String expectedBaskent = "Havana";
        String actualBaskent = ulkelerMap.get("Cuba");

        Assert.assertEquals(expectedBaskent,actualBaskent);


    }

}