package page_locator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class rechargePage {

    private WebDriver driver;

    @FindBy(xpath = "//span[contains(text(),'Nạp tiền')]")
    public WebElement recharge;

    @FindBy(xpath = "//div[@class='icon-text']//div")
    private WebElement bank;

    @FindBy(tagName = "input")
    private WebElement recharge_input;

    @FindBy(xpath = "//span[contains(text(),'Gửi yêu cầu')]")
    private WebElement request;

    @FindBy(xpath = "//span[contains(text(),'Xác nhận')]")
    private WebElement confirm;

    @FindBy(xpath = "//span[contains(text(),'Trở về')]")
    public WebElement back;

    @FindBy(className = "has-text-danger")
    private WebElement validation;

    @FindBy(xpath = "//div[@class='title has-text-white']")
    private WebElement title_popup;

    public rechargePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String get_title_modal() {
        return title_popup.getText().strip();
    }

    public boolean verify_title_modal() {
        String modal_title = "Nạp tiền vào ví";
        return get_title_modal().equals(modal_title);
    }

    public String get_bank() {
        return bank.getText().strip();
    }

    public String get_validation() {
        String validation_message = "";
        if (validation != null) {
            validation_message = validation.getText().strip();
        }
        return validation_message;
    }

    public void recharge(String recharge_txt) {
        recharge_input.sendKeys(recharge_txt);
        request.click();
        confirm.click();
    }

    public String get_IDrecharge() {
        WebElement table_recharge = driver.findElement(
                By.xpath("//table[@class='table is-fullwidth is-vcentered is-hoverable is_head_bg is-size-7']"));
        WebElement td = table_recharge.findElement(By.xpath("//tr[1]/td[1]/span"));
        String get_id = td.getText();
        return get_id;
    }

}
