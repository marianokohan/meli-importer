package meliimporter

class AxisProduct {

    static constraints = {
    }
	
//	int id 
	//TODO set id as "id"
	int prestaShopId
	//language "1"
	String name 
	//language "1"
	String description
	double price
	//associations.images.image.xlink:href
	List<String> images = new ArrayList<String>();
	//stock_availables.stock_available.id - TODO
	int stock
	//associations.categories.category - TODO ?
	
	/* TODO: available on PrestaShop
	 * warranty
	 * condition
	 */
	
	
}
