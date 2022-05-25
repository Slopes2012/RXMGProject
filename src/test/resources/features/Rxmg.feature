Feature: RXMG sample regression

  @positiveSignUp
  Scenario: New user successful signup

    Given the User navigates to https://rx-devtest.com/ and selects signup
    And the user inputs a user name an email address and a password
    When the user clicks on the signup button
    Then user should see their user name in the top right corner

  @negativeSignUpNew
  Scenario: Existing user should not be able to sign up

    Given the User navigates to https://rx-devtest.com/ and selects signup
    And the user inputs an existing user name an existing email address and a password
    |Sandra1|
    |sandra1@gmail.com|
    |password|
    When the user clicks on the signup button
    Then user should see error User Name is Exist

  @positiveLogin
  Scenario: Existing user should be able to login

    Given the User navigates to https://rx-devtest.com/ and selects signin
    And the user inputs an existing an existing email address and a password
    When the user clicks on the Sign in button
    Then user should see their user name in the top right corner and should be redirected

  @negativeLogin
  Scenario: Non existing user should not be able to login

    Given the User navigates to https://rx-devtest.com/ and selects signin
    And the user inputs a non existing email address and a password
    When the user clicks on the Sign in button
    Then user should see error User Name is Exist

  @post
  Scenario: Create a post
    Given the user has logged in
    When the user clicks on New Post
    And the user fills out and publish the Post
    Then the user should see the title of the article and the option to edit or delete the newly created article

  @postYourFeed
  Scenario: Validate Post displays correctly in Your Feed
    Given the user has logged in
    When the user clicks on New Post
    And the user fills out and publish the Post
    Then the user should see the title of the article and the option to edit or delete the newly created article
    When the user clicks Home
    Then the user should see the post in their Feed

  @postGlobalFeed
  Scenario: Validate Post displays correctly in Global Feed
    Given the user has logged in
    When the user clicks on New Post
    And the user fills out and publish the Post
    Then the user should see the title of the article and the option to edit or delete the newly created article
    When the user clicks Home
    Then the user should see the post in the Global Feed

  @postDelete
  Scenario: Validate a post can be deleted
    Given the user has logged in
    When the user clicks on New Post
    And the user fills out and publish the Post
    Then the user should see the title of the article and the option to edit or delete the newly created article
    Then the user should be able to delete the post

  @postDelete
  Scenario: Validate a post can be deleted and it is removed from the feed
    Given the user has logged in
    When the user clicks on New Post
    And the user fills out and publish the Post
    Then the user should see the title of the article and the option to edit or delete the newly created article
    Then the user should be able to delete the post and should not be displayed in the Feed
