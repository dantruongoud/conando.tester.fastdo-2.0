package page_locator;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class upgrade_productPage {
    public WebDriver driver;

    @FindBy(xpath = "//a[@class='button is-link']")
    public WebElement button;

    @FindBy(css = ".control.has-text-link")
    private WebElement cost;

    @FindBy(xpath = "//li[@class='column is-full']")
    private WebElement donetext;

    @FindBy(tagName = "input")
    public WebElement usercorrect;

    @FindBy(xpath = "//a[@class='button']")
    public WebElement btnBack;

    public upgrade_productPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void click_upgrade() {
        try {
            List<WebElement> upgrade = driver.findElements(By.tagName("tr"));
            for (int i = 0; i < upgrade.size(); i++) {
                List<WebElement> tdList = upgrade.get(i).findElements(By.tagName("td"));
                if (tdList.size() == 7) {
                    WebElement product = tdList.get(0);
                    String name_product = product.getText().strip();
                    if (name_product.equals("fOKRs")) {
                        WebElement upgradeBtn = tdList.get(6).findElement(By.tagName("a"));
                        upgradeBtn.click();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String price_amount() {
        String getprice = cost.getText().strip();
        return getprice;
    }

    public String get_textdone() {
        return donetext.getText();
    }

    public void doubleClick() {
        try {
            button.click();
            Thread.sleep(1000);
            button.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
