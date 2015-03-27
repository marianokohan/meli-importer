package meliimporter

import meliImporter.MeLiService

class PublicationsController {
	
	def timeApi
	
	MeLiService meLiService;
	AxisService axisService;

	def index() { 
//		def currentTime = timeApi.getCurrentTime()
		def currentTime = '-'
		
		List products = axisService.getProducts();

		if(session.meliUser != null) {
			return [publications: meLiService.getUserPublications(session.meliUser),
				'products': products]
		}
		
		
		['currentTime' : currentTime, 'authorizationCodeUrl': meLiService.getAuthorizationUrl(), 
			'products': products]
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

	def testUser() {
		session.testUser = meLiService.createTestUser()
		['authorizationCodeUrl': meLiService.getAuthorizationUrl()]
	}
	
	def publishTest() {
		//TODO: to remove
	}
	
	def publish(int productId) {
		//TODO: better way to obtain selected product
		// current object -> from page/controller, or handled by the service (avoid additional get from axis api)
		['product': axisService.getProduct(productId)]
	}
	
	def publishToMeLi(int productId) {
		//TODO: same details about current product
		MeLiItem item = new MeLiItem(axisService.getProduct(productId));
		item.properties = params
		meLiService.publishItem(item)
		redirect(action: 'index')
	}

}
