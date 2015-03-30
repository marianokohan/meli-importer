package meliimporter

class MeLiItem {

	static String ACTIVE_STATUS = "active"
	
    static constraints = {
    }
	
	//used to list the item
	String id
	String permalink
	String title
	String subtitle
	int available_quantity
	double price
	String thumbnail
	
	//used to publish de item
	//default values (initial)
	String category_id
	String currency_id
	String buying_mode
	String listing_type_id
	String condition
	//
	String warranty
	//different from list
	String description
	List pictures = [].withLazyDefault { return new ItemPicture() }
	//from http://www.mscharhag.com/2013/09/grails-data-binding-with-lists.html
	
	String status
	
	public MeLiItem(AxisProduct product) {
		//TODO: "test" items only on specific situation
		title = "[_TESTING_] " + product.name;
		description = product.description;
		price = product.price;
		product.images.each { image ->
			pictures.add(new ItemPicture(source: image));
		}
		warranty = "-" //TODO: something better?
	}
	
	public boolean isActive() {
		return ACTIVE_STATUS.equals(status);
	}
			
}
