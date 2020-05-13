package serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("https://www.emag.ro")
public class EmagPage extends PageObject {

    // find search bar
    @FindBy(name = "query")
    private WebElementFacade searchProducts;

    // find search button
    @FindBy(xpath = "//button[@class='btn btn-default searchbox-submit-button']")
    private WebElementFacade lookupButton;

    // enter word to search
    public void enter_keywords(String keyword) {
        searchProducts.type(keyword);
    }

    // click search button
    public void lookup_products() {
        lookupButton.click();
    }

    // activate filter
    public void use_recycled_filter() {
        String oldTab = getDriver().getWindowHandle();
        getDriver().findElements(By.tagName("a"))
                .stream()
                .filter(x -> x.getText().contains("Resigilate"))
                .collect(Collectors.toList()).get(0).click();
        getDriver().switchTo();

        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        tabs.remove(oldTab);
        getDriver().switchTo().window(tabs.get(0));
    }

    // take product prices
    public List<String> getProductPrice() {
        return getDriver().findElements(By.xpath("//p[@class='product-new-price']"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }


}