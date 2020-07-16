package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NotesTab {

    @FindBy(id = "add-new-note-btn")
    private WebElement addNewNoteBtn;

    @FindBy(className = "note-row")
    private List<WebElement> noteElements;

    @FindBy(id = "note-title")
    private WebElement noteTitleField;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionField;

    @FindBy(id = "note-save")
    private WebElement noteSaveBtn;

    private WebDriver driver;

    public NotesTab(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getNoteRow(String noteTitle, String noteDescription){

        for(WebElement noteElement : noteElements){
            WebElement titleElement = noteElement.findElement(By.className("note-title"));
            WebElement descriptionElement = noteElement.findElement(By.className("note-description"));

            if(titleElement.getText().equals(noteTitle) && descriptionElement.getText().equals(noteDescription)) return noteElement;
        }

        return null;
    }

    public void addNote(String noteTitle, String noteDescription){

        new WebDriverWait(driver, 1).until(ExpectedConditions.elementToBeClickable(addNewNoteBtn)).click();

        new WebDriverWait(driver, 1).until(ExpectedConditions.elementToBeClickable(noteSaveBtn));

        noteTitleField.sendKeys(noteTitle);
        noteDescriptionField.sendKeys(noteDescription);
        noteSaveBtn.click();
    }

    public boolean editNote(String oldTitle, String oldDescription, String newTitle, String newDescription){

        WebElement noteRow = getNoteRow(oldTitle, oldDescription);

        if(noteRow == null)
            return false;


        noteRow.findElement(By.className("note-edit")).click();

        new WebDriverWait(driver, 500).until(ExpectedConditions.elementToBeClickable(noteSaveBtn));

        noteTitleField.clear();
        noteDescriptionField.clear();

        noteTitleField.sendKeys(newTitle);
        noteDescriptionField.sendKeys(newDescription);
        noteSaveBtn.click();

        return true;
    }

    public boolean deleteNote(String title, String description){
        WebElement noteRow = getNoteRow(title,description);
        if(noteRow == null) return false;

        noteRow.findElement(By.className("note-delete")).click();

        try{
            WebDriverWait wait = new WebDriverWait(driver, 1);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            alert.accept();
        }catch(Throwable e){
            System.err.println("Error came while waiting for the alert popup. "+e.getMessage());
            return false;
        }

        return true;
    }
}
