package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

public class MenuPage {
    private Page page;
    
    // Locators
    private final String MENU_BUTTON = "#react-burger-menu-btn";
    private final String LOGOUT_LINK = "[data-test='logout-sidebar-link']";
    private final String SIDEBAR = ".bm-menu";

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

    public boolean isSidebarVisible() {
        return page.locator(SIDEBAR).isVisible();
    }

}
