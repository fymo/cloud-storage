package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.page.CredentialsTab;
import com.udacity.jwdnd.course1.cloudstorage.page.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.page.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.page.SignupPage;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialsTest {


    @LocalServerPort
    private Integer port;

    private static WebDriver driver;

    private static LoginPage loginPage;
    private static SignupPage signupPage;
    private static HomePage homePage;
    private static CredentialsTab credentialsTab;

    private static boolean loggedIn = false;

    @Autowired
    private EncryptionService encryptionService;

    @BeforeAll
    static void beforeAll(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        loginPage = new LoginPage(driver);
        signupPage = new SignupPage(driver);
        homePage = new HomePage(driver);

        credentialsTab = new CredentialsTab(driver);
    }

    @AfterAll
    static void afterAll(){
        driver.close();
    }

    @BeforeEach
    void beforeEach(){
        driver.get("http://localhost:" + port + "/");
    }


    @Test
    void testAddCredential(){

        login();

        homePage.openCredentialsTab();

        String url = "add url";
        String username  ="add username";
        String decryptedPassword = "add decrypted password";

        credentialsTab.addCredential(url, username, decryptedPassword);

        WebElement credentialRow = credentialsTab.getCredentialRow(url, username);
        assertNotNull(credentialRow);

        assertNotEquals(decryptedPassword, credentialsTab.getCredentialPassword(credentialRow));

    }

    @Test
    void testViewablePassowrdIsUnencrypted(){

        login();
        homePage.openCredentialsTab();

        String url = "edit url";
        String username  ="edit username";
        String decryptedPassword = "edit decrypted password";

        credentialsTab.addCredential(url, username, decryptedPassword);

        String editablePassword = credentialsTab.openEditCredentialDialog(url, username);

        assertEquals(decryptedPassword, editablePassword);

    }

    @Test
    void testEditCredential(){

        login();
        homePage.openCredentialsTab();

        String oldUrl = "edit url";
        String oldUsername  ="edit username";
        String oldDecryptedPassword = "edit decrypted password";

        String newUrl = "new edit url";
        String newUsername  ="new edit username";
        String newDecryptedPassword = "new edit decrypted password";


        credentialsTab.addCredential(oldUrl, oldUsername, oldDecryptedPassword);

        credentialsTab.editCredential(oldUrl, oldUsername, newUrl, newUsername, newDecryptedPassword);

        WebElement credentialRow = credentialsTab.getCredentialRow(newUrl, newUsername);
        assertNotNull(credentialRow);


        String editablePassword = credentialsTab.openEditCredentialDialog(newUrl, newUsername);

        assertEquals(newDecryptedPassword, editablePassword);
    }

    @Test
    void testDeleteCredential(){

        login();
        homePage.openCredentialsTab();

        String url = "delete url";
        String username  ="delete username";
        String decryptedPassword = "delete decrypted password";

        credentialsTab.addCredential(url, username, decryptedPassword);

        credentialsTab.deleteCredential(url, username);

        assertNull(credentialsTab.getCredentialRow(url, username));
    }

    private static void login(){

        if(loggedIn) return;

        String username = "user";
        String password = "pass";

        String firstName = "first";
        String lastName = "last";

        loginPage.goToSignup();

        signupPage.signup(firstName,lastName, username,password);

        loginPage.login(username, password);

        loggedIn = true;

    }

}
