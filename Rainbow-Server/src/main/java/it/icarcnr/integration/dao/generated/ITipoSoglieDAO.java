package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;

/**
 * Interface for TipoSoglieDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ITipoSoglieDAO {
	/**
	 * Perform an initial save of a previously unsaved TipoSoglie entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITipoSoglieDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TipoSoglie entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TipoSoglie entity);

	/**
	 * Delete a persistent TipoSoglie entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITipoSoglieDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TipoSoglie entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TipoSoglie entity);

	/**
	 * Persist a previously saved TipoSoglie entity and return it or a copy of
	 * it to the sender. A copy of the TipoSoglie entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ITipoSoglieDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TipoSoglie entity to update
	 * @return TipoSoglie the persisted TipoSoglie entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TipoSoglie update(TipoSoglie entity);

	public TipoSoglie findById(Integer id);

	/**
	 * Find all TipoSoglie entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TipoSoglie property to query
	 * @param value
	 *            the property value to match
	 * @return List<TipoSoglie> found by query
	 */
	public List<TipoSoglie> findByProperty(String propertyName, Object value);

	public List<TipoSoglie> findByDescrizione(Object descrizione);

	public List<TipoSoglie> findByLowerBound(Object lowerBound);

	public List<TipoSoglie> findByUpperBound(Object upperBound);

	public List<TipoSoglie> findByLivello(Object livello);

	public List<TipoSoglie> findByPathIcon(Object pathIcon);

	/**
	 * Find all TipoSoglie entities.
	 * 
	 * @return List<TipoSoglie> all TipoSoglie entities
	 */
	public List<TipoSoglie> findAll();
}