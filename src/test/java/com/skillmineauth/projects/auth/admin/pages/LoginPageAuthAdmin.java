package com.skillmineauth.projects.auth.admin.pages;
import com.java.auth.Factory.PlaywrightFactory;
import com.java.auth.helpers.JsonHelpers;
import com.java.auth.utils.PlaywrightUtils;
import com.microsoft.playwright.Page;
import org.testng.Assert;


import java.util.Map;

public class LoginPageAuthAdmin extends PlaywrightUtils {

    protected final String emailIDField = "//input[@id='email']";
    protected final String passwordField = "//input[@id='password']";
    protected final String loginButton = "//button[@type='submit']";
    protected final String profileIcon = "//a[@class='profile-user d-flex dropdown-toggle']//img";


    public LoginPageAuthAdmin(Page page) {
        super(page);
    }

    public boolean isLoginSuccessful() {
        String username= JsonHelpers.getValue("Login", "username");
        String password= JsonHelpers.getValue("Login", "password");
        fill(emailIDField, username);
        fill(passwordField, password);
        click(loginButton);
        return isTextVisible(profileIcon);
    }
}

