package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement inputUsernameField;

    @FindBy(id = "inputPassword")
    private WebElement inputPasswordField;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "error-msg")
    private WebElement errorMsg;

    @FindBy(id = "logout-msg")
    private WebElement logoutMsg;

    @FindBy(id = "signup-link")
    private WebElement signupLink;

    @FindBy(id = "signup-success")
    private WebElement signupSuccess;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        inputUsernameField.clear();
        inputPasswordField.clear();

        inputUsernameField.sendKeys(username);
        inputPasswordField.sendKeys(password);

        submitButton.click();
    }

    public void goToSignup() {
        signupLink.click();
    }

    public String getSignupMessage(){
        return signupSuccess.getText();
    }

}
