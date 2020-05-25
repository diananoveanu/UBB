package serenity.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import serenity.pages.OlxPage;

import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class OlxEndUserSteps {

    OlxPage olxPage;

    @Step
    public void user_opens_home_page() {
        olxPage.open();
    }

    @Step
    public void goes_to_login() {
        olxPage.go_to_my_account();
    }


    @Step
    public void type_credentials(String email, String password) {
        olxPage.type_credentials(email, password);
    }

    @Step
    public void login() {
        olxPage.login();
    }

    @Step
    public void user_should_see_account_data(String soldText) {
        assertThat(olxPage.getSoldText(), hasItem(containsString(soldText)));
    }

    @Step
    public void click_add_button(){
        olxPage.click_add_button();
    }

    @Step
    public void add_title(String title){
        olxPage.add_title(title);
    }

    @Step
    public void select_category(){
        olxPage.select_category();
    }

    @Step
    public void add_description(String desc){
        olxPage.add_description(desc);
    }

    @Step
    public void input_price(Integer price){
        olxPage.input_price(price);
    }

    @Step
    public void select_vendor_type(){
        olxPage.select_vendor_type();
    }

    @Step
    public void submit(){
        olxPage.submit();
    }

    @Step
    public void go_to_my_account_logged_in(){
        olxPage.go_to_my_account_logged_in();
    }

    @Step
    public void deactivate_product_click(){
        olxPage.deactivate_product_click();
    }

    @Step
    public void click_confirmation(){
        olxPage.click_confirmation();
    }
}