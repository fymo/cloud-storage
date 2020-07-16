package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.page.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.page.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.page.NotesTab;
import com.udacity.jwdnd.course1.cloudstorage.page.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotesTests {


    @LocalServerPort
    private Integer port;

    private static WebDriver driver;

    private static LoginPage loginPage;
    private static SignupPage signupPage;
    private static HomePage homePage;
    private static NotesTab notesTab;

    private static boolean loggedIn = false;

    @BeforeAll
    static void beforeAll(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        loginPage = new LoginPage(driver);
        signupPage = new SignupPage(driver);
        homePage = new HomePage(driver);

        notesTab = new NotesTab(driver);

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
    void testAddNote(){

        login();

        homePage.openNotesTab();

        String title = "add title";
        String description  ="add description";
        notesTab.addNote(title, description);
        assertNotNull(notesTab.getNoteRow(title, description));
    }

    @Test
    void testEditNote(){

        login();
        homePage.openNotesTab();

        String title = "edit title";
        String description  ="edit description";

        String newTitle = "new edit title";
        String newDescription  ="new edit description";

        notesTab.addNote(title, description);

        notesTab.editNote(title, description, newTitle, newDescription);

        assertNotNull(notesTab.getNoteRow(newTitle, newDescription));
    }

    @Test
    void testDeleteNote(){

        login();
        homePage.openNotesTab();

        String title = "delete title";
        String description  ="delete description";

        notesTab.addNote(title, description);

        notesTab.deleteNote(title, description);

        assertNull(notesTab.getNoteRow(title, description));
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
