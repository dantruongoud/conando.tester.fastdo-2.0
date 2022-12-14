package testcase.admin;

import org.openqa.selenium.WebDriver;

import page_locator.SignInPage;
import page_locator.admin.admin_activeIncorpPage;
import common.*;

public class admin_activeIncorpTest {
    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            using.login_admin();
            admin_activeIncorpPage admin_active = new admin_activeIncorpPage(driver);
            admin_active.crud_user.click();
            using.waitForPageLoaded();
            if (admin_active.verifyTitle()) {
                admin_active.chose_user();
                admin_active.find.click();
                admin_active.hover_menu();
                admin_active.chose_edit.click();
                admin_active.active.click();
                admin_active.save.click();
                if (using.messgaeError_tagline() != null) {
                    System.out.println(using.messgaeError_tagline());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
