import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
public class UrunFiyatHEsaplama {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver=new ChromeDriver();
        assertTrue(driver.getCurrentUrl().contains("Akkus"));

        //sepeteurun ekleme class ındaki bazı stepleri ekle *********************************************
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

        //cart ta tek tek price ları alıp yesil alandaki toplam fiyat ile dogrulama yap

        List<WebElement> cartItemPriceInEachProduct= driver.findElements(By.cssSelector("div.cart-item-summary__content.cart-item-summary__content--total.cart-item-summary__content--price"));
        double sum=0;

        for (WebElement w: cartItemPriceInEachProduct){

            double num= Double.parseDouble(w.getText());
            System.out.println("num = " + num);

            sum=sum+num;

        }

        //yesil alandaki Gesamtwarenwert toplam tutar aynı mı
        double totalGesamwaren= Integer.parseInt(driver.findElement(By.cssSelector("div.cart-order-summary__total-price")).getText());

        Assert.assertEquals(sum, totalGesamwaren);

        //*************************************************************************************************

        //anasayfaya dön
        driver.navigate().back();
        driver.wait(2000);

        //ilk urun fiyatını al
        String productPrice= driver.findElement(By.xpath("(//button[@class='custom-product-list-item-price-table-label js-highlighted'])[1]")).getText();

        System.out.println("productPrice = " + productPrice); //  3,05  € *****deger sureklı degisiyor**********

        //Almanya daki format nasıl double icin? , ile mi . ile mi?
       String priceWithoutEuroSign= productPrice.replace("€","");
       double proPriceSplitted= Double.parseDouble(priceWithoutEuroSign.trim().split(",")[0]);
        System.out.println("proPriceSplitted = " + proPriceSplitted);
       double decimalNum= Double.parseDouble("0."+priceWithoutEuroSign.trim().split(",")[1]);
        System.out.println("decimalNum = " + decimalNum);
       double price= proPriceSplitted+decimalNum; //3.05
        System.out.println("price = " + price);

       //urun sayısını arttır
        driver.findElement(By.xpath("(//input[@min='0'])[1]")).sendKeys("8");
        driver.findElement(By.xpath("(//input[@min='0'])[1]")).sendKeys(Keys.ENTER);

        //urun fiyatını 9 ile carp ve gorunen urun toplam fiaytını dogrula ilk class da da 1 tane eklemistik 1+8 yaz

       double totalPriceUnderProTitle= price*9;

       //urun alanında warenwert kısmındaki degeri al double a convert et
       String warenwertPrice=driver.findElement(By.xpath("(//div[@class='custom-product-list-item-quantity-bottom-title'])[2]")).getText();
       //bize gelmesi beklenen deger -->  24,76 €

       int firstNumInWarenwert= Integer.parseInt(warenwertPrice.trim().replace("€", "").trim().split(",")[0]);
       double secondNumInWarenwert= Double.parseDouble("0."+warenwertPrice.trim().replace("€", "").trim().split(",")[1]);
       double totalWarenwert= firstNumInWarenwert+secondNumInWarenwert;//24,76

        assertEquals(totalPriceUnderProTitle, totalWarenwert);

        //cart icona tıkla ve sepetin toplam tutarı da degisiyor mu dogrula

        driver.findElement(By.xpath("(//svg-icon[@key='cart'])[1]")).click();
        driver.wait(2000);

        //ilk item 9 ile carpılmıs fiyatını al
     String firstProdTotalPriceInCart=driver.findElement(By.xpath("(//div[@class='cart-item-summary__content cart-item-summary__content--total cart-item-summary__content--price'])[1]")).getText();

      double firstPartNum= Double.parseDouble(firstProdTotalPriceInCart.trim().replace("€", "").trim().split(",")[0]);
      double secondPartNum= Double.parseDouble("0."+firstProdTotalPriceInCart.trim().replace("€", "").trim().split(",")[1]);

     double totalProdPrice= firstPartNum+secondPartNum;//24.40

     //fiyatın arttıgını dogrula
    //tum fiyatları al
    List<WebElement> productPrices= driver.findElements(By.xpath("//div[@class=\"cart-item-summary__content cart-item-summary__content--total cart-item-summary__content--price\"]"));

    double sumOfProPrices=0;

    for (WebElement w: productPrices){

    String eachProdPrice= w.getText();

  //double degerlere cevir
   double firstPartNumInEachProPrice= Double.parseDouble(eachProdPrice.trim().replace("€", "").trim().split(",")[0]);
   double secondPartNumIneachProPrice= Double.parseDouble("0."+eachProdPrice.trim().replace("€", "").trim().split(",")[1]);

  double totalNumInEachPrice= firstPartNumInEachProPrice+secondPartNumIneachProPrice;

  //toplam fiyatı sumOfPrices a atama yap
  sumOfProPrices=sumOfProPrices+totalNumInEachPrice;
}

    //totalGesamwaren ile aynı mı dogrulama yap
    Assert.assertEquals(totalGesamwaren,sumOfProPrices);

    //nachste preistaffel yanındaki info iconuna tıkla -->
    driver.findElement(By.xpath("(//svg-icon[@key='info-transparent'])[1]")).click();

    // 1 stuck 3,05 €
    // 20 stuck 2,58 € gibi secenklerin dogru calıstıgını dogrula  2. item rakam degere tıkla
   driver.findElement(By.xpath("(//td[@class='cart-item-priceScale__element cart-item-priceScale__element--quantity'])[1]")).click();

    // 20 stuck yanındaki sayıyı al
    String priceNextToTwentyStuck= driver.findElement(By.xpath("(//td[@class=\"cart-item-priceScale__element cart-item-priceScale__element--price js-highlighted\"])[1]")).getText();

    // hesaplamanın dogru yapıldıgını dogrula






    }
}
