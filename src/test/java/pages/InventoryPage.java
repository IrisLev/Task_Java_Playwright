package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

public class InventoryPage {
    private Page page;
    
    // Locators
    private final String PRODUCT_CONTAINER = ".inventory_item";
    private final String PRODUCT_NAME = ".inventory_item_name";
    private final String PRODUCT_DESCRIPTION = ".inventory_item_desc";
    private final String PRODUCT_PRICE = ".inventory_item_price";
    
    public InventoryPage(Page page) {
        this.page = page;
    }
    
    /**
     * Get the name of the first product
     * @return the product name
     */
    public String getFirstProductName() {
        Locator firstProduct = page.locator(PRODUCT_CONTAINER).first();
        return firstProduct.locator(PRODUCT_NAME).textContent();
    }
    
    /**
     * Get the description of the first product
     * @return the product description
     */
    public String getFirstProductDescription() {
        Locator firstProduct = page.locator(PRODUCT_CONTAINER).first();
        return firstProduct.locator(PRODUCT_DESCRIPTION).textContent();
    }
    
    /**
     * Get the price of the first product
     * @return the product price
     */
    public String getFirstProductPrice() {
        Locator firstProduct = page.locator(PRODUCT_CONTAINER).first();
        return firstProduct.locator(PRODUCT_PRICE).textContent();
    }
    
    /**
     * Print the description and price of the first product
     */
    public void printFirstProductInfo() {
        String description = getFirstProductDescription();
        String price = getFirstProductPrice();
        
        System.out.println("First Product Information:");
        System.out.println("Description: " + description);
        System.out.println("Price: " + price);
    }
    

    
    /**
     * Check if the inventory page is loaded
     * @return true if products are visible
     */
    public boolean isInventoryPageLoaded() {
        return page.locator(PRODUCT_CONTAINER).first().isVisible();
    }
    
    /**
     * Get the total number of products on the page
     * @return the count of products
     */
    public int getProductCount() {
        return page.locator(PRODUCT_CONTAINER).count();
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
