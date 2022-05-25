package com.rxmg.pages;

import com.github.javafaker.Faker;
import com.rxmg.utils.BrowserUtils;
import com.rxmg.utils.ConfigurationReader;
import com.rxmg.utils.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class Pages {

    //Constructor to help initialize the elements of the class
    public Pages(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    String title;

    //Locators
    @FindBy (xpath = "//input[@placeholder='Username']")
    public WebElement userNameInputBox;
    @FindBy (xpath = "//input[@placeholder='Email']")
    public WebElement emailInputBox;
    @FindBy (xpath = "//input[@placeholder='Password']")
    public WebElement passwordInputBox;
    @FindBy (xpath = "//a[@href='#/register']")
    public WebElement signUpButtonLoginPage;
    @FindBy (xpath = "//button[@type='submit']")
    public WebElement signUpButton;
    @FindBy (xpath = "(//a[@class='nav-link'])[4]")
    public WebElement topCornerUserName;
    @FindBy (xpath = "//ul[@class='error-messages']")
    public WebElement existingUserErrorMessage;
    @FindBy (xpath = "//a[@href='#/login']")
    public WebElement logInButton;
    @FindBy (xpath = "//a[@href='#/editor']")
    public WebElement newPost;
    @FindBy (xpath = "//input[@placeholder='Article Title']")
    public WebElement postTitle;
    @FindBy (xpath = "//input[contains(@placeholder,'about')]")
    public WebElement postAbout;
    @FindBy (xpath = "//textarea[contains(@placeholder,'in markdown')]")
    public WebElement postMarkdown;
    @FindBy (xpath = "//input[contains(@placeholder,'tags')]")
    public WebElement postTags;
    @FindBy (xpath = "//button[text()='Publish Article']")
    public WebElement publishPost;
    @FindBy (xpath = "//h1")
    public WebElement postTitleAfterPublished;
    @FindBy (xpath = "//a[text()=' Edit Article']")
    public WebElement editArticle;
    @FindBy (xpath = "//button[text()=' Delete Article']")
    public WebElement deleteArticle;
    @FindBy (xpath = "//a[@href='#/']")
    public WebElement home;
    @FindBy (xpath = "//div[@class='article-preview']")
    public List<WebElement> listOfPosts;
    @FindBy (xpath = "//a[text()='Global Feed']")
    public WebElement globalFeed;
    @FindBy (xpath = "//i[@class='ion-heart']")
    public WebElement likeButton;
    /**
     * This method navigates to property url that is specified in the config.properties file
     */
    public static void navigateHome(){
        //Getting private driver using the getter method
       WebDriver driver = Driver.getDriver();
       //Getting the url from the configuration reader using getter
       String url = ConfigurationReader.getProperty("url");
       //Navigating to given url using the selenium methods.
        driver.navigate().to(url);
    }

    /**
     * Login method to minimize redundancy
     * @throws Exception
     */
    public void login () throws Exception {
        navigateHome();
        BrowserUtils.clickByElement(logInButton);
        emailInputBox.sendKeys(ConfigurationReader.getProperty("testAccount"));
        passwordInputBox.sendKeys(ConfigurationReader.getProperty("password"));
        BrowserUtils.clickByElement(signUpButton);
    }

    /**
     * Write article helper method. Receives strings but randomize the title to allow for validations on different test scenarios
     * @param about
     * @param markdown
     * @param tags
     * @throws Exception
     */
    public void writeArticle(String about, String markdown,String tags) throws Exception {
        Faker faker = new Faker();
        title = faker.name().title();
        postTitle.sendKeys(title);
        postAbout.sendKeys(about);
        postMarkdown.sendKeys(markdown);
        postTags.sendKeys(tags);
        BrowserUtils.clickByElement(publishPost);
    }

    /**
     * Method that validates that prior article's title is no longer on the feed.
     * @return
     * @throws Exception
     */
    public boolean validateDeletedPostIsNotDisplayed() throws Exception {
        BrowserUtils.clickByElement(globalFeed);
        if (listOfPosts.get(0).getText().equals(title)) {
            return false;
        }
        return true;
    }

}
