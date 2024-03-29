package testcase.System;

import org.openqa.selenium.WebDriver;

import page_locator.SignInPage;
import page_locator.shopPage;
import common.baseSetup;

public class shopTest {

    int testcase;
    String user;
    String month;

    public shopTest(int testcase, String user, String month) {
        this.testcase = testcase;
        this.user = user;
        this.month = month;
    }

    public static void main(String[] args) {
        try {

            shopTest[] list_data_test = {
                    new shopTest(1, "0", "0"),
                    new shopTest(2, "30", "1"),
                    new shopTest(3, "5", "3"),
            };

            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            shopPage shoppage = new shopPage(driver);
            SignInPage index = new SignInPage(driver);

            index.login();
            shoppage.naviga.click();
            index.waitForPageLoaded();

            if (shoppage.verifyTitle()) {

                shoppage.list_shop.click();
                index.waitForPageLoaded();

                shoppage.buy.click();
                index.waitForPageLoaded();
                if (shoppage.verify_title_modal()) {

                    shoppage.chose_incorp.click();
                    Thread.sleep(1000);
                    shoppage.list_incorp.click();

                    Thread.sleep(1200);
                    shoppage.clearTxt();
                    for (int i = 0; i < list_data_test.length; i++) {
                        System.out.println("=========================");

                        System.out.println("Testcase: " + list_data_test[i].testcase);
                        shoppage.buy_product(list_data_test[i].user, list_data_test[i].month);
                        Thread.sleep(1300);

                        String noti = index.messgaeError_tagline();
                        switch (noti) {
                            case "Số lượng người dùng tối thiểu là 5 người.":
                                System.out.println(noti);
                                // shoppage.del_tagline();
                                shoppage.clearTxt();
                                index.passed();
                                break;
                            case "Số tháng sử dụng tối thiểu là 3 tháng.":
                                System.out.println(noti);
                                index.btnDelTagline.click();
                                shoppage.clearTxt();
                                index.passed();
                                break;
                            default:
                                noti = index.messgaeError_tagline();
                                if (noti.length() == 0) {

                                    shoppage.confirm.click();

                                    if (shoppage.verify_donetext()) {
                                        System.out.println(shoppage.get_text());
                                        index.passed();
                                    }
                                } else {
                                    index.failed();
                                }
                                break;
                        }
                        Thread.sleep(1200);
                    }
                } else {
                    index.failed();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
