package testcase.admin;

import org.openqa.selenium.WebDriver;

import common.baseSetup;
import page_locator.SignInPage;
import page_locator.admin.createAdminPage;


public class createAdminTest {
    int testcase;
    String email, lastname, firstname, password;

    public createAdminTest(int testcase, String email, String lastname, String firstname, String password) {
        this.testcase = testcase;
        this.email = email;
        this.lastname = lastname;
        this.firstname = firstname;
        this.password = password;
    }

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            using.login_admin();
            createAdminPage account_admin = new createAdminPage(driver);
            account_admin.setting.click();
            using.waitForPageLoaded();
            account_admin.accountAdmin.click();
            using.waitForPageLoaded();
            if (account_admin.verifyTitle()) {
                account_admin.create_btn.click();
                createAdminTest[] list_data_test = {
                        new createAdminTest(1, "", "Nguyen", "Trường", "123456"),
                        new createAdminTest(2, "ndtruong", "nguyen", "Trường", "123456"),
                        new createAdminTest(3, "nguyendantruongg@gmail.com", "", "Trường", "123456"),
                        new createAdminTest(4, "nguyendantruongg@gmail.com", "Nguyen", "", "132456"),
                        new createAdminTest(5, "nguyendantruongg@gmail.com", "Nguyen", "Trường", ""),
                        new createAdminTest(6, "ndtruong.conando@gmail.com", "Nguyen", "Trường", "123456"),
                        new createAdminTest(7, "nguyendantruongg@gmail.com", "Nguyen", "Trường", "123456")
                };
                for (int i = 0; i < list_data_test.length; i++) {
                    System.out.println("=========================");
                    System.out.println("Testcase: " + list_data_test[i].testcase);
                    account_admin.create_account(list_data_test[i].email, list_data_test[i].lastname,
                            list_data_test[i].firstname, list_data_test[i].password);
                    Thread.sleep(1200);
                    String noti = using.messgaeError_tagline();
                    switch (noti) {
                        case "Bạn chưa nhập địa chỉ email!":
                            System.out.println(noti);
                            account_admin.print();
                            break;
                        case "Địa chỉ email không đúng!":
                            System.out.println(noti);
                            account_admin.print();
                            break;
                        case "Bạn chưa nhập họ và tên cho tài khoản!":
                            System.out.println(noti);
                            account_admin.print();
                            break;
                        case "Bạn chưa nhật mật khẩu cho tài khoản!":
                            System.out.println(noti);
                            account_admin.print();
                            break;
                        case "Email này đã được sử dụng, vui lòng sử dụng email khác!":
                            System.out.println(noti);
                            account_admin.print();
                            break;
                        default:
                            noti = using.messgaeError_tagline();
                            if (noti.equals("Đã cập nhật tài khoản thành công!")) {
                                System.out.println(noti);
                                System.out.println("Passed");
                                System.out.println("=========================");
                            } else {
                                System.out.println("Failed");
                            }
                            break;
                    }
                    Thread.sleep(1200);
                }
            } else {
                System.out.println("Sai tiêu đề trang hiển thị...");
                driver.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
