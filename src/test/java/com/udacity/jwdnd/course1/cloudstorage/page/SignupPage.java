package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstNameField;

    @FindBy(id = "inputLastName")
    private WebElement inputLastNameField;

    @FindBy(id = "inputUsername")
    private WebElement inputUsernameField;

    @FindBy(id = "inputPassword")
    private WebElement inputPasswordField;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "error-msg")
    private WebElement errorMsg;

    @FindBy(id = "login-link")
    private WebElement loginLink;

    public SignupPage(WebDriver driver){
           PageFactory.initElements(driver, this);
    }

    public void signup(String firstName, String lastName, String username, String password) {

        inputFirstNameField.clear();
        inputLastNameField.clear();
        inputUsernameField.clear();
        inputPasswordField.clear();

        inputFirstNameField.sendKeys(firstName);
        inputLastNameField.sendKeys(lastName);
        inputUsernameField.sendKeys(username);
        inputPasswordField.sendKeys(password);

        submitButton.click();

    }

    public void goToLogin(){
        loginLink.click();
    }

}
