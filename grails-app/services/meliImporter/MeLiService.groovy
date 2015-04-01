/**
 * 
 */
package meliImporter

import grails.converters.JSON
import grails.web.JSONBuilder
import meliimporter.MeLiCategory
import meliimporter.MeLiItem
import meliimporter.MeLiListingType

import org.apache.commons.logging.LogFactory

import com.mercadolibre.sdk.Meli
import com.ning.http.client.FluentStringsMap
import com.ning.http.client.Response

/**
 * @author mkohan
 *
 */
class MeLiService {

	static scope = "session" //to maintain access_token on MeLi client sdk - TODO: alternative?
	
	private static final log = LogFactory.getLog(this)

	private static final String ARG_SITE_ID = "MLA";
	
	private String redirectUri = "http://localhost:8080/meliImporter/publications/login";
	def Meli m = new Meli(6843475917972468, "gB9MQrFecL1rKvlQhHxJvnshUJ20PRgW")
	
	
	public String getAuthorizationUrl() {
		return m.getAuthUrl(redirectUri)
	}
	
	public MeLiUser authorize(String code) {
		m.authorize(code, redirectUri);
		//TODO: exception "To create an access token the user 178811085 must have an active session, or your application should request authorization for offline_access scope.. Stacktrace follows:"
		// currently app configured as 'offline_access'
		FluentStringsMap params = new FluentStringsMap();
		params.add("access_token", m.getAccessToken());
		Response response = m.get("/users/me", params);
		log.info("auth response: " + response.getResponseBody())
		return new MeLiUser(JSON.parse(response.getResponseBody()))
	}

	public MeLiUser createTestUser() {
		FluentStringsMap params = new FluentStringsMap();
		params.add("access_token", m.getAccessToken());
		def createTestUserJson = new JSONBuilder().build {
			site_id = ARG_SITE_ID
		}
		Response response = m.post("/users/test_user?access_token=${m.accessToken}", params, createTestUserJson.toString());
		log.info("createTestUser response: " + response.getResponseBody())
		return new MeLiUser(JSON.parse(response.getResponseBody()))
	}
	
	public MeLiItem getPublication(String id) {
		FluentStringsMap params = new FluentStringsMap();
		params.add("access_token", m.getAccessToken());
		Response response = m.get("/items/${id}", params);
//		log.info("response: " + response.getResponseBody())
		return new MeLiItem(JSON.parse(response.getResponseBody()))
	}

	public getUserPublications(MeLiUser user) {
		FluentStringsMap params = new FluentStringsMap();
		params.add("access_token", m.getAccessToken());
//		log.info("/users/${user.id}/items/search?status=active&access_token=${m.accessToken}")
		Response response = m.get("/users/${user.id}/items/search?status=active&access_token=${m.accessToken}", params);
		log.info("user publications response: " + response.getResponseBody()) 
		//TODO: the response of the api using java-sdk does not filter the 'active' items - this is not reproduced on the browser 
		// idea: review java-sdk -> handling of get params
		def items = JSON.parse(response.getResponseBody())["results"]
		List<MeLiItem> meLiItems = new LinkedList<MeLiItem>();
		items.each {
			//so the 'active' items are filtered here
			MeLiItem item = getPublication(it);
			if (item.isActive())
				meLiItems.add(item)
		}
		return meLiItems
	}
	
	public publishItem(MeLiItem item) {
		FluentStringsMap params = new FluentStringsMap();
		params.add("access_token", m.getAccessToken());
		JSON jsonItem = new JSON(item);
		Response response = m.post("/items?access_token=${m.accessToken}", params, jsonItem.toString());
		log.info("publishItem response: " + response.getResponseBody()) 
	}
		
	public MeLiCategory getCategory(String categoryId) {
		FluentStringsMap params = new FluentStringsMap();
		params.add("access_token", m.getAccessToken());
		Response response = m.get("/categories/${categoryId}", params);
		log.info("category response: " + response.getResponseBody())
		return new MeLiCategory(JSON.parse(response.getResponseBody()))
	}
	
	public List<MeLiCategory> getCategories(String siteId) {
		FluentStringsMap params = new FluentStringsMap();
		params.add("access_token", m.getAccessToken());
		Response response = m.get("/sites/${siteId}/categories", params);
		log.info("categories response: " + response.getResponseBody())
		def categoriesJSONArray = JSON.parse(response.getResponseBody());
		List<MeLiCategory> categories = new LinkedList<MeLiCategory>();
		categoriesJSONArray.each { category ->
			categories << new MeLiCategory(category);
		}
		return categories;
	}
	
	/**
	 * categories for Argentina
	 * @return
	 */
	public List<MeLiCategory> getCategories() {
		return getCategories(ARG_SITE_ID);
	}
	
	public List<MeLiListingType> getListingTypes(String siteId) {
		FluentStringsMap params = new FluentStringsMap();
		params.add("access_token", m.getAccessToken());
		Response response = m.get("/sites/${siteId}/listing_types", params);
		log.info("response categories: " + response.getResponseBody())
		def listingTypesJSONArray = JSON.parse(response.getResponseBody());
		List<MeLiListingType> listingTypes = new LinkedList<MeLiListingType>();
		listingTypesJSONArray.each { listingType ->
			listingTypes << new MeLiListingType(listingType);
		}
		return listingTypes;
	}
	
	/**
	 * listing Types for Argentina
	 * @return
	 */
	public List<MeLiListingType> getListingTypes() {
		return getListingTypes(ARG_SITE_ID);
	}
		
}
