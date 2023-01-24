/**
 * 
 */
package view;

import java.util.List;
import java.util.Scanner;


import controller.PetItemHelper;
import model.PetItem;

/**
 * @author Brandon Thompson - Brandon Thompson
 *CIS175 - Spring 2023
 * Jan 23, 2023
 */
public class DatabaseViewer {

	static Scanner userInput = new Scanner(System.in);
	static PetItemHelper pih = new PetItemHelper();

	public static void main(String[] args) {
		menu();
	}

	public static void menu() {
		boolean loop = true;
		System.out.println("Welcome what would you like to do?");
		while (loop) {
			System.out.println("  Select an item:");
			System.out.println(" 1. Add a pet");
			System.out.println(" 2. Edit a pet");
			System.out.println(" 3. Delete a pet");
			System.out.println(" 4. View list");
			System.out.println(" 5. Exit program");
			System.out.print(" Your selection: ");
			int selection = userInput.nextInt();
			userInput.nextLine();

			if (selection == 1) {
				addPet();
			}
			
			else if (selection == 2) {
				editPet();
			}
			
			else if (selection == 3) {
				deletePet();
			}
			
			else if (selection == 4) {
				viewTheList();
			}
			
			else {
				pih.cleanUp();
				System.out.println("   Goodbye!   ");
				loop = false;
			}

		}

	}
	
	private static void addPet() {
		System.out.print("Enter a pet: ");
		String pet = userInput.nextLine();
		System.out.print("Enter an owner: ");
		String owner = userInput.nextLine();
		PetItem toAdd = new PetItem(pet, owner);
		pih.insertItem(toAdd);

	}
	
	private static void deletePet() {
		System.out.print("Enter the pet to delete: ");
		String pet = userInput.nextLine();
		System.out.print("Enter the owner to delete: ");
		String owner = userInput.nextLine();
		PetItem toDelete = new PetItem(pet, owner);
		pih.deletePet(toDelete);

	}
	
	
	
	private static void viewTheList() {
		List<PetItem> allPets = pih.showAllPets();
		for(PetItem singlePet : allPets) {
			System.out.println(singlePet.returnPetDetails());
		}

	}
	
	private static void editPet() {
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by pet name");
		System.out.println("2 : Search by owner");
		int searchBy = userInput.nextInt();
		userInput.nextLine();
		List<PetItem> foundItems;
		if (searchBy == 1) {
			System.out.print("Enter the pet name name: ");
			String petName = userInput.nextLine();
			foundItems = pih.searchForPetByName(petName);
			
		}
		
		else {
			System.out.print("Enter the owner: ");
			String ownerName = userInput.nextLine();
			foundItems = pih.searchForPetByOwner(ownerName);

		}

		if (!foundItems.isEmpty()) {
			System.out.println("Found Results.");
			for (PetItem l : foundItems) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			
			System.out.print("Which ID to edit: ");
			int idToEdit = userInput.nextInt();

			PetItem toEdit = pih.searchForPetById(idToEdit);
			System.out.println("Retrieved " + toEdit.getName() + " from " + toEdit.getOwner());
			System.out.println("1 : Update pet name");
			System.out.println("2 : Update owner");
			int update = userInput.nextInt();
			userInput.nextLine();

			if (update == 1) {
				System.out.print("New pet: ");
				String newStore = userInput.nextLine();
				toEdit.setName(newStore);
			}
			
			else if (update == 2) {
				System.out.print("New owner: ");
				String newItem = userInput.nextLine();
				toEdit.setOwner(newItem);
			}

			pih.updatePet(toEdit);

		}
		
		else {
			System.out.println("No pets found");
		}

	}

}
