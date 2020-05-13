package serenity.features.search;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import serenity.steps.serenity.EmagEndUserSteps;


@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/emagPriceData.csv")
public class SearchByPriceDDT {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @ManagedPages(defaultUrl = "https://www.emag.ro")
    public Pages pages;

    public String name;
    public String price;

    @Qualifier
    public String getQualifier() {
        return name;
    }

    @Steps
    public EmagEndUserSteps endUser;

    @Issue("#EMAG-1")
    @Test
    public void searchEmagByProduct() {
        String prodName = getName();
        if (prodName.equals("iphone 10") || prodName.equals("frigider")) {
            endUser.is_the_home_page();
            endUser.looks_for(prodName);
            endUser.should_see_price(getPrice());
        }
    }

    @Issue("#EMAG-2")
    @Test
    public void searchEmagByProductFilter(){
        String prodName = getName();
        if(prodName.equals("macbook") || prodName.equals("minge")){
            endUser.is_the_home_page();
            endUser.looks_for(prodName);
            endUser.use_recycled_filter();
            endUser.should_see_price(getPrice());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
