package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.page.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.page.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.page.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationTests {

	@LocalServerPort
	private Integer port;
	private String baseUrl;

	private static WebDriver driver;

	private static LoginPage loginPage;
	private static SignupPage signupPage;
	private static HomePage homePage;

	@BeforeAll
	static void beforeAll(){
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		loginPage = new LoginPage(driver);
		signupPage = new SignupPage(driver);
		homePage = new HomePage(driver);
	}

	@AfterAll
	static void afterAll(){
		driver.close();
	}

	@BeforeEach
	void beforeEach(){
		baseUrl = "http://localhost:" + port + "/";
		driver.get(baseUrl);

	}

	@Test
	void authorizedAccessTest(){
		// test that the user was redirected to login page at first load
		assertNotEquals(baseUrl,driver.getCurrentUrl());
		assertEquals(baseUrl + "login",driver.getCurrentUrl());

		loginPage.goToSignup();

		assertEquals(baseUrl  + "signup",driver.getCurrentUrl());
	}

	@Test
	void testSignupLoginLogout(){

		loginPage.goToSignup();

		String firstName = "First";
		String lastName = "Last";

		String username = "user1";
		String password = "123";

		// test successful sigunp
		signupPage.signup(firstName, lastName, username, password);

		assertEquals(baseUrl+ "login", driver.getCurrentUrl());

		String signupMessage = loginPage.getSignupMessage();
		assertNotNull(signupMessage);
		assertEquals("Signup Successful, please login.",signupMessage);

		loginPage.login(username, password);
		assertEquals(baseUrl, driver.getCurrentUrl());

		homePage.logout();

		assertEquals(baseUrl+ "login", driver.getCurrentUrl());

		driver.get(baseUrl);

		assertEquals(baseUrl+ "login", driver.getCurrentUrl());

	}

}
