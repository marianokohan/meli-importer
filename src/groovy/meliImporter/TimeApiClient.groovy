/**
 * 
 */
package meliImporter

import groovyx.net.http.HTTPBuilder
import groovyx.net.http.HttpResponseException;

import org.apache.commons.logging.LogFactory

/**
 * @author mkohan
 *
 */
class TimeApiClient {
	

	def getCurrentTime() {
		try 
		{
		   def http = new HTTPBuilder('http://www.timeapi.org')
		   def html = http.get( path : '/utc/now',
			   headers: [USER_AGENT: 'Apache HTTPClient'] )
		   //solution from http://stackoverflow.com/questions/16919001/httpbuilder-gets-403-while-curl-works-fine-github-api
		   return html.BODY.text()
		}
		catch ( HttpResponseException) {
			return 'try later to get current time...'
		}
	}
	
}
