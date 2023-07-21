package it.icarcnr.integration.dao.generated;

import java.util.List;

/**
 * Interface for NodoRasSenDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface INodoRasSenDAO {
	/**
	 * Perform an initial save of a previously unsaved NodoRasSen entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * INodoRasSenDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            NodoRasSen entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(NodoRasSen entity);

	/**
	 * Delete a persistent NodoRasSen entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * INodoRasSenDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            NodoRasSen entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(NodoRasSen entity);

	/**
	 * Persist a previously saved NodoRasSen entity and return it or a copy of
	 * it to the sender. A copy of the NodoRasSen entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = INodoRasSenDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            NodoRasSen entity to update
	 * @return NodoRasSen the persisted NodoRasSen entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public NodoRasSen update(NodoRasSen entity);

	public NodoRasSen findById(NodoRasSenId id);

	/**
	 * Find all NodoRasSen entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the NodoRasSen property to query
	 * @param value
	 *            the property value to match
	 * @return List<NodoRasSen> found by query
	 */
	public List<NodoRasSen> findByProperty(String propertyName, Object value);

	/**
	 * Find all NodoRasSen entities.
	 * 
	 * @return List<NodoRasSen> all NodoRasSen entities
	 */
	public List<NodoRasSen> findAll();
}