package meliimporter

import grails.converters.JSON

import org.apache.commons.logging.LogFactory

import com.mercadolibre.sdk.Meli
import com.ning.http.client.FluentStringsMap
import com.ning.http.client.Response

class AuthenticateController {

	private static final log = LogFactory.getLog(this)
	
	def timeApi
	
	def Meli m;
	
	def beforeInterceptor = {
		if (m == null)
			m = new Meli(6843475917972468, "gB9MQrFecL1rKvlQhHxJvnshUJ20PRgW")
	}

	def index() { 
//		def currentTime = timeApi.getCurrentTime()
		def currentTime = '-'
		['currentTime' : currentTime, 'authorizationCodeUrl': m.getAuthUrl("http://localhost:8080/meliImporter/authenticate/login")]
	}
	
	def login() {
		if (!params.error) {
			m.authorize(params.code, "http://localhost:8080/meliImporter/authenticate/login");
			//TODO: exception "To create an access token the user 178811085 must have an active session, or your application should request authorization for offline_access scope.. Stacktrace follows:"
			// currently app configured as 'offline_access'
			FluentStringsMap params = new FluentStringsMap();
			params.add("access_token", m.getAccessToken());
			Response response = m.get("/users/me", params);
			log.info(response.getResponseBody())
			return JSON.parse(response.getResponseBody())
		}
		else {
			[ userInfo: "error: " + params.error + " - " + params.error_description]
		}
	}

}
