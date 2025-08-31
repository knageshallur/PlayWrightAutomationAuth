package com.skillmineauth.projects.auth.admin.pages;

import com.java.auth.helpers.JsonHelpers;
import com.java.auth.helpers.RandomNumberHelpers;
import com.java.auth.utils.PlaywrightUtils;
import com.microsoft.playwright.Page;


import java.io.IOException;

import com.java.auth.constants.FrameworkConstants;

public class ClientsSettingsPage extends PlaywrightUtils {
    protected final String clientSettings = "//span[.='Client Settings']";
    protected final String createClient = "//button[.=' Create Client ']";
    protected final String nameField = "//input[@id='client_name']";
    protected final String logoURlField = "//input[@id='logo_uri']";
    protected final String typeField = "//angular2-multiselect[@name='client_type']//div[@class='c-btn']";
    protected final String selectClientSide = "//angular2-multiselect[@name='client_type']//li[2]";
    protected final String redirectURLTextBox = "//textarea[@id='redirecturl']";
    protected final String allowedLogoutURLTextBox = "//textarea[@id='logouturl']";
    protected final String saveButton = "//button[.=' Save ']";
    protected final String searchText = "//div[@class='form-group col-12 col-lg-12 d-flex justify-content-between mb-1']/input[@type='text']";
    protected final String editButton = "//button[@class='btn btn-primary btn-sm ml-auto actionButton']";
    protected final String deleteButton = "//button[@data-toggle='modal']";
    protected final String confirmDeleteButton = "//div[@class='modal-footer']/button[2]";
    protected final String textClientNotFound = "//strong[normalize-space()='Client list not found!']";
    protected final String labelSettings = "//span[.='Settings']";
    protected final String labelOpenIdSettings = "//span[.='Open Id Connect Settings']";
    private JsonHelpers jsonReader = new JsonHelpers();


    public ClientsSettingsPage(Page page) {
        super(page);
    }


    public boolean isNewClientCreated() throws IOException {
        //click(labelSettings);
        //click(labelOpenIdSettings);
        click(clientSettings);
        click(createClient);
        fill(nameField,FrameworkConstants.CLIENT_NAME);
        fill(logoURlField,FrameworkConstants.LOGO_URL);
        clickOnDropDownAndSelectText(typeField,selectClientSide);
        fill(redirectURLTextBox,"*");
        fill(allowedLogoutURLTextBox,"*");
        click(saveButton);
        typeForSearch(searchText,FrameworkConstants.CLIENT_NAME);
        pressKeyboardKey("Enter");
        return customTextVisible(FrameworkConstants.CLIENT_NAME);
    }
    public void isNewClientDeleted(){
        //click(labelSettings);
        //click(labelOpenIdSettings);
        // click(clientSettings);
        //typeForSearch(searchText,"testing321");
        //pressKeyboardKey("Enter");
        click(editButton);
        click(deleteButton);
        click(confirmDeleteButton);
    }
}
