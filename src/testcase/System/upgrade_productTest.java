package testcase.System;

import org.openqa.selenium.WebDriver;

import common.baseSetup;
import page_locator.SignInPage;
import page_locator.edit_incorpPage;
import page_locator.upgrade_productPage;

public class upgrade_productTest {
    int testcase;
    String user;

    public upgrade_productTest(int testcase, String user) {
        this.testcase = testcase;
        this.user = user;
    }

    public static void main(String[] args) {
        try {

            upgrade_productTest[] data = {
                    new upgrade_productTest(1, "100"),
                    new upgrade_productTest(2, "0"),
                    new upgrade_productTest(3, "2"),
                    new upgrade_productTest(4, "6"),
            };

            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            edit_incorpPage get = new edit_incorpPage(driver);
            upgrade_productPage upgrade = new upgrade_productPage(driver);

            using.login();
            get.clickNavigation();
            get.find.click();
            upgrade.click_upgrade();

            System.out.println("=========================");
            System.out.println("Testcase: " + data[0].testcase);
            upgrade.usercorrect.sendKeys(data[0].user);

            upgrade.doubleClick();

            String noti = using.messgaeError_tagline();
            if (noti.equals("Số dư của bạn không đủ để thanh toán, vui lòng nạp thêm.")) {
                System.out.println(noti);
                using.passed();
                upgrade.btnBack.click();
                using.waitForPageLoaded();

                for (int i = 1; i < data.length; i++) {

                    upgrade.usercorrect.clear();
                    System.out.println("=========================");

                    System.out.println("Testcase: " + data[i].testcase);
                    upgrade.usercorrect.sendKeys(data[i].user);
                    upgrade.doubleClick();

                    noti = using.messgaeError_tagline();
                    switch (noti) {
                        case "Số lượng người dùng phải lớn hơn gói hiện tại.":
                            System.out.println(noti);
                            using.passed();
                            upgrade.btndeleteTagline.click();
                            break;
                        default:
                            if (upgrade.get_textdone() != null) {
                                System.out.println(upgrade.get_textdone());
                                using.passed();
                                upgrade.button.click();
                            } else {
                                using.failed();
                            }
                            break;
                    }
                    Thread.sleep(1200);
                }
            } else {
                using.failed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
