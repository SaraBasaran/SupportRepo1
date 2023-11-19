import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchAndProductImageTest {
    public static void main(String[] args) throws InterruptedException {

        //Go to homepage
        WebDriver driver= new ChromeDriver();
        driver.get("https://shop.schukat.com/schukatGermanySite/de/EUR/");

        //type ace in the search bar
        driver.findElement(By.tagName("input")).sendKeys("ace");
        driver.wait(2000);

        //select 3rd product
        driver.findElement(By.xpath("(//em[@class='search-results-highlight'])[3]")).click();

        //click on the product picture

        driver.findElement(By.xpath("(//div[@class='g-template g-item-template ng-star-inserted'])[1]")).click();
        driver.wait(2000);

        // verify the product picture is opened on a new window

       boolean productPictureIsOpened=driver.findElement(By.xpath("(//div[@class='g-items-container'])[2]")).isDisplayed();

        Assert.assertTrue(productPictureIsOpened);

        //click on Close button after seeing the product picture
        driver.findElement(By.cssSelector("i.g-btn-close")).click();
        driver.wait(2000);

        //click on right arrow button and verify that another product picture is shown
        driver.findElement(By.xpath("//i[@aria-label='Next']")).click();
        driver.wait(2000);

        boolean picFromGalery=driver.findElement(By.cssSelector("div.g-items-container")).isDisplayed();
        Assert.assertTrue(picFromGalery);

        //click on left arrow button

        driver.findElement(By.xpath("//i[@aria-label='Previous']")).click();

        driver.wait(2000);

        //verify that previous picture is shown

       boolean firstPicIsShown= driver.findElement(By.xpath("(//div[@class='g-template g-item-template ng-star-inserted'])[1]")).isDisplayed();
        Assert.assertTrue(firstPicIsShown);

        driver.close();


        }
}
