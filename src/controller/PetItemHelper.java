/**
 * 
 */
package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.PetItem;
/**
 * @author Brandon Thompson - Brandon Thompson
 *CIS175 - Spring 2023
 * Jan 22, 2023
 */
public class PetItemHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PetsList"); // Entity manager for the PetItem class
	
	public void insertItem(PetItem pi) { // Allows new pet objects to be created.
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(pi);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<PetItem> showAllPets(){ // Shows all of the held pets in the database
		EntityManager em = emfactory.createEntityManager();
		List<PetItem> allItems = em.createQuery("SELECT i FROM PetItem i").getResultList();
		return allItems;
	}
	
	public void deletePet(PetItem toDelete) { // Allows pets to be deleted
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PetItem> typedQuery = em.createQuery("SELECT pi FROM PetItem pi WHERE pi.name = :selectedPet and pi.owner = :selectedOwner", PetItem.class);
		typedQuery.setParameter("selectedPet", toDelete.getName());
		typedQuery.setParameter("selectedOwner", toDelete.getOwner());
		
		typedQuery.setMaxResults(1);
		
		PetItem result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();;
		em.close();
	}
	
	public PetItem searchForPetById(int idToEdit) { // Allows user to search pets by id.
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		PetItem found = em.find(PetItem.class, idToEdit);
		em.close();
		return found;
		
		
	}
	
	public List<PetItem> searchForPetByName(String petName) { // Allows pets to be found by their name
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PetItem> typedQuery = em.createQuery("SELECT pi FROM PetItem pi WHERE pi.name = :selectedPet", PetItem.class);
		typedQuery.setParameter("selectedPet", petName);
		
		List<PetItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public List<PetItem> searchForPetByOwner(String ownerName) { // Allows pets to be found by their owners name.
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<PetItem> typedQuery = em.createQuery("SELECT pi FROM PetItem pi WHERE pi.owner = :selectedOwner", PetItem.class);
		typedQuery.setParameter("selectedOwner", ownerName);
		
		List<PetItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	
	public void updatePet(PetItem toEdit) { // Allows user to update a pet
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public void cleanUp() {
		emfactory.close();
	}
}
