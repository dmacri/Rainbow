package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;

/**
 * Interface for SensoreDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ISensoreDAO {
	/**
	 * Perform an initial save of a previously unsaved Sensore entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISensoreDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Sensore entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Sensore entity);

	/**
	 * Delete a persistent Sensore entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISensoreDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Sensore entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Sensore entity);

	/**
	 * Persist a previously saved Sensore entity and return it or a copy of it
	 * to the sender. A copy of the Sensore entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ISensoreDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Sensore entity to update
	 * @return Sensore the persisted Sensore entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Sensore update(Sensore entity);

	public Sensore findById(Integer id);

	/**
	 * Find all Sensore entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Sensore property to query
	 * @param value
	 *            the property value to match
	 * @return List<Sensore> found by query
	 */
	public List<Sensore> findByProperty(String propertyName, Object value);

	public List<Sensore> findByDescription(Object description);

	/**
	 * Find all Sensore entities.
	 * 
	 * @return List<Sensore> all Sensore entities
	 */
	public List<Sensore> findAll();
}