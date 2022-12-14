package testcase.admin;

import org.openqa.selenium.WebDriver;

import common.baseSetup;
import page_locator.SignInPage;
import page_locator.admin.admin_requestAmountPage;
import page_locator.admin.trackingPage;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;

public class trackingTest {
    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            admin_requestAmountPage get = new admin_requestAmountPage(driver);
            using.login();
            trackingPage trackingpage = new trackingPage(driver);
            trackingpage.naviga.click();
            using.waitForPageLoaded();
            if (trackingpage.verify_title()) {
                String idgiaodich = trackingpage.get_tracking1();
                StringSelection str = new StringSelection(idgiaodich);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

                Thread.sleep(2000);

                trackingpage.click_logout();
                using.login_admin();
                get.crud_request.click();
                using.waitForPageLoaded();
                trackingpage.naviga_command.click();
                using.waitForPageLoaded();
                trackingpage.search_input.click();
                // Nhấn Control+V để dán
                Robot rb = null;
                try {
                    rb = new Robot();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
                rb.keyPress(KeyEvent.VK_CONTROL);
                rb.keyPress(KeyEvent.VK_V);
                rb.keyRelease(KeyEvent.VK_CONTROL);
                rb.keyRelease(KeyEvent.VK_V);
                rb.keyPress(KeyEvent.VK_ENTER);
                rb.keyRelease(KeyEvent.VK_ENTER);
                trackingpage.get_datatracking();
            } else {
                System.out.println("Tiêu đề sai");
                driver.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
