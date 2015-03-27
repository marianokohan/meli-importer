package meliimporter

import grails.converters.XML
import groovyx.net.http.HTTPBuilder

import org.apache.commons.logging.LogFactory
import org.apache.http.client.HttpResponseException

/**
 * Service to access to http://www.axis.com.ar api
 * based on PrestaShop: https://www.prestashop.com/ - http://doc.prestashop.com/display/PS16/Web+service+reference
 * @author mkohan
 *
 */
class AxisService {

	private static final log = LogFactory.getLog(this)

	private String siteUrl = "http://www.axis.com.ar/"
	private String token = "8D1G53A3XCX7TKSMGQPS34ATSPMEWET5"
	
	
	def http = new HTTPBuilder(siteUrl)

	public List getProducts() {
		List products = new LinkedList<AxisProduct>()
		try
		{
		   http.auth.basic token, ''
//		   NodeChild html = http.get( path : '/api/products',
//		   def html = http.get( path : '/api/products',
//			   headers: [USER_AGENT: 'Apache HTTPClient'] )
//		   log.info("axis response: " + html.getClass())
//		   String htmlString = new groovy.xml.StreamingMarkupBuilder().bindNode(html) as String
		   http.get( path : '/api/products',
			   headers: [USER_AGENT: 'Apache HTTPClient'] ) { resp, xml ->
//			   log.info(resp.status);
//			   log.info("${xml.products.product[0].@id}") 
//			   log.info("${xml.products.product[0].@'xlink:href'}")
			   xml.products.product.each { product ->
//				   def productUrl = ${product.@'xlink:href'}
				   def productId = Integer.parseInt("${product.@id}")
				   if (productId < 10) { //TODO: products filtering
					   products.add(getProduct(productId))
				   }
			   }
		   }
		}
		catch ( HttpResponseException exception) {
			return log.info("list products exception: " + exception)
		}
		return products;
    }
	
	public AxisProduct getProduct(int productId) {
	   AxisProduct product
	   try
	   {
		   http.auth.basic token, ''
		   http.get( path : "/api/products/${productId}",
			   headers: [USER_AGENT: 'Apache HTTPClient'] ) { resp, xml ->
//   			   log.info(resp.status);
//   			   log.info("${xml.product.id.text()}")
//			     product = new AxisProduct(XML.parse(xml.product))
		   		 //TODO: better way than this solution (http://stackoverflow.com/questions/10068661/converting-xml-to-domain-object-in-grails)
		   		 //			similar to json
		   		 //TODO set id as "id"
			     product = new AxisProduct(prestaShopId: "${xml.product.id.text()}", 
					 name: "${xml.product.name.language.text()}",
					 description: "${xml.product.description.text()}",
					 price: "${xml.product.price.text()}")
				 //TODO: images, stock
				 /*
				  * http://www.axis.com.ar/api/images/products/13/4,
				  * http://www.axis.com.ar/api/stock_availables/265
				  * "Internal error. To see this error please display the PHP errors."
				  */
		       }
	   }
	   catch ( HttpResponseException exception) {
		   return log.info("get product '${productId}' exception: " + exception)
	   }
	   return product
	}
	
}
