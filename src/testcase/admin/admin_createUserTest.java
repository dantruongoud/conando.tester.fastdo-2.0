package testcase.admin;

import org.openqa.selenium.WebDriver;

import common.baseSetup;
import page_locator.SignInPage;
import page_locator.admin.admin_addUserPage;

public class admin_createUserTest {
    int testcase;
    String username;
    String phone;
    String firstname;
    String lastname;
    // String password;

    public admin_createUserTest(int testcase, String username, String phone, String firstname, String lastname) {
        this.testcase = testcase;
        this.username = username;
        this.phone = phone;
        this.firstname = firstname;
        this.lastname = lastname;
        // this.password = password;
    }

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            admin_addUserPage admin_createUser = new admin_addUserPage(driver);
            using.login_admin();
            admin_createUser.crud_user.click();
            using.waitForPageLoaded();
            if (admin_createUser.verifyTitle()) {
                admin_createUser.add.click();
                admin_createUserTest[] list_data_Test = {
                        new admin_createUserTest(1, "", "d", "Nguyen", "Truong"),
                        new admin_createUserTest(2, "truong", "d", "Nguyen", "Truong"),
                        new admin_createUserTest(3, "truong@gmail.com", "", "Nguyen", "Truong"),
                        new admin_createUserTest(4, "truong@gmail.com", "d", "Nguyen", "Truong"),
                        new admin_createUserTest(5, "truong@gmail.com", "0328651245", "", "Truong"),
                        new admin_createUserTest(6, "truong@gmail.com", "12", "Nguyen", "Truong"),
                        new admin_createUserTest(7, "truong@gmail.com", "0328341092", "Nguyen", ""),
                        new admin_createUserTest(8, "ndtruong.conando@gmail.com", "0328341092", "Nguyen", "Truong"),
                        new admin_createUserTest(9, "ndtruong@gmail.com", "0328341092", "Nguyen", "Truong")
                };
                for (int i = 0; i < list_data_Test.length; i++) {
                    System.out.println("=========================");
                    System.out.println("Testcase: " + list_data_Test[i].testcase);
                    admin_createUser.register(list_data_Test[i].username, list_data_Test[i].phone,
                            list_data_Test[i].firstname,
                            list_data_Test[i].lastname);
                    Thread.sleep(1200);
                    String noti = using.messgaeError_tagline();
                    switch (noti) {
                        case "Bạn chưa nhập địa chỉ email!":
                            System.out.println(noti);
                            admin_createUser.print();
                            break;
                        case "Địa chỉ email không đúng!":
                            System.out.println(noti);
                            admin_createUser.print();
                            break;
                        case "Bạn chưa nhập họ và tên cho tài khoản!":
                            System.out.println(noti);
                            admin_createUser.print();
                            break;
                        case "Bạn chưa nhật mật khẩu cho tài khoản!":
                            System.out.println(noti);
                            admin_createUser.print();
                            break;
                        case "Bạn chưa nhập số điện thoại!":
                            System.out.println(noti);
                            admin_createUser.print();
                            break;
                        case "Số điện thoại không đúng định dạng!":
                            System.out.println(noti);
                            admin_createUser.print();
                            break;
                        case "Email này đã được sử dụng, vui lòng sử dụng email khác!":
                            System.out.println(noti);
                            admin_createUser.print();
                            break;
                        default:
                            noti = using.messgaeError_tagline();
                            if (noti != null) {
                                System.out.println(noti);
                                Thread.sleep(1000);
                                noti = using.messgaeError_tagline();
                                System.out.println(noti);
                                System.out.println("Hoàn tất thêm mới người dùng");
                                System.out.println("=========================");
                            } else {
                                System.out.println("Failed");
                            }
                            break;
                    }
                    Thread.sleep(1200);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
