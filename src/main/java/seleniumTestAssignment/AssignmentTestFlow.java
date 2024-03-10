package seleniumTestAssignment;

import org.testng.Assert;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AssignmentTestFlow {

	public static void main(String[] args) {

		// Set the path to the ChromeDriver executable
       System.setProperty("webdriver.chrome.driver", "exeFileFolder/chromedriver-win32/chromedriver.exe");
        
        // Initialize the WebDriver
        WebDriver driver = new ChromeDriver();
        
        driver.manage().window().maximize();
        JavascriptExecutor jse=(JavascriptExecutor)driver;

        // Open Flipkart Website
        driver.get("https://www.flipkart.com");
        String my_title=driver.getTitle();
        System.out.println(my_title);

        // Verify that the homepage loads successfully
        String actualTitle = driver.getTitle();
        String expectedTitle = "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!";
        Assert.assertEquals(actualTitle, expectedTitle);
        
        // Search and Add to Cart
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("laptop",Keys.ENTER);
        

        // Wait for search results to load
        WebDriverWait wait = new WebDriverWait(driver, 10);
        
        WebElement laptopLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@data-id,'COMG')]")));
        System.out.println(laptopLink.getText());
        laptopLink.click();
        String homePage = driver.getWindowHandle();
        Set<String> window=driver.getWindowHandles();
        for(String page : window) {
        	if(!page.equals(homePage)) {
        		driver.switchTo().window(page);
        		break;
        	}
        }
  
        // Add the selected laptop to the shopping cart and Navigate to shopping cart 
        WebElement addToCartButton = driver.findElement(By.xpath("//div[@class='_1p3MFP dTTu2M']//ul//li"));
        addToCartButton.click();
        
        //cart button
        WebElement cartButton = driver.findElement(By.linkText("Cart"));
        cartButton.click(); 
		          
        

        // Click on the "Proceed to Checkout" button
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement checkoutButton= driver.findElement(By.cssSelector("._2KpZ6l._2ObVJD._3AWRsL"));
        jse.executeScript("arguments[0].click();", checkoutButton);
       
        
        
       //Due to OTP Authentication automation is not possible for Login Functionality
          WebElement LoginUsername=driver.findElement(By.xpath("//input[@type='text']"));
        LoginUsername.sendKeys("7387805169");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
       

        // User Authentication - Handle if login is required
     

        // Shipping Information
      
        driver.findElement(By.className("_1P2-0f")).click();
        
        WebElement name=driver.findElement(By.xpath("//input[@name='name']"));
        name.sendKeys("DemoTest");
        WebElement phone=driver.findElement(By.xpath("//input[@name='phone']"));
        phone.sendKeys("1234567890");
        
     
        WebElement Address = driver.findElement(By.xpath("//textarea[@name='addressLine1']"));
        Address.sendKeys("123 Street");
        WebElement shippingCity = driver.findElement(By.xpath("//input[@name='city']"));
        shippingCity.sendKeys("City");
        
        //State Selection
        Select drpState = new Select(driver.findElement(By.xpath("//select[@name='state']")));
		drpState.selectByVisibleText("Maharashtra");
        WebElement shippingState = driver.findElement(By.id("state"));
        shippingState.sendKeys("State");
        WebElement AddressType = driver.findElement(By.xpath("//input[@name='locationTypeTag']"));
        AddressType.click();

        // Proceed to the next step
        WebElement saveDeliverhereBtn = driver.findElement(By.xpath("//button[@type='button' and @tabindex='10']"));
        saveDeliverhereBtn.click();

        //Review Order Summary
        WebElement continuebtn = driver.findElement(By.xpath("//span[@id='to-payment']"));
        
        jse.executeScript("arguments[0].ScrollIntoView(true);", continuebtn);
        continuebtn.click();
        
        WebElement OrderSummary=driver.findElement(By.xpath("//div[@class='_2nQDXZ']"));
        System.out.println(OrderSummary.getText());
       
        /*
        if ( OrderSummary.isDisplayed()) {
            System.out.println("Order summary displayed.");
        } else {
            System.out.println("Order summary not displayed.");
        }*/
        Assert.assertEquals(OrderSummary.getText(), laptopLink.getText());

        Alert alert = driver.switchTo().alert();
        driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[1]/div/div/button")).click();
        
        driver.findElement(By.xpath("//label[@for='CREDIT']")).click();
      
        
       
        // Close the browser window
        driver.quit();



	}

}
