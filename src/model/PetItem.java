/**
 * 
 */
package model;

/**
 * @author Brandon Thompson - Brandon Thompson
 *CIS175 - Spring 2023
 * Jan 22, 2023
 */
public class PetItem {
	private int id;
	private String name;
	private String owner;
	
	public PetItem() {
		
	}
	
	public PetItem(String name, String owner) {
		this.name = name;
		this.owner = owner;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public String returnPetDetails() {
		return "Pet name: " + this.name + "\nOwner: " + this.owner;
	}
	
}
