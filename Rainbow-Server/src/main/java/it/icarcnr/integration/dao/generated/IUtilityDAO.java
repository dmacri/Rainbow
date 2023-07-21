package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;

/**
 * Interface for UtilityDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IUtilityDAO {
	/**
	 * Perform an initial save of a previously unsaved Utility entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUtilityDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Utility entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Utility entity);

	/**
	 * Delete a persistent Utility entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUtilityDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Utility entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Utility entity);

	/**
	 * Persist a previously saved Utility entity and return it or a copy of it
	 * to the sender. A copy of the Utility entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IUtilityDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Utility entity to update
	 * @return Utility the persisted Utility entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Utility update(Utility entity);

	public Utility findById(Integer id);

	/**
	 * Find all Utility entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Utility property to query
	 * @param value
	 *            the property value to match
	 * @return List<Utility> found by query
	 */
	public List<Utility> findByProperty(String propertyName, Object value);

	public List<Utility> findByScript(Object script);

	public List<Utility> findByDescription(Object description);

	/**
	 * Find all Utility entities.
	 * 
	 * @return List<Utility> all Utility entities
	 */
	public List<Utility> findAll();
}