package core.pages.pim.ManageNetwork.Partner;

import lib.FunctionLibrary;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class PartnerListing_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".qa-partner-list .inline-modal-header .secondary-btn")
    FluentWebElement createPartnerButton;

    @FindBy(how = How.NAME, using = "name")
    FluentWebElement partnerNameInputBox;

    @FindBy(how = How.NAME, using = "email")
    FluentWebElement partnerEmailInputBox;

    @FindBy(how = How.CSS, using = ".primary-btn")
    FluentWebElement invitePartnerButton;

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement searchPartnerInputBox;

    @FindBy(how = How.CSS, using = ".qa-partner-list .table tbody tr")
    FluentList<FluentWebElement> partnerDetailsList;

    @FindBy(how = How.CSS, using = ".qa-partner-list .table tbody tr td:nth-child(1)")
    FluentList<FluentWebElement> partnerNameList;

    @FindBy(how = How.CSS, using = ".qa-partner-list .table tbody tr td:nth-child(4) .glyphicon-eye-open")
    FluentList<FluentWebElement> partnerEditIcon;

    @FindBy(how = How.CSS, using = ".no-data h2")
    FluentWebElement noResultFound;

    @FindBy(how = How.CSS, using = ".first-time-import")
    FluentWebElement blankPartnerPage;



    String createPartnerButtonName = "Create Partner Button";
    String partnerNameInputBoxName = "Partner Name InputBox";
    String partnerEmailInputBoxName = "Partner Email Input Box";
    String invitePartnerButtonName = "Invite Partner Button Name";
    String searchPartnerInputBoxName = "Search partner InputBox";
    String partnerNameListName = "Partner Name List";
    String partnerDetailsListName = "Partner Details List";
    String partnerEditIconName = "partner Edit Icon";
    String noResultFoundName = "No Result Found";
    String blankPartnerPageName = "Blank Partner Page";



    public void createPartner(String partnerName, String partnerEmail) {
        FunctionLibrary.click(createPartnerButton, createPartnerButtonName);
        FunctionLibrary.input(partnerNameInputBox, partnerNameInputBoxName, partnerName);
        FunctionLibrary.input(partnerEmailInputBox, partnerEmailInputBoxName, partnerEmail);
        FunctionLibrary.click(invitePartnerButton, invitePartnerButtonName);
    }

    public void searchPartnerName(String partnerName){
        FunctionLibrary.input(searchPartnerInputBox, searchPartnerInputBoxName, partnerName);

    }

    public List<String> getPartnerNames(){
        return FunctionLibrary.retrieveTexts(partnerNameList, partnerNameListName);
    }

    public List<String> getPartnerDetails(){
        return FunctionLibrary.retrieveTexts(partnerDetailsList, partnerDetailsListName);
    }


    public void clickOnEditPartner() {
        FunctionLibrary.click(partnerEditIcon.get(0), partnerEditIconName);
    }

    public String getPageContentForPartnerNotFound() {
        return FunctionLibrary.retrieveText(noResultFound, noResultFoundName);
    }

    public String getPageContentOfBlankPartnerListingPage() {
        return FunctionLibrary.retrieveText(blankPartnerPage, blankPartnerPageName);
    }

    public void waitForPageToLoad() {
        FunctionLibrary.waitForElementToBeClickable(createPartnerButton, createPartnerButtonName);
    }
}
