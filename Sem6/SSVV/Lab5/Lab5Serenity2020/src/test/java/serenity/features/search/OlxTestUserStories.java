package serenity.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import serenity.steps.serenity.OlxEndUserSteps;

@RunWith(SerenityRunner.class)
public class OlxTestUserStories {

    private String email = "geo.badita@gmail.com";
    private String password = "Vvss-olx?1";
    private String title = "Vand tractor";
    private String desc = "Cel mai bun anunt, descrierea trebuie sa contina 20 de caractere";
    private Integer price = 12000;

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public OlxEndUserSteps user;

    @Issue("#LOGIN-1")
    @Test
    public void user_tries_to_login() {
        user.user_opens_home_page();
        user.goes_to_login();
        user.type_credentials(email, password);
        user.login();
        user.user_should_see_account_data("Sold cont: 0 €");
    }

    @Issue("#ADD-1")
    @Test
    public void user_adds_product() {
        user.user_opens_home_page();
        user.goes_to_login();
        user.type_credentials(email, password);
        user.login();
        user.click_add_button();
        user.add_title(title);
        user.select_category();
        user.add_description(desc);
        user.input_price(price);
        user.select_vendor_type();
        user.submit();
    }

    @Issue("#DEACTIVATE-1")
    @Test
    public void deactivate_prduct(){
        user.user_opens_home_page();
        user.goes_to_login();
        user.type_credentials(email, password);
        user.login();
        user.go_to_my_account_logged_in();
        user.deactivate_product_click();
        user.click_confirmation();
    }

}
