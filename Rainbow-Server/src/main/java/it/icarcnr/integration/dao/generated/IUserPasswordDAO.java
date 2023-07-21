package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import java.util.List;

/**
 * Interface for UserPasswordDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IUserPasswordDAO {
	/**
	 * Perform an initial save of a previously unsaved UserPassword entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUserPasswordDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserPassword entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(UserPassword entity);

	/**
	 * Delete a persistent UserPassword entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUserPasswordDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            UserPassword entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(UserPassword entity);

	/**
	 * Persist a previously saved UserPassword entity and return it or a copy of
	 * it to the sender. A copy of the UserPassword entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IUserPasswordDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserPassword entity to update
	 * @return UserPassword the persisted UserPassword entity instance, may not
	 *         be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public UserPassword update(UserPassword entity);

	public UserPassword findById(Integer id);

	/**
	 * Find all UserPassword entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the UserPassword property to query
	 * @param value
	 *            the property value to match
	 * @return List<UserPassword> found by query
	 */
	public List<UserPassword> findByProperty(String propertyName, Object value);

	public List<UserPassword> findByValue(Object value);

	/**
	 * Find all UserPassword entities.
	 * 
	 * @return List<UserPassword> all UserPassword entities
	 */
	public List<UserPassword> findAll();
}