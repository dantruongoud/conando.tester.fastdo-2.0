package testcase;

import org.openqa.selenium.WebDriver;

import page_locator.SignInPage;
import page_locator.edit_incorpPage;
import common.baseSetup;

public class edit_incorpTest {

    int testcase;
    String name_incorp, address_incorp, phone_incorp, mail_incorp;

    public edit_incorpTest(int testcase, String name_incorp, String address_incorp, String phone_incorp,
            String mail_incorp) {
        this.testcase = testcase;
        this.name_incorp = name_incorp;
        this.address_incorp = address_incorp;
        this.phone_incorp = phone_incorp;
        this.mail_incorp = mail_incorp;
    }

    public static void main(String[] args) {
        try {

            create_incorpTest[] list_dataTest = {
                    new create_incorpTest(1, "", "23 Trường Thi 1", "0328341092", "conando.vn@gmail.com"),
                    new create_incorpTest(2, "Do Corp", "", "0328341092", "conando.vn@gmail.com"),
                    new create_incorpTest(3, "Do Corp", "23 Trường Thi 1", "", "conando.vn@gmail.com"),
                    new create_incorpTest(4, "Do Corp", "23 Trường Thi 1", "0328341092", ""),
                    new create_incorpTest(5, "Do Corp", "23 Trường Thi 1", "0328341092", "conando.vn@gmail.com")

            };

            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            edit_incorpPage edit_incorp = new edit_incorpPage(driver);

            using.login();
            edit_incorp.clickNavigation();
            edit_incorp.find.click();
            edit_incorp.edit.click();

            if (edit_incorp.verify_popup()) {

                edit_incorp.clearTXT();

                for (int i = 0; i < list_dataTest.length; i++) {
                    System.out.println("=========================");

                    System.out.println("Testcase: " + list_dataTest[i].testcase);
                    edit_incorp.create_incorp(list_dataTest[i].name_incorp, list_dataTest[i].address_incorp,
                            list_dataTest[i].phone_incorp, list_dataTest[i].mail_incorp);
                    Thread.sleep(1500);

                    String noti = using.messgaeError_tagline();
                    
                    switch (noti) {
                        case "Bạn chưa nhập tên tổ chức.":
                            System.out.println(noti);
                            edit_incorp.print();
                            break;
                        case "Bạn chưa nhập địa chỉ tổ chức.":
                            System.out.println(noti);
                            edit_incorp.print();
                            break;
                        case "Bạn chưa nhập Số điện thoại người đại diện.":
                            System.out.println(noti);
                            edit_incorp.print();
                            break;
                        case "Bạn chưa nhập email tổ chức.":
                            System.out.println(noti);
                            edit_incorp.print();
                            break;
                        default:
                            noti = using.messgaeError_tagline();
                            if (noti != null) {
                                System.out.println(noti);
                                System.out.println("Hoàn tất chỉnh sửa tổ chức");
                                using.passed();
                            } else {
                                using.failed();
                            }
                            break;
                    }
                    Thread.sleep(1000);
                }
            } else {
                using.failed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
