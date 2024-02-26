import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
public class TC1 {
    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        BaseMethods methods = new BaseMethods(driver);
        driver.get("https://www.saucedemo.com/");
        String Title = driver.getTitle();
        System.out.println(Title);
        methods.takeSnapShot(driver,".\\Login pic.png");
        methods.SendLoginCredentials("standard_user","secret_sauce");
        WebElement FirstProduct = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        FirstProduct.click();
        WebElement CartIcon = driver.findElement(By.className("shopping_cart_link"));
        CartIcon.click();
        WebElement Checkout = driver.findElement(By.id("checkout"));
        Checkout.click();
        WebElement Username = driver.findElement(By.id("first-name"));
        Username.sendKeys("Omar");
        WebElement Lastname = driver.findElement(By.id("last-name"));
        Lastname.sendKeys("Al-Ghazali");
        WebElement Postalcode = driver.findElement(By.id("postal-code"));
        Postalcode.sendKeys("19311");
        WebElement Continue = driver.findElement(By.id("continue"));
        Continue.click();
        String TotalPrice = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]")).getText();
        System.out.println(TotalPrice);
        WebElement Finish = driver.findElement(By.id("finish"));
        Finish.click();
        String expected = "Thank you for your order!";
        String actual = driver.findElement(By.className("complete-header")).getText();
        Assert.assertTrue(actual.contains(expected));
        WebElement BackHome = driver.findElement(By.id("back-to-products"));
        BackHome.click();
        methods.takeSnapShot(driver,".\\Before Filter Pic.png");
        WebElement DropDownBar = driver.findElement(By.className("product_sort_container"));
        Select selection = new Select(DropDownBar);
        selection.selectByValue("lohi");
        methods.takeSnapShot(driver,".\\After Filter Pic.png");
        WebElement HamburgerIcon = driver.findElement(By.id("react-burger-menu-btn"));
        HamburgerIcon.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement Logout = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
        Logout.click();
        String expected_1 = "Accepted usernames are:";
        String actual_1 = driver.findElement(By.id("login_credentials")).getText();
        Assert.assertTrue(actual_1.contains(expected_1));
        methods.SendLoginCredentials("locked_out_user","secret_sauce");
        String Error = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        System.out.println(Error);
        Thread.sleep(5000);
        WebElement Loginfield = driver.findElement(By.id("user-name"));
        Loginfield.clear();
        WebElement Password = driver.findElement(By.id("password"));
        Thread.sleep(5000);
        Password.clear();
        methods.SendLoginCredentials("problem_user","");
        methods.takeSnapShot(driver,".\\DOGZZZZ.png");
        WebElement HamburgerIcon2 = driver.findElement(By.id("react-burger-menu-btn"));
        HamburgerIcon2.click();
        WebElement Logout2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
        Logout2.click();
        driver.quit();
    }
}