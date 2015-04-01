package meliimporter

import groovy.xml.MarkupBuilder
import meliImporter.MeLiService

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
			session.productId = productId;
		}
		
	}
	
	def categoryChanged(String categoryId, int level) {
		MeLiCategory category = meLiService.getCategory(categoryId)
		if (category.isLeaf()) {
			//TODO: alternative enambling the button (instead of rendering)
			render g.submitButton(name: 'publish', value: 'Publish');	
		}
		else {
			def writer = new StringWriter()
			def builder = new MarkupBuilder(writer)
			builder.div(id: "container_Category_${level + 1}");
			def html = writer.toString()
			//TODO: simpler way to render 2 tags?
			render g.select(id:"category_${level}", name: "category_id.${level}",
					from: category.children_categories, optionKey:'id', optionValue: 'name',
					onchange: "categoryChanged(this.value, ${level + 1});") + html
		}
	}
	
	def publish() {
		session.item.properties = params;
		session.item.category_id = params.category_id["${params.category_id.size()}"]
		meLiService.publishItem(session.item);
		session.item = null;
		session.productId = null;
		redirect(controller: 'publications');
	}


}
