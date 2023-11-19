import junit.framework.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import static junit.framework.Assert.assertTrue;

public class InvalidLoginProcess {
    public static void main(String[] args) throws InterruptedException {
        //login page den basla
        WebDriver driver= new ChromeDriver();
        driver.get("https://shop.schukat.com/schukatGermanySite/de/EUR/register/new-customer");

        //register page kodunu hafızaya alır
       String registerPageWH= driver.getWindowHandle();

       //https://shop.schukat.com/schukatGermanySite/de/EUR/register/new-customer

       //yopmail e switch et
       driver.switchTo().newWindow(WindowType.WINDOW);
       driver.get("https://yopmail.com/");

       //email olustur
       driver.findElement(By.cssSelector("input.ycptinput")).sendKeys("abc123@yopmail.com");
       //email sayfasına git
       driver.findElement(By.xpath("//div[@id='refreshbut']")).click();

       //email i dogrula
       String yopEMail= driver.findElement(By.cssSelector("div.bname")).getText();
        Assert.assertEquals("abc123@gmail.com", yopEMail);

       //yopmail page kodunu hafızaya al
       String yopMailWH= driver.getWindowHandle();

       //shp.schukat register page e git ve email kısmına yopmail hesabı gir

        driver.switchTo().window(registerPageWH);

        //register page de vor name doldur
        driver.findElement(By.xpath("//input[@formcontrolname='firstName']")).sendKeys("s");

        //register page de name doldur
        driver.findElement(By.xpath("//input[@formcontrolname='lastName']")).sendKeys("s");

        //yopmail deki emaili register page de gir
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(yopEMail);

        //register page de password kısmını doldur
        String password="abc123A.";

        driver.findElement(By.xpath("(//input[@type='password'])[1]")).sendKeys(password);

        //password u confirm icin 2. defa gir
        driver.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys(password);

        //ilk checkbox ı isaretle
        WebElement firstCheckBox= driver.findElement(By.name("confirmationAGB"));
        firstCheckBox.click();

        //2. checkbox ı isaretle
        WebElement secondCheckBox= driver.findElement(By.id("confirmationPrivacy"));
       secondCheckBox.click();

       // Registrierung abschließen butonuna tıkla
        driver.findElement(By.linkText(" Registrierung abschließen "));
        driver.wait(2000);

        //Fast geschafft! baslıgını dogrula
        driver.findElement(By.cssSelector("p.registration__done__title")).sendKeys(Keys.ARROW_UP);
       String fastGeschaftTitle= driver.findElement(By.cssSelector("p.registration__done__title")).getText();
        Assert.assertTrue(fastGeschaftTitle.contains("Fast geschafft!"));


        //yopmailde inbox a git
        driver.get("https://yopmail.com/wm");
        String yopEmailInputBox= driver.getWindowHandle();
        driver.switchTo().window(yopEmailInputBox);

        //sayfayı refresh et ve bekle
        driver.navigate().refresh();
        driver.wait(3000);

        //input box da oldugunu dogrula
      String inputBoxHeader= driver.findElement(By.cssSelector("div.ellipsis.nw.b.f18")).getText();
      Assert.assertEquals(inputBoxHeader, "Registrierung Schukat-Kundenkonto");

      //shop.schukat a dön
        driver.switchTo().window(registerPageWH);
        driver.wait(2000);

        //account icon ustune tıkla
        driver.findElement(By.xpath("(//div[@slot='icon'])[1]")).click();

        //Registeren yazısına tıkla
        driver.findElement(By.xpath("(//a[@href='/schukatGermanySite/de/EUR/login'])[1]")).click();

        //login sayfasına yonlendirildigini dogrula
      assertTrue(driver.findElement(By.xpath("//h1[text()='Login für Kunden']")).isDisplayed());

      //yopmail deki email ile giris yap
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(yopEMail);

      //password gir
      driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);

      //Anmelden butonuna tıkla
        driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();



















    }
}
