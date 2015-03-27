import grails.converters.JSON
import meliimporter.ItemPicture
import meliimporter.MeLiItem

class BootStrap {

    def init = { servletContext ->
		//json marshalling solution from http://manbuildswebsite.com/2010/02/15/rendering-json-in-grails-part-3-customise-your-json-with-object-marshallers/
		//TODO: alternative from http://compiledammit.com/2012/08/16/custom-json-marshalling-in-grails-done-right/
		//TODO (simple): (test to) apply this config. into the respective domain object 
		JSON.registerObjectMarshaller( MeLiItem ) { MeLiItem item ->
		    return [
		            title : item.title,
					category_id : item.category_id,
					price : item.price,
					currency_id : item.currency_id,
					available_quantity : item.available_quantity,
					buying_mode : item.buying_mode,
					listing_type_id : item.listing_type_id,
					condition : item.condition,
					description : item.description,
					warranty : item.warranty,
					pictures : item.pictures
		    ]
		}
		JSON.registerObjectMarshaller( ItemPicture ) { ItemPicture picture ->
			return [
				source : picture.source
			]
		}
    }
    def destroy = {
    }
}
