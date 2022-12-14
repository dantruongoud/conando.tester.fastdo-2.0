package testcase;

import org.openqa.selenium.WebDriver;

import page_locator.SignInPage;
import page_locator.incorpPage;
import common.baseSetup;

public class create_incorpTest {
    int testcase;
    String name_incorp, address_incorp, phone_incorp, mail_incorp;

    public create_incorpTest(int testcase, String name_incorp, String address_incorp, String phone_incorp,
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
            SignInPage login_all = new SignInPage(driver);
            incorpPage incorppage = new incorpPage(driver);

            login_all.login();
            incorppage.clickNavigation();
            incorppage.add_incorp.click();
            incorppage.veryfi_modal();

            for (int i = 0; i < list_dataTest.length; i++) {
                System.out.println("=========================");

                System.out.println("Testcase: " + list_dataTest[i].testcase);
                incorppage.create_incorp(list_dataTest[i].name_incorp, list_dataTest[i].address_incorp,
                        list_dataTest[i].phone_incorp, list_dataTest[i].mail_incorp);
                Thread.sleep(1200);

                String noti = incorppage.messgaeError();
                switch (noti) {
                    case "Bạn chưa nhập tên tổ chức.":
                        incorppage.print();
                        break;
                    case "Bạn chưa nhập địa chỉ tổ chức.":
                        incorppage.print();
                        break;
                    case "Bạn chưa nhập Số điện thoại người đại diện.":
                        incorppage.print();
                        break;
                    case "Bạn chưa nhập email tổ chức.":
                        incorppage.print();
                        break;
                    default:
                        if (incorppage.messgaeError() != null) {
                            System.out.println(incorppage.messgaeError());
                            System.out.println("Hoàn tất tạo mới tổ chức, nhập mã xác thực ở email");
                            login_all.passed();
                        } else {
                            login_all.failed();
                        }
                        break;
                }
                Thread.sleep(1200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
