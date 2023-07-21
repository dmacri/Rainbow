package it.icarcnr.integration.dao.generated;

import java.util.List;

/**
 * Interface for Q2fPerGraficiDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IQ2fPerGraficiDAO {
	/**
	 * Perform an initial save of a previously unsaved Q2fPerGrafici entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IQ2fPerGraficiDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Q2fPerGrafici entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Q2fPerGrafici entity);

	/**
	 * Delete a persistent Q2fPerGrafici entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IQ2fPerGraficiDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Q2fPerGrafici entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Q2fPerGrafici entity);

	/**
	 * Persist a previously saved Q2fPerGrafici entity and return it or a copy
	 * of it to the sender. A copy of the Q2fPerGrafici entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IQ2fPerGraficiDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Q2fPerGrafici entity to update
	 * @return Q2fPerGrafici the persisted Q2fPerGrafici entity instance, may
	 *         not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Q2fPerGrafici update(Q2fPerGrafici entity);

	public Q2fPerGrafici findById(Q2fPerGraficiId id);

	/**
	 * Find all Q2fPerGrafici entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Q2fPerGrafici property to query
	 * @param value
	 *            the property value to match
	 * @return List<Q2fPerGrafici> found by query
	 */
	public List<Q2fPerGrafici> findByProperty(String propertyName, Object value);

	/**
	 * Find all Q2fPerGrafici entities.
	 * 
	 * @return List<Q2fPerGrafici> all Q2fPerGrafici entities
	 */
	public List<Q2fPerGrafici> findAll();
}