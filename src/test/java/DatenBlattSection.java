import junit.framework.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
public class DatenBlattSection {
       public static void main(String[] args) throws InterruptedException {
        WebDriver driver= new ChromeDriver();

        //title ın Akkus page de oldugunu dogrula
     Assert.assertTrue(driver.getTitle().contains("Akkus"));

        //title altında 220 ergebnisse de 220 oldugunu dogrula
        String ergebnisseResultUnderTitle= driver.findElement(By.cssSelector("span.custom-category-intro__currentResults.ng-star-inserted")).getText();

        int ergebnisseResultNum= Integer.parseInt(ergebnisseResultUnderTitle.trim().split(" ")[0]); //220 Ergenisse den 220 alındı

           // aus 220 Artikeln
          String ausArtikeln=driver.findElement(By.xpath("//span[text()=' aus 220 Artikeln']")).getText();
           int ausArtResultNum= Integer.parseInt(ausArtikeln.trim().split(" ")[1]);  // 220 rakamı alındı

           //iki sonuc esit mi assert yap
           Assert.assertEquals(ergebnisseResultNum, ausArtResultNum);

      // Datenblatt checkbox a dogru in ve checkbox ı isaretle
           WebElement datenBlattCheckBox= driver.findElement(By.xpath("(//span[@class='custom-plp-facets__default-facets-line-checkmark'])[1]"));
           JavascriptExecutor jsexecutor = ((JavascriptExecutor) driver);
           jsexecutor.executeScript("arguments[0].scrollIntoView(true);", datenBlattCheckBox);

        datenBlattCheckBox.click();
        driver.wait(3000);


        //sayfa datenblatt ısaretlenince otomatik yukarı cıkıyor  oyuzden tekrar scroll ınto view yapmak gerekli
        jsexecutor.executeScript("arguments[0].scrollIntoView(true);", datenBlattCheckBox);
       driver.wait(2000);

       //liste icinde 1. kısımdaki datenblatt kısmının oldugunu dogrula
        WebElement datenBlattInListColumn1= driver.findElement(By.xpath("(//div[text()=' Datenblatt '])[1]"));
        jsexecutor.executeScript("arguments[0].scrollIntoView(true);", datenBlattInListColumn1);
        Assert.assertTrue(datenBlattInListColumn1.isDisplayed());

        //list te bulunan section rakamlarının sonuncusunu al
      // String lastNumInListDB= driver.findElement(By.cssSelector("a.last")).getText();
      //int lastNumberInListSectionForDB= Integer.parseInt(lastNumInListDB.trim());

      //butun datenblatt sutunundaki satır sayısını al list icine
        List<WebElement> datenBlatt1 = driver.findElements(By.xpath("td.custom-product-list-item-pdf.custom-product-list-item-cell"));
        int datenBlatt1Size=datenBlatt1.size();
        System.out.println("1.No of Rows are :"+datenBlatt1Size);

        // 2 numaralı kısıma tıkla
        driver.findElement(By.xpath("//a[@aria-label='page 2']")).click();
        driver.wait(2000);

        //liste icindeki 2. kısımdaki datenblat kısmının oldugunu dogrula
        WebElement datenBlattInListColumn2= driver.findElement(By.xpath("(//div[text()=' Datenblatt '])[2]"));
        jsexecutor.executeScript("arguments[0].scrollIntoView(true);", datenBlattInListColumn2);
        Assert.assertTrue(datenBlattInListColumn2.isDisplayed());

        //2 numaralı kısımdaki tum datenblatt ları say
        List<WebElement> datenBlatt2 = driver.findElements(By.xpath("td.custom-product-list-item-pdf.custom-product-list-item-cell"));
        int datenBlatt2Size=datenBlatt2.size();
        System.out.println("2.No of Rows are :"+datenBlatt2Size);

        //3 numaralı kısıma tıkla ve datenblatt baslıgını dogrula

        driver.findElement(By.xpath("//a[@aria-label='page 3']")).click();
        driver.wait(2000);

        //3. kısımdaki datenblatt kısmını oldugunu dogrula
        WebElement datenBlattInListColumn3= driver.findElement(By.xpath("(//div[text()=' Datenblatt '])[3]"));
        jsexecutor.executeScript("arguments[0].scrollIntoView(true);", datenBlattInListColumn3);
        Assert.assertTrue(datenBlattInListColumn3.isDisplayed());

        //3.kısımdaki tum datenblattları say

        List<WebElement> datenBlatt3 = driver.findElements(By.xpath("td.custom-product-list-item-pdf.custom-product-list-item-cell"));
        int datenBlatt3Size=datenBlatt3.size();
        System.out.println("3.No of Rows are :"+datenBlatt3Size);

        //4. kısıma tıkla
        driver.findElement(By.xpath("//a[@aria-label='page 4']")).click();
        driver.wait(2000);

        //4. kısımdaki datenblatt kısmını oldugunu dogrula
        WebElement datenBlattInListColumn4= driver.findElement(By.xpath("(//div[text()=' Datenblatt '])[4]"));
        jsexecutor.executeScript("arguments[0].scrollIntoView(true);", datenBlattInListColumn4);
        Assert.assertTrue(datenBlattInListColumn4.isDisplayed());

        //4.kısımdaki tum datenblattları say

        List<WebElement> datenBlatt4 = driver.findElements(By.xpath("td.custom-product-list-item-pdf.custom-product-list-item-cell"));
        int datenBlatt4Size=datenBlatt4.size();
        System.out.println("4.No of Rows are :"+datenBlatt4Size);

        //tum list elementleri aldıktan sonra datenblatt checkbox ı cose et/ unchecked yap

           driver.findElement(By.xpath("//svg-icon[@key='cancel']")).click();
           driver.wait(2000);

        //   unchecked yaptıktan sonra iki sayını esit olup olmadıgını dogrula
           //iki sonuc esit mi assert yap
           Assert.assertEquals(ergebnisseResultNum, ausArtResultNum);


    }


}
