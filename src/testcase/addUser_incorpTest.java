package testcase;

import org.openqa.selenium.WebDriver;

import page_locator.SignInPage;
import page_locator.addUser_incorpPage;
import page_locator.edit_incorpPage;
import common.baseSetup;

public class addUser_incorpTest {
    
    int testcase;
    String username;
    String lastname;

    public addUser_incorpTest(int testcase, String username, String lastname) {
        this.testcase = testcase;
        this.username = username;
        this.lastname = lastname;
    }

    public static void main(String[] args) {
        try {
            addUser_incorpTest[] list_data_test = {
                    new addUser_incorpTest(1, "", "Nguyen"),
                    new addUser_incorpTest(2, "ndtruong.conando@gmail.com", ""),
                    new addUser_incorpTest(3, "ndtruong@gmail.com", ""),
                    new addUser_incorpTest(4, "ndruong@gmail.com", "Nguyen"),
            };

            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            edit_incorpPage get = new edit_incorpPage(driver);
            addUser_incorpPage addUser = new addUser_incorpPage(driver);

            using.login();
            get.clickNavigation();
            get.find.click();
            addUser.naviga_user();

            if (addUser.verifyTitle()) {
                for (int i = 0; i < list_data_test.length; i++) {
                    System.out.println("=========================");

                    System.out.println("Testcase: " + list_data_test[i].testcase);
                    addUser.createUser(list_data_test[i].username, list_data_test[i].lastname);
                    Thread.sleep(1000);

                    String noti = using.messgaeError_tagline();
                    Thread.sleep(1000);
                    switch (noti) {
                        case "Bạn chưa nhập địa chỉ email, hoặc địa chỉ email không đúng !":
                            System.out.println(noti);
                            addUser.print();
                            break;

                        case "Bạn chưa nhập họ và tên cho tài khoản!":
                            System.out.println(noti);
                            addUser.print();
                            break;

                        case "Địa chỉ email này đã có trong tổ chức của bạn.":
                            System.out.println(noti);
                            addUser.print();
                            break;

                        default:
                            noti = using.messgaeError_tagline();
                            if (noti != null) {
                                System.out.println(noti);
                                addUser.print();
                            } else {
                                using.failed();
                            }
                            break;
                    }
                    Thread.sleep(1000);
                }
            } else {
                using.failed();
                System.out.println("Verify title popup is invalid...");
                driver.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
