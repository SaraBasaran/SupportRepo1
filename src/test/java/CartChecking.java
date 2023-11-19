import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CartChecking {
    public static void main(String[] args) {

        WebDriver driver= new ChromeDriver();
        driver.get("https://shop.schukat.com/schukatGermanySite/de/EUR/");


       String proTitle= driver.findElement(By.className("cart-item__details-title link")).getText();

        Actions actions= new Actions(driver);
        actions.sendKeys(proTitle, Keys.ARROW_DOWN);

        Assert.assertTrue(proTitle.contains("RACM40-12SK/OF"));

    }


}
