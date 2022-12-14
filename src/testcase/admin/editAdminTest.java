package testcase.admin;

import org.openqa.selenium.WebDriver;

import common.baseSetup;
import page_locator.SignInPage;
import page_locator.admin.editAdminPage;


public class editAdminTest {
    int testcase;
    String lastname, firstname, phone, passwordold, passwordnew;

    public editAdminTest(int testcase, String lastname, String firstname, String phone, String passwordold,
            String passwordnew) {
        this.testcase = testcase;
        this.lastname = lastname;
        this.firstname = firstname;
        this.phone = phone;
        this.passwordold = passwordold;
        this.passwordnew = passwordnew;
    }

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            using.login_admin();
            editAdminPage edit = new editAdminPage(driver);
            edit.naviga.click();
            using.waitForPageLoaded();
            if (edit.verify_title("Thông tin tài khoản")) {
                editAdminTest[] data = {
                        new editAdminTest(1, "", "firstname", "0328341092", "passwordold", "passwordnew"),
                        new editAdminTest(2, "Nguyen", "", "0328341092", "passwordold", "passwordnew"),
                        new editAdminTest(3, "Nguyen", "Dan Truong", "", "passwordold", "passwordnew"),
                        new editAdminTest(4, "Nguyen", "Dan Truong", "truong", "password", "password"),
                        new editAdminTest(5, "Nguyen", "Dan Truong", "0328341092", "", "passwordnew"),
                        new editAdminTest(6, "Nguyen", "Dan Truong", "0328341092", "123", ""),
                        new editAdminTest(7, "Nguyen", "Dan Truong", "0328341092", "123", "123"),
                        new editAdminTest(8, "Nguyen", "Dan Truong", "0328341092", "dantruong2410", "dantruong2410"),
                };
                for (int i = 0; i < data.length; i++) {
                    System.out.println("=========================");
                    System.out.println("Testcase: " + data[i].testcase);
                    edit.editAdmin(data[i].lastname, data[i].firstname, data[i].phone, data[i].passwordold,
                            data[i].passwordnew);
                    using.button.click();
                    Thread.sleep(1200);
                    String noti = using.messgaeError_tagline();
                    switch (noti) {
                        case "Bạn chưa nhập họ và tên cho tài khoản!":
                            System.out.println(noti);
                            edit.print();
                            break;
                        case "Bạn chưa nhập số điện thoại!":
                            System.out.println(noti);
                            edit.print();
                            break;
                        case "Số điện thoại không đúng!":
                            System.out.println(noti);
                            edit.print();
                            break;
                        case "Bạn chưa nhập mật khẩu cũ!":
                            System.out.println(noti);
                            edit.print();
                            break;
                        case "Bạn chưa nhập mật khẩu mới!":
                            System.out.println(noti);
                            edit.print();
                            break;
                        case "Mật khẩu cũ không chính xác!":
                            System.out.println(noti);
                            edit.print();
                            break;
                        default:
                            noti = using.messgaeError_tagline();
                            if (noti.equals("Đã cập nhật thông tin và mật khẩu thành công!")) {
                                System.out.println(noti);
                                System.out.println("PASSED");
                                System.out.println("=========================");
                            } else {
                                System.out.println("FAILED");
                                System.out.println("=========================");
                            }
                            break;
                    }
                    Thread.sleep(1200);
                }
            } else {
                System.out.println("Title is wrong...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
