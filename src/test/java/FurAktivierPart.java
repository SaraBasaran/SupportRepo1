import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

public class FurAktivierPart {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver=new ChromeDriver();

        //url dogrulaması yap
        Assert.assertTrue(driver.getCurrentUrl().contains("Akkus"));

        //cart a tıkla
        driver.findElement(By.xpath("(//button[@class='custom-product-list-item-quantity-input-button button button--primary'])[2]")).click();
        driver.wait(2000);

        // Für Einkauf aktiv gozukuyor mu dogrula
        boolean furEinkaufAktivBtn=driver.findElement(By.cssSelector("div.cart-info__topLine-left")).isDisplayed();

        Assert.assertTrue(furEinkaufAktivBtn);

        // Für Einkauf aktiv butonuna tıkla deaktive et
        driver.findElement(By.xpath("//input[@class='cart-info__topLine-input']")).click();

        //toplam fiyat bolumundeki soru isaretine tıkla
        driver.findElement(By.xpath("(//svg-icon[@key='hint-transparent'])[1]")).click();

        //pop up ıcındeki hier e tıkla
        driver.findElement(By.xpath("//a[@href='/schukatGermanySite/de/EUR/Bestellhinweise']")).click();

        //betesllhinweise page acıldıgını dogrula
        String cartPageWH= driver.getWindowHandle();
        String bestelURL=driver.switchTo().newWindow(WindowType.TAB).getCurrentUrl();
        Assert.assertTrue(bestelURL.contains("Bestellhinweise"));

        //cart page e geri dön
        driver.switchTo().window(cartPageWH);






    }
}
