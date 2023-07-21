package it.icarcnr.integration.dao.generated;

import java.util.List;
import java.util.Set;

/**
 * Interface for PositionDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPositionDAO {
	/**
	 * Perform an initial save of a previously unsaved Position entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPositionDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Position entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Position entity);

	/**
	 * Delete a persistent Position entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPositionDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Position entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Position entity);

	/**
	 * Persist a previously saved Position entity and return it or a copy of it
	 * to the sender. A copy of the Position entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IPositionDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Position entity to update
	 * @return Position the persisted Position entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Position update(Position entity);

	public Position findById(Integer id);

	/**
	 * Find all Position entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Position property to query
	 * @param value
	 *            the property value to match
	 * @return List<Position> found by query
	 */
	public List<Position> findByProperty(String propertyName, Object value);

	public List<Position> findByLat(Object lat);

	public List<Position> findByLng(Object lng);

	public List<Position> findByIndirizzo(Object indirizzo);

	/**
	 * Find all Position entities.
	 * 
	 * @return List<Position> all Position entities
	 */
	public List<Position> findAll();
}