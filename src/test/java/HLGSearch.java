import junit.framework.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import static junit.framework.Assert.assertTrue;

public class HLGSearch {
    public static void main(String[] args) throws InterruptedException {
    /*

           burada doğrulama yapmaya gerek yok amaç doğru sayfada olduğunuzu doğrulamak için yazdım
          Aşağıda sağ köşede pdf download var
          Tıklayın
          İkinci sayfada açılıyor
          Yüklendiğini doğrulayın
*/

        //Go to homepage
        WebDriver driver= new ChromeDriver();
       // driver.get("https://shop.schukat.com/schukatGermanySite/de/EUR/");

        //Arama butonuna "hlg" yazın
        driver.findElement(By.cssSelector("input.custom-searchbox__input")).sendKeys("hlg");
        driver.wait(2000);

        //Enter basmadan gelen ilk ürün önerisine tıklayın
        driver.findElement(By.xpath("//div[text()='HLG-240H-30B']")).click();
        driver.wait(2000);

        //Pembe renkte "HLG-320H-42C" ürün baslıgını görün
        String productTitle= driver.findElement(By.xpath("//h1")).getText();
        boolean productTitleVerification= productTitle.contains(" HLG-320H-42C ");
        assertTrue(productTitleVerification);

        driver.wait(3000);

        //ilk urun bilgisi linkine tıkla
        driver.findElement(By.cssSelector("a.custom-product-summary-download__link")).click();
        driver.wait(3000);

        // acılan TAB de urun info gosterildigini dogrula

         String homepageHandle= driver.getWindowHandle();
         driver.switchTo().newWindow(WindowType.TAB);
         driver.get("https://shopapi.schukat.com/medias/HLG-80H-SPEC.pdf?context=bWFzdGVyfHBpbWFzc2V0c3w3OTUzOTJ8YXBwbGljYXRpb24vcGRmfGFHSmlMMmhoWVM4NU5qUTFOelE1T0RZeU5ETXdMMGhNUnkwNE1FZ3RVMUJGUXk1d1pHWXw3YWEzMTZjZTNhMWQzM2JhMWNhMjcwYWNiNGI3NWM0MWMyZDg5Y2E3ZTY5YzBiODk3OGI5ZDI4NGQzODE5NDVk&_gl=1*7h8nbo*_gcl_au*MTI4ODk0MDY4NS4xNjk4NTE1Nzk2");
         driver.wait(2000);

         String productInfoURL= driver.getCurrentUrl();
         System.out.println("productInfoURL = " + productInfoURL);
        assertTrue(productInfoURL.contains("shopapi"));
//        String ProductInfoWindowHandle= driver.getWindowHandle();
//        driver.wait(2000);

        //homepage e dön ve scroll down ettikten sonra upload link kontrol et
        driver.switchTo().window(homepageHandle);

        driver.wait(2000);
        String homepageURL= driver.getCurrentUrl();
        Assert.assertTrue(homepageURL.contains("shop.schukat.com"));

        JavascriptExecutor jsexecutor = ((JavascriptExecutor) driver);

        WebElement dokumenteLink= driver.findElement(By.linkText(" Dokumente & Medien "));
        jsexecutor.executeScript("arguments[0].scrollIntoView(true);", dokumenteLink);


        jsexecutor.executeScript("arguments[0].click();", dokumenteLink);

        driver.wait(2000);

        driver.findElement(By.cssSelector(" a.custom-product-summary-download__link")).click();

        // bir directory olustur

        String homeDirectory= System.getProperty("user.home");
        System.out.println(homeDirectory);

        boolean isExist= Files.exists(Paths.get(homeDirectory));

        Assert.assertTrue(isExist);








    }
}
