package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the locations database table.
 * 
 */
@Entity
@Table(name="locations")
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LOCATIONS_LOCATIONID_GENERATOR", sequenceName="LOCATIONS_SEQ", allocationSize=100)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOCATIONS_LOCATIONID_GENERATOR")
	@Column(name="location_id")
	private Integer locationId;

	private String city;

	@Column(name="postal_code")
	private String postalCode;

	@Column(name="state_province")
	private String stateProvince;

	@Column(name="street_address")
	private String streetAddress;

	//uni-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="country_id")
	private Country country;

	public Location() {
	}

	public Integer getLocationId() {
		return this.locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStateProvince() {
		return this.stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public String getStreetAddress() {
		return this.streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}