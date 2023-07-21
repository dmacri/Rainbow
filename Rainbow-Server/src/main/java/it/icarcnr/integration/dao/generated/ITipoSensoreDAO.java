package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;

/**
 * Interface for TipoSensoreDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ITipoSensoreDAO {
	/**
	 * Perform an initial save of a previously unsaved TipoSensore entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITipoSensoreDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TipoSensore entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(TipoSensore entity);

	/**
	 * Delete a persistent TipoSensore entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITipoSensoreDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TipoSensore entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(TipoSensore entity);

	/**
	 * Persist a previously saved TipoSensore entity and return it or a copy of
	 * it to the sender. A copy of the TipoSensore entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ITipoSensoreDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TipoSensore entity to update
	 * @return TipoSensore the persisted TipoSensore entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public TipoSensore update(TipoSensore entity);

	public TipoSensore findById(Integer id);

	/**
	 * Find all TipoSensore entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TipoSensore property to query
	 * @param value
	 *            the property value to match
	 * @return List<TipoSensore> found by query
	 */
	public List<TipoSensore> findByProperty(String propertyName, Object value);

	public List<TipoSensore> findByDescription(Object description);

	public List<TipoSensore> findByUnitaMis(Object unitaMis);

	public List<TipoSensore> findByMin(Object min);

	public List<TipoSensore> findByMax(Object max);

	public List<TipoSensore> findByName(Object name);

	/**
	 * Find all TipoSensore entities.
	 * 
	 * @return List<TipoSensore> all TipoSensore entities
	 */
	public List<TipoSensore> findAll();
}