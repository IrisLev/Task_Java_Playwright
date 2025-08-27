package tests;

import com.microsoft.playwright.*;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.InventoryPage;
import pages.MenuPage;

public class SauceDemoTest {
    private Playwright playwright;
    private Browser browser;
    private Page page;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private MenuPage menuPage;
    
    @BeforeMethod
    public void setUp() {
        // Initialize Playwright
        playwright = Playwright.create();
        
        // Launch browser (using Chromium by default)
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false) // Set to true for headless mode
                .setSlowMo(1000)); // Add delay between actions for better visibility
        
        // Create new page
        page = browser.newPage();
        
        // Initialize page objects
        loginPage = new LoginPage(page);
        inventoryPage = new InventoryPage(page);
        menuPage = new MenuPage(page);
    }
    
    @Test
    public void testSauceDemoWorkflow() {
        System.out.println("=== Starting SauceDemo Test Workflow ===");
        
        // Step 1: Login to https://www.saucedemo.com/
        System.out.println("\n1. Logging in to SauceDemo...");
        loginPage.navigateToLoginPage();
        loginPage.login("standard_user", "secret_sauce");
        
        // Verify login was successful by checking if we're on the inventory page
        if (inventoryPage.isInventoryPageLoaded()) {
            System.out.println("✓ Login successful - Inventory page loaded");
        } else {
            System.out.println("✗ Login failed - Inventory page not loaded");
            return;
        }
        
        // Step 2: Print the description and price of the first product
        System.out.println("\n2. Getting first product information...");
        inventoryPage.printFirstProductInfo();
        
        // Step 3: Click on the menu and log out
        System.out.println("\n3. Logging out...");
        menuPage.logout();
        int a =0;
        // Verify logout was successful by checking if we're back on the login page
        if (loginPage.getCurrentUrl().contains("saucedemo.com") && 
            !loginPage.getCurrentUrl().contains("inventory")) {
            System.out.println("✓ Logout successful - Back to login page");
        } else {
            System.out.println("✗ Logout may have failed - Not on login page");
        }
        
        System.out.println("\n=== Test Workflow Completed ===");
    }
    
    @AfterMethod
    public void tearDown() {
        // Close browser and playwright
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
