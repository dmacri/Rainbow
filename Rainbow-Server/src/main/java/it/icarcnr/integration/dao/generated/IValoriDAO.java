package it.icarcnr.integration.dao.generated;

import java.util.List;

/**
 * Interface for ValoriDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IValoriDAO {
	/**
	 * Perform an initial save of a previously unsaved Valori entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IValoriDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Valori entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Valori entity);

	/**
	 * Delete a persistent Valori entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IValoriDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Valori entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Valori entity);

	/**
	 * Persist a previously saved Valori entity and return it or a copy of it to
	 * the sender. A copy of the Valori entity parameter is returned when the
	 * JPA persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IValoriDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Valori entity to update
	 * @return Valori the persisted Valori entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Valori update(Valori entity);

	public Valori findById(ValoriId id);

	/**
	 * Find all Valori entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Valori property to query
	 * @param value
	 *            the property value to match
	 * @return List<Valori> found by query
	 */
	public List<Valori> findByProperty(String propertyName, Object value);

	public List<Valori> findByValue(Object value);

	/**
	 * Find all Valori entities.
	 * 
	 * @return List<Valori> all Valori entities
	 */
	public List<Valori> findAll();
}