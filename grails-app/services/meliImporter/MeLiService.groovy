/**
 * 
 */
package meliImporter

import grails.converters.JSON

import org.apache.commons.logging.LogFactory

import com.mercadolibre.sdk.Meli
import com.ning.http.client.FluentStringsMap
import com.ning.http.client.Response

/**
 * @author mkohan
 *
 */
class MeLiService {

	private static final log = LogFactory.getLog(this)
	
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
		log.info("access_token: " + m.getAccessToken())
		params.add("access_token", m.getAccessToken());
		Response response = m.get("/users/me", params);
		log.info(response.getResponseBody())
		return new MeLiUser(JSON.parse(response.getResponseBody()))
	}

}
