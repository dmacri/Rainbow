package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;

/**
 * Interface for RequestDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IRequestDAO {
	/**
	 * Perform an initial save of a previously unsaved Request entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRequestDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Request entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Request entity);

	/**
	 * Delete a persistent Request entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRequestDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Request entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Request entity);

	/**
	 * Persist a previously saved Request entity and return it or a copy of it
	 * to the sender. A copy of the Request entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IRequestDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Request entity to update
	 * @return Request the persisted Request entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Request update(Request entity);

	public Request findById(Integer id);

	/**
	 * Find all Request entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Request property to query
	 * @param value
	 *            the property value to match
	 * @return List<Request> found by query
	 */
	public List<Request> findByProperty(String propertyName, Object value);

	public List<Request> findByName(Object name);

	public List<Request> findByDescription(Object description);

	public List<Request> findByDescriptionExtended(Object descriptionExtended);

	public List<Request> findByPercentageGapMajor(Object percentageGapMajor);

	public List<Request> findByPercentageGapCritical(
			Object percentageGapCritical);

	/**
	 * Find all Request entities.
	 * 
	 * @return List<Request> all Request entities
	 */
	public List<Request> findAll();
}