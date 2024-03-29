package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import java.util.List;

/**
 * Interface for LocationGeoRasDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ILocationGeoRasDAO {
	/**
	 * Perform an initial save of a previously unsaved LocationGeoRas entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILocationGeoRasDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LocationGeoRas entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(LocationGeoRas entity);

	/**
	 * Delete a persistent LocationGeoRas entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILocationGeoRasDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            LocationGeoRas entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(LocationGeoRas entity);

	/**
	 * Persist a previously saved LocationGeoRas entity and return it or a copy
	 * of it to the sender. A copy of the LocationGeoRas entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ILocationGeoRasDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LocationGeoRas entity to update
	 * @return LocationGeoRas the persisted LocationGeoRas entity instance, may
	 *         not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public LocationGeoRas update(LocationGeoRas entity);

	public LocationGeoRas findById(Integer id);

	/**
	 * Find all LocationGeoRas entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the LocationGeoRas property to query
	 * @param value
	 *            the property value to match
	 * @return List<LocationGeoRas> found by query
	 */
	public List<LocationGeoRas> findByProperty(String propertyName, Object value);

	/**
	 * Find all LocationGeoRas entities.
	 * 
	 * @return List<LocationGeoRas> all LocationGeoRas entities
	 */
	public List<LocationGeoRas> findAll();
}