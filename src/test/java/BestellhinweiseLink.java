import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static junit.framework.Assert.assertTrue;

public class BestellhinweiseLink {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver= new ChromeDriver();

        //sayfada en asagı in bestellhinweise link ine tıkla
        Actions actions=new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).perform();

        WebElement bestellhinweiseLink= driver.findElement(By.xpath("//a[@href='bestellhinweise']"));
        bestellhinweiseLink.click();

        //bestellhinweise page inde oldugunu dogrula

        assertTrue(driver.getCurrentUrl().contains("bestellhinweise"));

        //homepage e dön
        driver.navigate().back();

        //Unser Vertriebsteam linkine tıkla
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        driver.findElement(By.linkText("Unser Vertriebsteam")).click();

        //url de contact oldugunu dogrula
        assertTrue(driver.getCurrentUrl().contains("contact"));

        //homepage e dön
        driver.navigate().back();

        //Uber Uns linkine tıkla
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        driver.findElement(By.xpath("//a[@href='ueberuns']")).click();

        //ueberuns url de oldugunu dogrula
       assertTrue(driver.getCurrentUrl().contains("ueberuns"));

       //homepage e dön
        driver.navigate().back();
        driver.wait(2000);

        //Vorkasse linkine tıkla
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        driver.findElement(By.xpath("//svg-icon[@key='payment-advance']")).click();
        driver.wait(2000);

        //Vorkasse ye navigate ettigini dogrula
        //bestellhinweise link ile aynı sayfayı acıyor??
        assertTrue(driver.getCurrentUrl().contains("bestellhinweise"));

        driver.navigate().back();

        //Rechnungskauf linkine tıkla

        driver.findElement(By.linkText("Rechnungskauf")).click();

        //URL dogrula >> bestellhinweise page ine yonlendiriyor
       assertTrue(driver.getCurrentUrl().contains("bestellhinweise"));

       //homepage e dön
        driver.navigate().back();

        //Lastschrift linkine tıkla
        driver.findElement(By.linkText("Lastschrift")).click();

        // Lastschrift page e yonlendirdigini dogrula
        assertTrue(driver.getCurrentUrl().contains("bestellhinweise"));
















    }

}
