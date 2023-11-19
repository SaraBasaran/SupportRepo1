
import junit.framework.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;


public class CategoryAndManufacturerPageNavitest {
    public static void main(String[] args) throws InterruptedException {

        //Go to homepage
        WebDriver driver= new ChromeDriver();
        driver.get("https://shop.schukat.com/schukatGermanySite/de/EUR/");

        driver.findElement(By.linkText("Alle Hersteller anzeigen")).click();

        driver.wait(3000);

        //Verify that Manufacturer page is shown
        boolean manufacturerPageNavigation=driver.getCurrentUrl().contains("brandoverview");

        Assert.assertTrue(manufacturerPageNavigation);

        //Click on 'R' letter from manufacturer row
        driver.findElement(By.xpath("//a[text()='R']")).click();

        driver.wait(2000);

        //click on Rademacher
        //Verify that number of artikel is same with the manufacturer result page
//       String artikelNum=driver.findElement(By.xpath("//a[@href='/schukatGermanySite/de/EUR/c/RADE']//following::div[1]")).getText();
        //Click on 'Rademacher'

        driver.findElement(By.linkText("Rademacher")).click();

       driver.wait(2000);

       //verify that title of the page is "Rademacher

       String manuTitle= driver.findElement(By.xpath("//h1")).getText();

       Assert.assertEquals(" Rademacher ", manuTitle);
       driver.wait(2000);

        //verify the number of artikel is equal to the artikel number under the brand logo

       String resultArtikelNum=driver.findElement(By.xpath("//div/div/h2/span[1]")).getText();

       driver.wait(2000);
       // 63 Ergebnisse --> getting just the number of the text
       int resultOfNumber= Integer.parseInt(resultArtikelNum.trim().split(" ")[0]);

       // aus 63 Artikeln --> getting just the number of the text
        String ausArtikelNum= driver.findElement(By.xpath("//div/div/h2/span[2]")).getText();
        driver.wait(2000);
       int ausNumberArtikel= Integer.parseInt(ausArtikelNum.trim().split(" ")[1]);

       //verify that aus 63 equals to 63 Ergebnise
       Assert.assertEquals(resultArtikelNum, ausArtikelNum);

       //Go back to home page and click on a single manifacturer

        driver.findElement(By.xpath("(//a[@class='logo__link'])[1]")).click();
        driver.wait(2000);

        driver.
        findElement(By.xpath("//a[@href='/schukatGermanySite/de/EUR/c/MEAN?query=:relevance:allCategories:MEAN']")).
                click();

        //user is navigated to the page of the manufacturer
        // get current url

        driver.wait(2000);
        boolean manuPageNavigation= driver.getCurrentUrl().contains("MEAN");

        Assert.assertTrue(manuPageNavigation);

        //scroll down and click on AC/DC item on Meanwell page
        WebElement acDCImg= driver.findElement(By.xpath("(//img[@class='custom-manufacturers-category__image'])[2]"));
        JavascriptExecutor jsexecutor = ((JavascriptExecutor) driver);
        jsexecutor.executeScript("arguments[0].scrollIntoView(true);", acDCImg);

        acDCImg.click();

        driver.wait(5000);

        //get current result text under Meanwell title: 1103 Ergebnisse
        //sayfadaki baslık altındaki sonuc rakamı alınır
        String currentResult= driver.findElement(By.cssSelector("span.custom-category-intro__currentResults")).getText();
        int currentResultNum= Integer.parseInt(currentResult.trim().split(" ")[0]);
        driver.wait(2000);

        //get result number from list part:
        //list kısmındaki meanwell isminin yanındaki kutucuk icindeki rakam alınır: 1103

      String firstResultNumInList= driver.findElement(By.xpath("(//span[@class='classification-facets__values__facet-line-count'])[1]")).getText();
      int firstResultNum= Integer.parseInt(firstResultNumInList.trim());

      // verify that the number in list and under title part is same
        //iki rakamında esit oldugu dogrulanır
        Assert.assertEquals(firstResultNum, currentResultNum);

        //verify that all result number in the list is equal to the total number of results under the title
        //list kısmındaki 2.sonuc sayısı alınır (Recom un yanındaki)

        //get second result number
       String secondResultNumInList= driver.findElement(By.xpath("(//span[@class='classification-facets__values__facet-line-count'])[2]")).getText();
       int secondNumInList= Integer.parseInt(secondResultNumInList.trim());

       //get third result number
       // list kısmındaki 3.sonuc sayısı alınır (XP Power ın yanındaki)

       String thirdResultNumInList= driver.findElement(By.xpath("(//span[@class='classification-facets__values__facet-line-count'])[3]")).getText();
       int thirdNumInList= Integer.parseInt(thirdResultNumInList.trim());


       //list kısımındaki toplam sonuc sayısı bulunur
      int totalResultNumInList= currentResultNum+ secondNumInList+thirdNumInList;

      //get the total number under the title
        //title altındaki toplam sonuc sayısını bul

      String totalNumUnderTitle=driver.findElement(By.xpath("//span[text()=' aus 1324 Artikeln']")).getText();
      //' aus 1324 Artikeln'
     int totalResultNumUnderTitle= Integer.parseInt(totalNumUnderTitle.trim().split(" ")[1]);

     //title altındaki toplam sonuc sayısı ile list kısmındaki toplam sonuc sayısı biribirine esit mi assert et
     Assert.assertEquals(totalNumUnderTitle, totalResultNumInList);



        //click on homepage button

        driver.findElement(By.xpath("(//a[@class='logo__link'])[1]")).click();
        driver.wait(2000);

        //click on one of the categories (clicked on 3rd image)-->embedded losungen

        driver.findElement(By.xpath("(//img[@class='custom-manufacturers-category__image'])[3]")).click();

        driver.wait(2000);

        //verify that category link is landing the expected manufacturer page
        //Embedded Lösungen title verification

        String categoryPageTitle=driver.findElement(By.xpath("//h1[@class='custom-category-intro__title']")).getText();
        boolean categoryPageNavi= categoryPageTitle.contains(" Embedded Lösungen ");

        Assert.assertTrue(categoryPageNavi);



    }

}
