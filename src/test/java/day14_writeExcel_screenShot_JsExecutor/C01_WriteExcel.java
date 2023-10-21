package day14_writeExcel_screenShot_JsExecutor;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class C01_WriteExcel {

    @Test
    public void excelTest() throws IOException {
        //*1) Adimlari takip ederek 1.sayfa 1.satira kadar gidelim
        String dosyaYolu = "src/test/java/day13_excelOtomasyonu/ulkeler.xlsx";
        // projenin icinde oldugu icin Dinamik dosya yoluna ihtiyac yok
        // eger masaustunde olsaydi  Dinamik dosya yolu yapmakta fayda var.

        FileInputStream fis = new FileInputStream(dosyaYolu);
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sayfa1 = workbook.getSheet("Sayfa1");

        //*2) 5.hucreye yeni bir cell olusturalim(CREATE)
        sayfa1.getRow(0).createCell(4);

        //*3) Olusturdugumuz hucreye “Nufus” yazdiralim
        sayfa1.getRow(0).getCell(4).setCellValue("Nufus");

        //*4) 2.satir nufus kolonuna 1500000 yazdiralim
        sayfa1.getRow(1).createCell(4).setCellValue("1500000");

        //*5) 10.satir nufus kolonuna 250000 yazdiralim
        sayfa1.getRow(9).createCell(4).setCellValue("250000");

        //*6) 15.satir nufus kolonuna 54000 yazdiralim
        sayfa1.getRow(14).createCell(4).setCellValue("54000");


        //bu degisiklikleri workbooK'da yaptim excel'in bundan haberi yok
        //butun degisiklikleri yaptiktan sonra workbook'u excel'e kaydedecegiz.
        //dikkat etmemiz gereken sey
        // excel acikken onun uzerinde bir seyler yaparsaniz bozulur.
        // onun icin excel kapali olmali.

        //*7) Dosyayi kaydedelim
        FileOutputStream fos = new FileOutputStream(dosyaYolu);
        workbook.write(fos);

        //*8)Dosyayi kapatalim (defteri rafina kaldirmak lazim)
        fis.close();
        fos.close();
        workbook.close();
    }
}


