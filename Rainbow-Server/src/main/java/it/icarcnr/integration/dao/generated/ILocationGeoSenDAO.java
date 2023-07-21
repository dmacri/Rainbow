package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import java.util.List;

/**
 * Interface for LocationGeoSenDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ILocationGeoSenDAO {
	/**
	 * Perform an initial save of a previously unsaved LocationGeoSen entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILocationGeoSenDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LocationGeoSen entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(LocationGeoSen entity);

	/**
	 * Delete a persistent LocationGeoSen entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILocationGeoSenDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            LocationGeoSen entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(LocationGeoSen entity);

	/**
	 * Persist a previously saved LocationGeoSen entity and return it or a copy
	 * of it to the sender. A copy of the LocationGeoSen entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ILocationGeoSenDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LocationGeoSen entity to update
	 * @return LocationGeoSen the persisted LocationGeoSen entity instance, may
	 *         not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public LocationGeoSen update(LocationGeoSen entity);

	public LocationGeoSen findById(Integer id);

	/**
	 * Find all LocationGeoSen entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the LocationGeoSen property to query
	 * @param value
	 *            the property value to match
	 * @return List<LocationGeoSen> found by query
	 */
	public List<LocationGeoSen> findByProperty(String propertyName, Object value);

	/**
	 * Find all LocationGeoSen entities.
	 * 
	 * @return List<LocationGeoSen> all LocationGeoSen entities
	 */
	public List<LocationGeoSen> findAll();
}