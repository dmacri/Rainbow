package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * Interface for ServiceDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IServiceDAO {
	/**
	 * Perform an initial save of a previously unsaved Service entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IServiceDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Service entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Service entity);

	/**
	 * Delete a persistent Service entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IServiceDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Service entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Service entity);

	/**
	 * Persist a previously saved Service entity and return it or a copy of it
	 * to the sender. A copy of the Service entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IServiceDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Service entity to update
	 * @return Service the persisted Service entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Service update(Service entity);

	public Service findById(Integer id);

	/**
	 * Find all Service entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Service property to query
	 * @param value
	 *            the property value to match
	 * @return List<Service> found by query
	 */
	public List<Service> findByProperty(String propertyName, Object value);

	public List<Service> findByName(Object name);

	public List<Service> findByDescription(Object description);

	public List<Service> findByFile(Object file);

	public List<Service> findByValue(Object value);

	public List<Service> findBySamplingPeriod(Object samplingPeriod);

	public List<Service> findByLog(Object log);

	public List<Service> findByIdUtility(Object idUtility);

	public List<Service> findByStartMinute(Object startMinute);

	/**
	 * Find all Service entities.
	 * 
	 * @return List<Service> all Service entities
	 */
	public List<Service> findAll();
}