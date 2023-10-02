package utilities;

public class ReusableMethods {

    public static void bekle(int saniye){ // statik bir methodum var


        try {
            Thread.sleep(saniye*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
