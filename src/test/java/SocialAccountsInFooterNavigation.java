import junit.framework.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static junit.framework.Assert.assertTrue;

public class SocialAccountsInFooterNavigation {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver= new ChromeDriver();

        //footer kısmına git shop.schukat.com a tıkla

        Actions actions=new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).perform();

        driver.findElement(By.xpath("//a[@href='https://shop.schukat.com']")).click();

        //url in shop.schukat.com oldugunu dogrula

        Assert.assertEquals("https://shop.schukat.com/schukatGermanySite/de/EUR/",driver.getCurrentUrl());
        driver.navigate().back();
        driver.wait(2000);

        //homePage window handle kodu al
        String homePageWindowHandle= driver.getWindowHandle();

        //youtube link e tıkla
        actions.sendKeys(Keys.PAGE_DOWN).perform();
       driver.findElement(By.xpath("//svg-icon[@aria-label='social-youtube-icon']")).click();
       driver.wait(2000);

        //youtube page navigate et
        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://www.youtube.com/channel/UCvDfWBK4Yu5-dDZ-SNnakRA");

       String youTubeWindowHandle= driver.getWindowHandle();

       //youtube da baslık dogrulaması
      String youtubeTitle= driver.findElement(By.cssSelector("yt-formatted-string#yt-formatted-string")).getText();
      Assert.assertEquals("Schukat electronic Vertriebs GmbH", youtubeTitle);

      //homepage e navigate et
        driver.switchTo().window(homePageWindowHandle);

      //facebook button a tıkla
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        driver.findElement(By.xpath("//svg-icon[@key='social-facebook']")).click();

      //facebook page yeni tab de acılıyor shop schukat baslık dogrula

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.facebook.com/Schukat.electronic/");
        driver.wait(2000);


        String facebookHeader=driver.findElement(By.xpath("(//h1)[2]")).getText();
        Assert.assertEquals("Schukat electronic Vertriebs GmbH", facebookHeader);
        driver.wait(2000);

        //homepage e geri dön
        driver.switchTo().window(homePageWindowHandle);

        // xing icon a tıkla ve xing page e navigate et

        actions.sendKeys(Keys.PAGE_DOWN).perform();
        driver.findElement(By.xpath("//a[@href='https://www.xing.com/companies/schukatelectronicvertriebsgmbh']")).click();

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.xing.com/pages/schukatelectronicvertriebsgmbh");

        //xing URL de oldugunu dogrula
        String xingURL= driver.getCurrentUrl();
        Assert.assertEquals("https://www.xing.com/pages/schukatelectronicvertriebsgmbh", xingURL);

        //homepage e geri dön
        driver.switchTo().window(homePageWindowHandle);

        // linked In icona tıkla ve linkedIn page e navigate et,
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        driver.findElement(By.xpath("//svg-icon[@key='social-linked-in']")).click();
        driver.wait(2000);

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.linkedin.com/company/schukat-electronic-vertriebs_gmbh/?originalSubdomain=de");

        //linkedIn url de header kısımınıda schukat oldugunu dogrula
        String linkedHeader= driver.findElement(By.xpath("//h1")).getText();
        assertTrue(linkedHeader.contains("Schukat electronic Vertriebs GmbH"));

        //homepage e geri dön
        driver.switchTo().window(homePageWindowHandle);

        //twitter icon a tıkla
        actions.sendKeys(Keys.PAGE_DOWN).perform();
       driver.findElement(By.xpath("//svg-icon[@key='social-twitter']")).click();
        driver.wait(2000);

        //twitter a yonlendirdigini dogrula
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://twitter.com/schukat_monheim/");

        assertTrue(driver.getCurrentUrl().contains("schukat"));


    }


}
