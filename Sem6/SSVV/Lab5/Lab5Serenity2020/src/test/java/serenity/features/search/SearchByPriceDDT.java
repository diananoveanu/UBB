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
    public String correct;

    @Qualifier
    public String getQualifier() {
        return name;
    }

    @Steps
    public EmagEndUserSteps endUser;

    @Issue("#EMAG-1")
    @Test
    public void searchEmagByProduct() {
        if (getCorrect().equals("t")) {
            String prodName = getName();
            endUser.is_the_home_page();
            endUser.looks_for(prodName);
            endUser.should_see_price(getPrice());
        } else {
            String prodName = getName();
            endUser.is_the_home_page();
            endUser.looks_for(prodName);
            endUser.should_not_find_requested_price(getPrice());
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

    public String getCorrect() {
        return correct;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
