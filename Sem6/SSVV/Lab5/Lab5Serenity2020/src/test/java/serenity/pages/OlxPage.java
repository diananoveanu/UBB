package serenity.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("https://www.olx.ro")
public class OlxPage extends PageObject {

    @FindBy(xpath = "//a[@class='userbox-login tdnone']")
    private WebElementFacade myAccount;

    @FindBy(name = "login[email]")
    private WebElementFacade emailField;

    @FindBy(name = "login[password]")
    private WebElementFacade passwordField;

    @FindBy(xpath="//button[@class='login-button login-button--submit']")
    private WebElementFacade loginButton;

    @FindBy(xpath = "//a[@class='postnewlink fbold tdnone']")
    private WebElementFacade addButton;

    @FindBy(name="data[title]")
    private WebElementFacade title;

    @FindBy(id = "targetrenderSelect1-0")
    private WebElementFacade category;

    @FindBy(name="data[description]")
    private WebElementFacade description;

    @FindBy(xpath = "//span[@class='caticon cat-icon-5']")
    private WebElementFacade autoCategory;

    @FindBy(name="data[param_price][1]")
    private WebElementFacade inputPrice;

    @FindBy(id="save")
    private WebElementFacade continueAddBtn;

    @FindBy(xpath = "//div[@class='inlblk rel']")
    private WebElementFacade accountBtn;

    @FindBy(xpath = "//span[@class='cirlce-icon']")
    private WebElementFacade confirmationBtn;

    public void go_to_my_account_logged_in(){
        accountBtn.click();
    }

    public void deactivate_product_click(){
        List<WebElement> strs = getDriver().findElements(By.tagName("a"))
                .stream()
                .filter(x -> x.getText().equals("Dezactiveaza"))
                .collect(Collectors.toList());

        strs.forEach(x -> System.out.println(x.getText()));

        strs.get(0).click();
    }

    public void click_confirmation(){
        confirmationBtn.click();
    }

    public void go_to_my_account(){
       myAccount.click();
    }

    public void type_credentials(String email, String password){
        emailField.type(email);
        passwordField.type(password);
    }

    public void login(){
        loginButton.click();
    }

    public void click_add_button(){
        addButton.click();
    }

    public void add_title(String title){
        this.title.type(title);
    }

    public void select_category(){
        category.click();
        autoCategory.click();
        getDriver().findElements(By.xpath("//a[@class='icon-list block rel lheight16 tdnone clr']")).get(1).click();
    }

    public void add_description(String desc){
        description.type(desc);
    }

    public void input_price(Integer price){
        inputPrice.type(price.toString());
    }

    public void select_vendor_type(){
        getDriver()
                .findElements(By.xpath("//span[@class='chips__text']"))
                .get(8).click();
    }

    public void submit(){
        continueAddBtn.click();
    }

    public List<String> getSoldText(){
        List<String> strs = getDriver()
                .findElements(By.xpath("//span[@class='paybalance-box__balance-label ']"))
                .stream()
                .map(x -> x.getText())
                .collect(Collectors.toList());
        return strs;
    }
}