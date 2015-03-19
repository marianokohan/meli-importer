package meliimporter

import meliImporter.MeLiService
import meliImporter.MeLiUser

class PublicationsController {
	
	def timeApi
	
	MeLiService meLiService;

	def index() { 
//		def currentTime = timeApi.getCurrentTime()
		def currentTime = '-'
		['currentTime' : currentTime, 'authorizationCodeUrl': meLiService.getAuthorizationUrl()]
	}
	
	def login() {
		//TODO: error handling in OAuth process
		if (!params.error) {
			session.meliUser = meLiService.authorize(params.code) //TODO: user as parameter and/or command
			redirect(action: 'index')
		}
		else {
			[ userInfo: "error: " + params.error + " - " + params.error_description]
		}
	}


}
