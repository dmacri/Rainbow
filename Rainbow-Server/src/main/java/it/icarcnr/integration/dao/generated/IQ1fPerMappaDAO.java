package it.icarcnr.integration.dao.generated;

import java.util.List;

/**
 * Interface for Q1fPerMappaDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IQ1fPerMappaDAO {
	/**
	 * Perform an initial save of a previously unsaved Q1fPerMappa entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IQ1fPerMappaDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Q1fPerMappa entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Q1fPerMappa entity);

	/**
	 * Delete a persistent Q1fPerMappa entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IQ1fPerMappaDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Q1fPerMappa entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Q1fPerMappa entity);

	/**
	 * Persist a previously saved Q1fPerMappa entity and return it or a copy of
	 * it to the sender. A copy of the Q1fPerMappa entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IQ1fPerMappaDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Q1fPerMappa entity to update
	 * @return Q1fPerMappa the persisted Q1fPerMappa entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Q1fPerMappa update(Q1fPerMappa entity);

	public Q1fPerMappa findById(Q1fPerMappaId id);

	/**
	 * Find all Q1fPerMappa entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Q1fPerMappa property to query
	 * @param value
	 *            the property value to match
	 * @return List<Q1fPerMappa> found by query
	 */
	public List<Q1fPerMappa> findByProperty(String propertyName, Object value);

	/**
	 * Find all Q1fPerMappa entities.
	 * 
	 * @return List<Q1fPerMappa> all Q1fPerMappa entities
	 */
	public List<Q1fPerMappa> findAll();
}