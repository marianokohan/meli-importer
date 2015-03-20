/**
 * 
 */
package meliImporter

import java.text.SimpleDateFormat

/**
 * @author mkohan
 *
 */
class MeLiUser {

	Long id;
	String nickname;
	String first_name; //TODO: configuration of different name for databinding (bean notation)
	String last_name;  //same TODO
	String email;
//	Date registrationDate; 
//	String registration_date; 
	String registrationDate; //see TODO on setter
	
	//for test users
	String password
	String site_status
	
	public String getName() {
		return first_name + " " + last_name;
	}
	
	//TODO: required to bind to Long?
	//consider altenative solution (upgrade version?)
	//	static constraints = {
	//		return it.properties.findAll {k,v -> true}
	//	}
	public setId(Long id) {
		this.id = id;
	}

	//TODO: binding to Date - better way to use bean convention for property
	public setRegistration_date(String registration_date){
//		this.registration_date = registration_date;
//		Date d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(registration_date);
		this.registrationDate = registration_date;
	}
	
}

