package unbxdTests.testNg.ui.pim;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class ResetOrg_Tests {

	public static String orgID = null;
	public static String baseUrl = null;

	public void getTheOrgDetails() {
		String url = System.getProperty("baseUrl");
		if (url.equalsIgnoreCase("http://pimqa.unbxd.com/#/login")) {
			orgID = "2aebef4f50692803423a2d18691ef390";
			baseUrl = "http://pimqa.unbxd.com/";
		} else if (url.equalsIgnoreCase("https://pimdev.unbxd.io/#/login")) {
			orgID = "6dbc50bcdd194e48025a08ee8906666f";
			baseUrl = "https://pimdev.unbxd.io/";
		} else {
			orgID = "f2b5dbbf6c09935e1f141d76218515b2";
			baseUrl = "https://pim.unbxd.com/";
		}
	}

	@Test
	public void resetOrganization() {

		getTheOrgDetails();
		Map<String, String> org = new HashMap<>();
		org.put("org_id", orgID);

		RestAssured.given().contentType("application/json").body(org).when()
				.put(baseUrl + "api/v1/" + orgID + "/configs/data/reset").then().assertThat().statusCode(200);
	}

}