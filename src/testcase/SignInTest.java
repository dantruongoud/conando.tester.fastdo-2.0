package testcase;

import org.openqa.selenium.WebDriver;

import excelHelpers.excelHelpers;
import page_locator.SignInPage;
import common.baseSetup;

public class SignInTest {

	public static void main(String[] args) {
		try {

			//setup dataTest
			excelHelpers excel = new excelHelpers();
			excel.setExcelSheet("login");

			baseSetup init = new baseSetup();
			WebDriver driver = init.initChromeDriver();
			SignInPage signInPage = new SignInPage(driver);

			for (int i = 1; i < 6; i++) {
				System.out.println("=========================");

				System.out.println("Testcase: " + excel.getCellData("TCID", i));
				signInPage.signin(excel.getCellData("username", i), excel.getCellData("password", i));
				Thread.sleep(1000);

				String noti = signInPage.messgaeError();

				switch (noti) {
					case "Tên đăng nhập hoặc mật khẩu không đúng !":
						System.out.println(noti);
						signInPage.passed();
						signInPage.clearTxt();
						break;

					default:
						if (signInPage.verifyPageTitle()) {
							System.out.println("Đăng nhập thành công, chọn phân hệ: ");
							signInPage.chose_company();
							signInPage.passed();
						} else {
							signInPage.failed();
						}
						break;
				}
				Thread.sleep(1000);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
