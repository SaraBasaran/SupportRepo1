import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RoHS_Konform {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver= new ChromeDriver();

        // title altındaki ergebnisse sayısını al
        String currentResult= driver.findElement(By.xpath("//h2")).getText();
        System.out.println("currentResult = " + currentResult);
        int currentResFirstNum= Integer.parseInt(currentResult.trim().split(" ")[0]);


            //RoHS_Konform checkbox a tıkla
       WebElement rohsCheckbox= driver.
                findElement(By.xpath("(//span[@class='custom-plp-facets__default-facets-line-checkmark'])[2]"));
       rohsCheckbox.sendKeys(Keys.ARROW_DOWN);
       rohsCheckbox.click();

        driver.wait(2000);

       //sayfa ortasındaki title kısmında ki rakam ile sonuc rakamı esit mi karsılastır
       String resultInTitle= driver.findElement(By.xpath("div.custom-plp-facets__default-facets__title")).getText();
       int resultNumInTitle= Integer.parseInt(resultInTitle.trim().split(" ")[0]);

        String currentResultAfterClicking= driver.findElement(By.xpath("//h2")).getText();
        int currentResFirstNumAfterClicking= Integer.parseInt(currentResultAfterClicking.trim().split(" ")[0]);
       Assert.assertEquals(resultNumInTitle, currentResFirstNumAfterClicking);

        //RoHS Konform checkbox ı unchecked yap/kapat
        driver.findElement(By.xpath("//svg-icon[@key='cancel']")).click();
        driver.wait(2000);


    }
}
