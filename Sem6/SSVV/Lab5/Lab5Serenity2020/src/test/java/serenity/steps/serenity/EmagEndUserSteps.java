package serenity.steps.serenity;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import serenity.pages.EmagPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EmagEndUserSteps {

    EmagPage emagPage;

    @Step
    public void enters(String keyword) {
        emagPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search() {
        emagPage.lookup_products();
    }

    @Step
    public void should_see_price(String price) {
        assertThat(emagPage.getProductPrice(), hasItem(containsString(price)));
    }

    @Step
    public void should_not_find_requested_price(String price){
        assertThat(emagPage.getProductPrice(), not(hasItem(containsString(price))));
    }

    @Step
    public void is_the_home_page() {
        emagPage.open();
    }

    @Step
    public void looks_for(String price) {
        enters(price);
        starts_search();
    }

    @Step
    public void use_recycled_filter(){
        emagPage.use_recycled_filter();
    }


}