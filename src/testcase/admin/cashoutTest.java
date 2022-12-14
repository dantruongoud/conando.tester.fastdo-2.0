package testcase.admin;

import org.openqa.selenium.WebDriver;

import common.baseSetup;
import page_locator.SignInPage;
import page_locator.admin.admin_requestAmountPage;
import page_locator.admin.cashoutPage;

public class cashoutTest {
    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            using.login_admin();

            admin_requestAmountPage use = new admin_requestAmountPage(driver);
            page_locator.admin.rechargePage add_money = new page_locator.admin.rechargePage(driver);
            use.crud_request.click();
            if (use.verify_title()) {
                cashoutPage cashout = new cashoutPage(driver);
                cashout.naviga_cashout.click();
                using.waitForPageLoaded();
                if (cashout.verifyTitle()) {
                    cashout.cashoutbtn.click();
                    Thread.sleep(1000);
                    add_money.confirm_btn.click();
                    Thread.sleep(1000);
                    System.out.println("=========================");
                    System.out.println("Testcase: 1");
                    String validation_input = add_money.validation();
                    if (validation_input != null) {
                        System.out.println(validation_input);
                        System.out.println("Passed");
                        add_money.tr.click();
                        add_money.confirm_btn.click();
                        validation_input = add_money.validation();
                        if (validation_input != null) {
                            System.out.println("=========================");
                            System.out.println("Testcase: 2");
                            System.out.println(validation_input);
                            System.out.println("Passed");
                            cashout.system_cashout("123", "Lấy tiền người giàu");
                            add_money.confirm_btn.click();
                            String noti = using.messgaeError_tagline();
                            if (noti != null) {
                                System.out.println("=========================");
                                System.out.println("Testcase: 3");
                                System.out.println(noti);
                                System.out.println("Passed");
                            } else {
                                System.out.println("Failed");
                                driver.close();
                            }
                        } else {
                            System.out.println("Failed");
                            driver.close();
                        }
                    } else {
                        System.out.println("Failed");
                        driver.close();
                    }
                }
            } else {
                System.out.println("Sai tiêu trang hiển thị...");
                driver.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}