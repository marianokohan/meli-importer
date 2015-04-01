package meliimporter

class MeLiCategory {

    static constraints = {
    }
	
	String id;
	String name;
	long total_items_in_this_category;
	List children_categories = [].withLazyDefault { return new MeLiCategory() };

	
	public setId(String id) {
		this.id = id;
	}
	
	public boolean isLeaf() {
		return (children_categories.size() == 0);
	}
		
}
