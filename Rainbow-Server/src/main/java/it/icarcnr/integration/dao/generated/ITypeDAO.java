package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;

/**
 * Interface for TypeDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ITypeDAO {
	/**
	 * Perform an initial save of a previously unsaved Type entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Type entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Type entity);

	/**
	 * Delete a persistent Type entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Type entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Type entity);

	/**
	 * Persist a previously saved Type entity and return it or a copy of it to
	 * the sender. A copy of the Type entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ITypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Type entity to update
	 * @return Type the persisted Type entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Type update(Type entity);

	public Type findById(Integer id);

	/**
	 * Find all Type entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Type property to query
	 * @param value
	 *            the property value to match
	 * @return List<Type> found by query
	 */
	public List<Type> findByProperty(String propertyName, Object value);

	public List<Type> findByName(Object name);

	public List<Type> findByDescription(Object description);

	/**
	 * Find all Type entities.
	 * 
	 * @return List<Type> all Type entities
	 */
	public List<Type> findAll();
}