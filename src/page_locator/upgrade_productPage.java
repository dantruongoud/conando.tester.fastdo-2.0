package page_locator;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class upgrade_productPage {
    public WebDriver driver;

    @FindBy(tagName = "td")
    private By upgrade_btn;

    @FindBy(how = How.TAG_NAME, using = "tr")
    private List<WebElement> upgrade;

    @FindBy(xpath = "//a[@class='button is-link']")
    public WebElement button;

    @FindBy(css = ".control.has-text-link")
    private WebElement cost;

    @FindBy(xpath = "//li[@class='column is-full']")
    private WebElement donetext;

    @FindBy(tagName = "input")
    private WebElement usercorrect;

    public upgrade_productPage(WebDriver driver) {
        this.driver = driver;
    }

    public void click_upgrade() {
        try {
            for (int i = 0; i < upgrade.size(); i++) {
                List<WebElement> tdList = upgrade.get(i).findElements(upgrade_btn);
                if (tdList.size() == 7) {
                    WebElement product = tdList.get(0);
                    String name_product = product.getText().strip();
                    if (name_product.equals("fOKRs")) {
                        WebElement upgradeBtn = tdList.get(5).findElement(By.tagName("a"));
                        upgradeBtn.click();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void usercorrectInput() {
        try {
            if (usercorrect.isDisplayed()) {
                usercorrect.sendKeys("2");
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
}
