package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;

/**
 * Interface for NetworkDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface INetworkDAO {
	/**
	 * Perform an initial save of a previously unsaved Network entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * INetworkDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Network entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Network entity);

	/**
	 * Delete a persistent Network entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * INetworkDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Network entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Network entity);

	/**
	 * Persist a previously saved Network entity and return it or a copy of it
	 * to the sender. A copy of the Network entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = INetworkDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Network entity to update
	 * @return Network the persisted Network entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Network update(Network entity);

	public Network findById(Integer id);

	/**
	 * Find all Network entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Network property to query
	 * @param value
	 *            the property value to match
	 * @return List<Network> found by query
	 */
	public List<Network> findByProperty(String propertyName, Object value);

	public List<Network> findByName(Object name);

	public List<Network> findByDescription(Object description);

	/**
	 * Find all Network entities.
	 * 
	 * @return List<Network> all Network entities
	 */
	public List<Network> findAll();
}