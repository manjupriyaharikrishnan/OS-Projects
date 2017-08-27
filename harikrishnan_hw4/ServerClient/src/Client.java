/*Client Object*/
public class Client {
	//Client Information
	public String hostname;
	public String portnum;
	public String name;
	public String age;
	public String gender;
	public String latitude;
	public String longitude;
	public String ID;
	
	//Object Constructor
	public Client(String ID, String hostname, String portnum, String name, String latitude, String longitude, String gender, String age) {
		this.ID = ID;
		this.hostname = hostname;
		this.portnum = portnum;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	//Getter Setter Methods
	public String getID(){
		return ID;
	}
	
	public void setID(String ID){
		this.ID = ID;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getPortnum() {
		return portnum;
	}
	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public String getLongitude() {
		return latitude;
	}
	
	public void setLongitude(String longitude){
		this.longitude = longitude;
	}
}