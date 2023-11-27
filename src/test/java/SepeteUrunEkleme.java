import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import static junit.framework.Assert.assertTrue;

public class SepeteUrunEkleme {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver=new ChromeDriver();
        assertTrue(driver.getCurrentUrl().contains("Akkus"));

        //sepete 4 urun ekle
        driver.findElement(By.xpath("//div[@class='custom-product-list-item-checkbox-number']")).sendKeys(Keys.ARROW_DOWN);

        //1.urun ekle
        driver.findElement(By.xpath("(//div[@class='custom-product-list-item-checkbox-number'])[1]")).click();

        //cart a tıkla
        driver.findElement(By.xpath("(//button[@class='custom-product-list-item-quantity-input-button button button--primary'])[1]")).click();
        driver.wait(2000);

        //sepet ekranı acıldıgını dogrula
       String cartScreenTitle= driver.findElement(By.cssSelector("div.cart-dialog__title")).getText();
        Assert.assertTrue(cartScreenTitle.contains("Warenkorb"));

        //sepet ekranını kapat
        driver.findElement(By.cssSelector("button.cart-dialog__close-button"));

        //2.urun ekle

        driver.findElement(By.xpath("(//div[@class='custom-product-list-item-checkbox-number'])[2]")).click();

        //cart a tıkla
        driver.findElement(By.xpath("(//button[@class='custom-product-list-item-quantity-input-button button button--primary'])[2]")).click();
        driver.wait(2000);

        //sepet ekranını kapat
        driver.findElement(By.cssSelector("button.cart-dialog__close-button"));

        //3.urun ekle
        driver.findElement(By.xpath("(//div[@class='custom-product-list-item-checkbox-number'])[3]")).click();

        //cart a tıkla
        driver.findElement(By.xpath("(//button[@class='custom-product-list-item-quantity-input-button button button--primary'])[3]")).click();
        driver.wait(2000);

        //sepet ekranını kapat
        driver.findElement(By.cssSelector("button.cart-dialog__close-button"));

        //4.urun ekle
        driver.findElement(By.xpath("(//div[@class='custom-product-list-item-checkbox-number'])[4]")).click();

        //cart a tıkla
        driver.findElement(By.xpath("(//button[@class='custom-product-list-item-quantity-input-button button button--primary'])[4]")).click();
        driver.wait(2000);

        //Warenkorb anzeigen e tıkla

        driver.findElement(By.linkText("Warenkorb anzeigen")).click();

        //cart summary title dogrula yesil alandaki text: " Der Mindestbestellwert von ... " gorunur mu degil mi
        String cartSummary= driver.findElement(By.cssSelector("div.cart-order-summary-state")).getText();
        assertTrue(cartSummary.contains(" Der Mindestbestellwert"));

        //sepet baslıgı olarak 4 Positionen deki rakam dogru mu assert et
       String cartItemNumber= driver.findElement(By.xpath("//h2")).getText();
       assertTrue(cartItemNumber.contains("4"));

       //sepete eklenen urunlerin toplam fiyatı ile yesil baslık altındaki miktar aynı mı

        //tek tek price ları alıp yesil alandaki toplam fiyat ile dogrulama yap

    List<WebElement> cartItemPriceInEachProduct= driver.findElements(By.cssSelector("div.cart-item-summary__content.cart-item-summary__content--total.cart-item-summary__content--price"));
       double sum=0;
    for (WebElement w: cartItemPriceInEachProduct){

       double num= Double.parseDouble(w.getText());

       sum=sum+num;

    }

    //yesil alandaki Gesamtwarenwert toplam tutar aynı mı
     double totalGesamwaren= Double.parseDouble(driver.findElement(By.cssSelector("div.cart-order-summary__total-price")).getText());

    Assert.assertEquals(sum, totalGesamwaren);

    //Angelegt am gozukuyor mu dogrula
    boolean angelegtAm= driver.findElement(By.xpath("(//span[@class='cart-info__shared-block-right-text'])[2]")).isDisplayed();

    Assert.assertTrue(angelegtAm);

    // Aktualisiert am:  gozukuyor mu dogrula
       boolean aktualisiertDateFromCart= driver.findElement(By.xpath("(//span[@class='cart-info__shared-block-right-text'])[4]")).isDisplayed();

        Assert.assertTrue(aktualisiertDateFromCart);


       }

}
