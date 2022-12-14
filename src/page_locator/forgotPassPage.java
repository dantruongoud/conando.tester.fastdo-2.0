package page_locator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class forgotPassPage {
    public WebDriver driver;

    @FindBy(xpath = "//a[@class='button is-white is-fullwidth']")
    public WebElement forgot_btn;

    @FindBy(tagName = "input")
    private WebElement email_input;

    @FindBy(css = ".button.is-link.is-fullwidth")
    private WebElement contineu_btn;

    @FindBy(css = ".title.is-size-6")
    private WebElement titlePage;

    public forgotPassPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        String a = "";
        if (titlePage.isDisplayed()) {
            a = titlePage.getText().strip();
            System.out.println(a);
        }
        return a;
    }

    public boolean verifyTitle() {
        String b = "Quên mật khẩu";
        return getTitle().equals(b);
    }

    public void forgot(String email) {
        try {
            email_input.sendKeys(email);
            contineu_btn.click();
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cleartxt() {
        try {
            if (email_input.isDisplayed()) {
                email_input.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void print() {
        System.out.println("Passed");
        System.out.println("=========================");
    }
}
