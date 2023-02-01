package testcase;

import org.openqa.selenium.WebDriver;

import excelHelpers.excelHelpers;
import page_locator.SignInPage;
import common.baseSetup;

public class SignInTest {

	public static void main(String[] args) {
		try {

			// setup dataTest
			excelHelpers excel = new excelHelpers();
			excel.setExcelSheet("login");

			baseSetup init = new baseSetup();
			WebDriver driver = init.initChromeDriver();
			SignInPage index = new SignInPage(driver);

			for (int i = 1; i < 6; i++) {
				System.out.println("=========================");

				System.out.println("Testcase: " + excel.getCellData("TCID", i));
				index.signin(excel.getCellData("username", i), excel.getCellData("password", i));
				Thread.sleep(1000);

				String noti = index.messgaeError();

				switch (noti) {
					case "Tên đăng nhập hoặc mật khẩu không đúng !":
						System.out.println(noti);
						index.passed();
						index.clearTxt();
						break;

					default:
						if (index.verifyPageTitle()) {
							System.out.println("Đăng nhập thành công, chọn phân hệ: ");
							index.chose_company();
							index.passed();
						} else {
							index.failed();
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
