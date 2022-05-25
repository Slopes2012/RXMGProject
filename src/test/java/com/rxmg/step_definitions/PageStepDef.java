package com.rxmg.step_definitions;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.rxmg.pages.Pages;
import com.rxmg.utils.BrowserUtils;
import com.rxmg.utils.ConfigurationReader;
import com.rxmg.utils.Driver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class PageStepDef {

    //Create object of the signupPage to access elements and locator and initialize the class.
    Pages pages = new Pages();
    //Declare a Faker object to be used by the entire class
    Faker faker = new Faker();
    //Step needed to produce non-hash faker values
    Name name = faker.name();
    //Variable to store userName used for validations
    String userName = name.firstName();
    //Declared actual user name variable to use for assertions
    String actualUserName;

    @Given("the User navigates to https:\\/\\/rx-devtest.com\\/ and selects signup")
    public void theUserNavigatesToHttpsRxDevtestComAndSelectsSignup() throws Exception {
        //Navigate to Conduit main/login page
        Pages.navigateHome();
        //Click on Signup button on the top corner
        BrowserUtils.clickByElement(pages.signUpButtonLoginPage);
        //Assert user is actually in the signup page
        Assert.assertEquals("https://rx-devtest.com/#/register", Driver.getDriver().getCurrentUrl());
    }

    @And("the user inputs a user name an email address and a password")
    public void theUserInputsAUserNameAnEmailAddressAndAPassword()throws Exception {
        String email = name.firstName();
        //Using the sign up object to reach the locators and using the sendKeys method from selenium to simulate "typing"
        pages.userNameInputBox.sendKeys(userName);
        pages.emailInputBox.sendKeys(email+ "@gmail.com");
        pages.passwordInputBox.sendKeys("password123");
    }

    @When("the user clicks on the signup button")
    public void the_user_clicks_on_the_signup_button() throws Exception {
        //Use the framework's click method to click the green signup button
        BrowserUtils.clickByElement(pages.signUpButton);
    }
    @Then("user should see their user name in the top right corner")
    public void userShouldSeeTheirUserNameInTheTopRightCorner() {
        //Get the text property of the topCornerUserName element and store into actualUserName String variable
        actualUserName = pages.topCornerUserName.getText();
        //Validating actual name on the browser with the previously used to sign up user name
        Assert.assertTrue(actualUserName.contains(userName));

    }

    @And("the user inputs an existing user name an existing email address and a password")
    public void theUserInputsAnExistingUserNameAnExistingEmailAddressAndAPassword(DataTable signUpdata) {
        //Create a list to be able to access the cucumber table data from the feature file
        List <String> existingUserData = signUpdata.asList();
        //Typing text in input boxes to signUp
        pages.userNameInputBox.sendKeys(existingUserData.get(0));
        pages.emailInputBox.sendKeys(existingUserData.get(1));
        pages.passwordInputBox.sendKeys(existingUserData.get(2));
    }

    @Then("user should see error User Name is Exist")
    public void userShouldSeeErrorUserNameIsExist() throws Exception {
        //Validating error message is present and error message text
        Assert.assertTrue(pages.existingUserErrorMessage.isDisplayed());
        //Added a condition to be able to reuse this method since locator for error message is the same.
        //Condition also handles if no error message is found. Error message is the expected behavior for the validation
        if (pages.existingUserErrorMessage.getText().equals("username is exist")){
            Assert.assertEquals("username is exist", pages.existingUserErrorMessage.getText());
        }else if (pages.existingUserErrorMessage.getText().equals("email is not found")){
            Assert.assertEquals("email is not found", pages.existingUserErrorMessage.getText());
        }else{
            throw new Exception("Error message not present - No error was displayed for the negative test");
        }
    }

    @Given("the User navigates to https:\\/\\/rx-devtest.com\\/ and selects signin")
    public void theUserNavigatesToHttpsRxDevtestComAndSelectsSignin() throws Exception {
        //Navigate to Conduit main/login page
        Pages.navigateHome();
        //Click on login/sign in button on the top corner
        BrowserUtils.clickByElement(pages.logInButton);
        //Assert user is actually in the signup page
        Assert.assertEquals("https://rx-devtest.com/#/login", Driver.getDriver().getCurrentUrl());

    }


    @And("the user inputs an existing an existing email address and a password")
    public void theUserInputsAnExistingAnExistingEmailAddressAndAPassword() {
        //Using the configuration.properties file to read account information.
        pages.emailInputBox.sendKeys(ConfigurationReader.getProperty("testAccount"));
        pages.passwordInputBox.sendKeys(ConfigurationReader.getProperty("password"));
    }

    @When("the user clicks on the Sign in button")
    public void theUserClicksOnTheSignInButton() throws Exception {
        //clicking signup button since both signin and signup have the same locators
        BrowserUtils.clickByElement(pages.signUpButton);
    }
    @Then("user should see their user name in the top right corner and should be redirected")
    public void userShouldSeeTheirUserNameInTheTopRightCornerAndShouldBeRedirected() {
        //Validate userName matches and asserts url
        actualUserName = pages.topCornerUserName.getText();
        Assert.assertTrue(actualUserName.contains(ConfigurationReader.getProperty("existingUserName")));
        Assert.assertNotEquals("https://rx-devtest.com/#/login", Driver.getDriver().getCurrentUrl());
    }

    @And("the user inputs a non existing email address and a password")
    public void theUserInputsANonExistingEmailAddressAndAPassword() {
        //Using the configuration.properties file to read account information.
        pages.emailInputBox.sendKeys("NonExistingUser@fakeEmail.com");
        pages.passwordInputBox.sendKeys(ConfigurationReader.getProperty("password"));
    }

    @Given("the user has logged in")
    public void theUserHasLoggedIn() throws Exception {
        pages.login();
    }


    @When("the user clicks on New Post")
    public void theUserClicksOnNewPost() throws Exception {
        //Clicking "New Post" and validating url
        BrowserUtils.clickByElement(pages.newPost);
        Assert.assertEquals("https://rx-devtest.com/#/editor", Driver.getDriver().getCurrentUrl());
    }

    @And("the user fills out and publish the Post")
    public void theUserFillsOutAndPublishThePost() throws Exception {
        //Write article using helper method
        pages.writeArticle("Hello","posting cool things","#Magic, #QA, #Automation, #Rock&Roll");
    }


    @Then("the user should see the title of the article and the option to edit or delete the newly created article")
    public void theUserShouldSeeTheTitleOfTheArticleAndTheOptionToEditOrDeleteTheNewlyCreatedArticle() {
        //Validating the page after article is created
        Assert.assertTrue(pages.postTitleAfterPublished.isDisplayed());
        Assert.assertTrue(pages.editArticle.isDisplayed());
        Assert.assertTrue(pages.deleteArticle.isDisplayed());
    }

    @When("the user clicks Home")
    public void theUserClicksHome() throws Exception {
        BrowserUtils.clickByElement(pages.home);
    }

    @Then("the user should see the post in their Feed")
    public void theUserShouldSeeThePostInTheirFeed() {
        //We gather a list of webelements that represent the list of post. if value is greater than 1 then there will be more than 1 post in the "Your feed"
        Assert.assertTrue(pages.listOfPosts.size() > 1);
    }

    @Then("the user should see the post in the Global Feed")
    public void theUserShouldSeeThePostInTheGlobalFeed() throws Exception {
        //We gather a list of webelements that represent the list of post. if value is greater than 1 then there will be more than 1 post in the "Global feed"
        BrowserUtils.clickByElement(pages.globalFeed);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),20);
        wait.until(ExpectedConditions.visibilityOf(pages.likeButton));
        Assert.assertTrue(pages.listOfPosts.size() > 1);
    }

    @Then("the user should be able to delete the post")
    public void theUserShouldBeAbleToDeleteThePost() throws Exception {
        //Validates the delete functionality
        BrowserUtils.clickByElement(pages.deleteArticle);
        pages.validateDeletedPostIsNotDisplayed();
    }

    @Then("the user should be able to delete the post and should not be displayed in the Feed")
    public void theUserShouldBeAbleToDeleteThePostAndShouldNotBeDisplayedInTheFeed() throws Exception {
        //Validates the post is no longer displayed in the "Global Feed" after deleting.
        BrowserUtils.clickByElement(pages.deleteArticle);
        pages.validateDeletedPostIsNotDisplayed();
    }
}


