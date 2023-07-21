package it.icarcnr.integration.dao.generated;

import java.util.List;

/**
 * Interface for ReleaseDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IReleaseDAO {
	/**
	 * Perform an initial save of a previously unsaved Release entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IReleaseDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Release entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Release entity);

	/**
	 * Delete a persistent Release entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IReleaseDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Release entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Release entity);

	/**
	 * Persist a previously saved Release entity and return it or a copy of it
	 * to the sender. A copy of the Release entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IReleaseDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Release entity to update
	 * @return Release the persisted Release entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Release update(Release entity);

	public Release findById(Integer id);

	/**
	 * Find all Release entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Release property to query
	 * @param value
	 *            the property value to match
	 * @return List<Release> found by query
	 */
	public List<Release> findByProperty(String propertyName, Object value);

	public List<Release> findByVersion(Object version);

	/**
	 * Find all Release entities.
	 * 
	 * @return List<Release> all Release entities
	 */
	public List<Release> findAll();
}