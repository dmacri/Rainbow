package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;

/**
 * Interface for GroupDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IGroupDAO {
	/**
	 * Perform an initial save of a previously unsaved Group entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IGroupDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Group entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Group entity);

	/**
	 * Delete a persistent Group entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IGroupDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Group entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Group entity);

	/**
	 * Persist a previously saved Group entity and return it or a copy of it to
	 * the sender. A copy of the Group entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IGroupDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Group entity to update
	 * @return Group the persisted Group entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Group update(Group entity);

	public Group findById(Integer id);

	/**
	 * Find all Group entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Group property to query
	 * @param value
	 *            the property value to match
	 * @return List<Group> found by query
	 */
	public List<Group> findByProperty(String propertyName, Object value);

	public List<Group> findByName(Object name);

	/**
	 * Find all Group entities.
	 * 
	 * @return List<Group> all Group entities
	 */
	public List<Group> findAll();
}