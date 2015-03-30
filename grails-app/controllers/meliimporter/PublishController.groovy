package meliimporter

import meliImporter.MeLiService;

class PublishController {

	MeLiService meLiService;
	AxisService axisService;
	
	def index(int productId) {
		if ( (session.item == null) ||
			((session.item != null) && (session.productId != productId)) ) //identify browser's back button + another product selected
																			// -> TODO: better? - additional domain object -> relation between product and item (the given product published on this item) 
		{
			//TODO: better way to obtain selected product
			// current object -> from page/controller, or handled by the service (avoid additional get from axis api)
			session.item = new MeLiItem(axisService.getProduct(productId));
			session.productId = productId
		}
	}
	
	def publish() {
		session.item.properties = params
		meLiService.publishItem(session.item)
		session.item = null;
		session.productId = null;
		redirect(controller: 'publications')
	}


}
