package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

public class MenuPage {
    private Page page;
    
    // Locators
    private final String MENU_BUTTON = "#react-burger-menu-btn";
    private final String LOGOUT_LINK = "[data-test='logout-sidebar-link']";
    private final String ALL_ITEMS_LINK = "#inventory_sidebar_link";
    private final String ABOUT_LINK = "#about_sidebar_link";
    private final String RESET_APP_STATE_LINK = "#reset_sidebar_link";
    private final String SIDEBAR = ".bm-menu";
    private final String SIDEBAR_OVERLAY = ".bm-overlay";
    private final String CLOSE_MENU_BUTTON = "#react-burger-cross-btn";
    
    public MenuPage(Page page) {
        this.page = page;
    }
    
    /**
     * Click on the menu button (hamburger menu)
     */
    public void clickMenuButton() {
        page.click(MENU_BUTTON);
        // Wait for sidebar to be visible
        page.waitForSelector(SIDEBAR, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
    }
    
    /**
     * Click on the logout link in the sidebar menu
     */
    public void clickLogoutLink() {
        try {
            // Wait for the logout link to be visible and clickable
            System.out.println("Waiting for logout link to be visible...");
            page.waitForSelector(LOGOUT_LINK, new Page.WaitForSelectorOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(10000));
            
            // Get the locator and ensure it's ready
            Locator logoutLocator = page.locator(LOGOUT_LINK);
            System.out.println("Logout link found, attempting to click...");
            
            // Try multiple click strategies
            logoutLocator.click(new Locator.ClickOptions().setTimeout(5000));
            System.out.println("Logout link clicked successfully");
            
        } catch (Exception e) {
            System.out.println("Error clicking logout link: " + e.getMessage());
            // Fallback: try with force click
            try {
                page.locator(LOGOUT_LINK).click(new Locator.ClickOptions().setForce(true));
                System.out.println("Logout link clicked with force");
            } catch (Exception e2) {
                System.out.println("Force click also failed: " + e2.getMessage());
                throw e2;
            }
        }
    }
    
    /**
     * Click on the "All Items" link in the sidebar menu
     */
    public void clickAllItemsLink() {
        page.click(ALL_ITEMS_LINK);
    }
    
    /**
     * Click on the "About" link in the sidebar menu
     */
    public void clickAboutLink() {
        page.click(ABOUT_LINK);
    }
    
    /**
     * Click on the "Reset App State" link in the sidebar menu
     */
    public void clickResetAppStateLink() {
        page.click(RESET_APP_STATE_LINK);
    }
    
    /**
     * Click on the close menu button (X button)
     */
    public void clickCloseMenuButton() {
        page.click(CLOSE_MENU_BUTTON);
    }
    
    /**
     * Perform complete logout by opening menu and clicking logout
     */
    public void logout() {
        System.out.println("Starting logout process...");
        
        // Click menu button
        System.out.println("Clicking menu button...");
        clickMenuButton();
        
        // Wait for sidebar to be fully loaded
        System.out.println("Waiting for sidebar to load...");
        page.waitForTimeout(1000);
        
        // Verify sidebar is visible
        if (isSidebarVisible()) {
            System.out.println("Sidebar is visible, proceeding with logout...");
        } else {
            System.out.println("Warning: Sidebar may not be visible");
        }
        
        // Click logout link
        clickLogoutLink();
    }
    
    /**
     * Check if the sidebar menu is visible
     * @return true if sidebar is visible
     */
    public boolean isSidebarVisible() {
        return page.locator(SIDEBAR).isVisible();
    }
    
    /**
     * Check if the menu button is visible
     * @return true if menu button is visible
     */
    public boolean isMenuButtonVisible() {
        return page.locator(MENU_BUTTON).isVisible();
    }
    
    /**
     * Check if the logout link is visible in the sidebar
     * @return true if logout link is visible
     */
    public boolean isLogoutLinkVisible() {
        return page.locator(LOGOUT_LINK).isVisible();
    }
    
    /**
     * Close the sidebar menu by clicking the overlay
     */
    public void closeMenuByOverlay() {
        page.click(SIDEBAR_OVERLAY);
    }
}
