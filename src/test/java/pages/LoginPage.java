package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

public class LoginPage {
    private Page page;
    
    // Locators
    private final String USERNAME_INPUT = "#user-name";
    private final String PASSWORD_INPUT = "#password";
    private final String LOGIN_BUTTON = "#login-button";
    private final String ERROR_MESSAGE = "[data-test='error']";
    
    public LoginPage(Page page) {
        this.page = page;
    }
    
    /**
     * Navigate to the SauceDemo login page
     */
    public void navigateToLoginPage() {
        page.navigate("https://www.saucedemo.com/");
    }
    
    /**
     * Enter username in the username field
     * @param username the username to enter
     */
    public void enterUsername(String username) {
        page.fill(USERNAME_INPUT, username);
    }
    
    /**
     * Enter password in the password field
     * @param password the password to enter
     */
    public void enterPassword(String password) {
        page.fill(PASSWORD_INPUT, password);
    }
    
    /**
     * Click the login button
     */
    public void clickLoginButton() {
        page.click(LOGIN_BUTTON);
    }
    
    /**
     * Perform complete login with username and password
     * @param username the username to login with
     * @param password the password to login with
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
    
    /**
     * Get the error message if login fails
     * @return the error message text
     */
    public String getErrorMessage() {
        Locator errorElement = page.locator(ERROR_MESSAGE);
        if (errorElement.isVisible()) {
            return errorElement.textContent();
        }
        return null;
    }
    
    /**
     * Check if error message is displayed
     * @return true if error message is visible
     */
    public boolean isErrorMessageVisible() {
        return page.locator(ERROR_MESSAGE).isVisible();
    }
    
    /**
     * Get the current page title
     * @return the page title
     */
    public String getPageTitle() {
        return page.title();
    }
    
    /**
     * Get the current URL
     * @return the current URL
     */
    public String getCurrentUrl() {
        return page.url();
    }
}
