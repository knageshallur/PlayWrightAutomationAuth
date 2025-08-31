package com.skillmineauth.projects.auth.admin.pages;

import com.java.auth.helpers.JsonHelpers;
import com.java.auth.utils.PlaywrightUtils;
import com.microsoft.playwright.Page;

public class PasswordPolicyPage extends PlaywrightUtils {
    protected final String menuPasswordPolicy = "//span[contains(text(),'Password Policy')]";
    protected final String menuSettings = "//span[text()='Settings']";
    protected final String sliderMinCharCount = "(//input[@type='range'])[1]";
    protected final String sliderDigitCount = "(//input[@type='range'])[3]";
    protected final String sliderSpecialCharCount = "(//input[@type='range'])[4 ]";
    protected final String sliderMaxCharCount = "(//input[@type='range'])[2]";
    protected final String toggleUpperCaseLowercase="(//label[@class='switch'])[1]";
    protected final String buttonSave = "//button[contains(text(),'Save')]";
    protected final String slider="input[formcontrolname='minimum_length']";

    public PasswordPolicyPage(Page page) {
        super(page);
    }
    public String createNewPasswordPolicy(){
        click(menuSettings);
        click(menuPasswordPolicy);
        setSliderValue(slider,8);
//        setSliderValue(sliderMaxCharCount,20);
//        setSliderValue(sliderDigitCount,6);
//        setSliderValue(sliderSpecialCharCount,5);
        click(buttonSave);
        return JsonHelpers.getValue("passwordpolicy","successMessage");
    }
}
