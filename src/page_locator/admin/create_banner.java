package page_locator.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.awt.AWTException;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.Robot;

public class create_banner {
    
    private WebDriver driver;

    @FindBy(css = "a[href='/admin/config/banners']")
    public WebElement naviga;

    @FindBy(css = ".icon-text.has-text-link")
    public WebElement create_btn;

    @FindBy(css = "input[placeholder='Nhập tiêu đề...']")
    public WebElement title_input;

    @FindBy(xpath = "//div[@class='select is_line is-fullwidth']//select")
    private WebElement selected;

    public create_banner(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void select() {
        try {
            Select selectdropdown = new Select(selected);
            selectdropdown.selectByValue("/client/companys");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void print() {
        System.out.println("Status: PASSED");
        System.out.println("=========================");
        title_input.clear();
    }

    public void uploadImage(String upfile_btn, String typeFind) {
        try {
            WebElement upBtn;
            if (typeFind == "id") {
                upBtn = driver.findElement(By.id(upfile_btn));
            } else if (typeFind == "class") {
                upBtn = driver.findElement(By.className(upfile_btn));
            } else {
                upBtn = driver.findElement(By.xpath(upfile_btn));
            }
            String filePath = "C:\\Users\\Admin\\Downloads\\test3.jpg";

            // Click để mở form upload
            upBtn.click();
            Thread.sleep(3000);

            // Khởi tạo Robot class
            Robot rb = null;
            try {
                rb = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }

            // Copy File path vào Clipboard
            StringSelection str = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

            Thread.sleep(2000);

            // Nhấn Control+V để dán
            rb.keyPress(KeyEvent.VK_CONTROL);
            rb.keyPress(KeyEvent.VK_V);

            Thread.sleep(2000);

            // Nhấn Enter
            rb.keyPress(KeyEvent.VK_ENTER);
            rb.keyRelease(KeyEvent.VK_ENTER);

            rb.keyRelease(KeyEvent.VK_CONTROL);

            Thread.sleep(4000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
