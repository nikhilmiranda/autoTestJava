package unbxdTests.testNg.ui.pim;
import io.restassured.RestAssured;
import org.apache.http.HttpResponse;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.selenium.webdriven.commands.GetCookie;
public class ResetOrg_Tests {
    public static String orgID = null;
    public static String baseUrl = null;
//    public String getTheOrgDetails() {
////       // String url = System.getProperty("baseUrl");
////       
////        if(url.equalsIgnoreCase("http://pimqa.unbxd.io/#/login")) {
////            orgID = "2aebef4f50692803423a2d18691ef390";
////            baseUrl = "pimqa.unbxd.io";
////        }
////        else if(url.equalsIgnoreCase("https://pimdev.unbxd.io/#/login")){
////            orgID = "orgID = \"f2b5dbbf6c09935e1f141d76218515b2\"";
////            baseUrl = "pimdev.unbxd.io";
////        }
////        else {
////            orgID = "f2b5dbbf6c09935e1f141d76218515b2";
////            baseUrl = "pim.unbxd.io";
////        }
//        return url
//    }
    @Test
    public void resetOrganization() {
//        getTheOrgDetails();
        orgID = "de41bab53a0a447b0ea50cabdc8eecb8";
        // Map<String, String> org = new HashMap<>();
        // org.put("org_id", orgID);
        // GetCookie.class.getField(_un_sso_uid);

        RestAssured.given()
                .contentType("application/json")
                .cookie("_un_sso_uid=eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjpudWxsLCJleHBpcnkiOiIyMDIwLTA3LTIxIDEyOjQzOjE2IFVUQyIsImVtYWlsIjoibmlraGlsLm1pcmFuZGErMTA2QHVuYnhkLmNvbSIsInJlZ2lvbnMiOnt9fQ.aMb7AofXmTVf2Y0M2LFe95SRiBOk3LWbB6W40Pt1YmM")
                .when().post("https://pimdev.unbxd.io/paprika/api/v1/orgs/"+orgID+"/reset")
                .then().assertThat().statusCode(200);
    }
}