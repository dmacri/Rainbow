package it.icarcnr.integration.dao.generated;

import java.util.List;

/**
 * Interface for SensoreSoglieDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ISensoreSoglieDAO {
	/**
	 * Perform an initial save of a previously unsaved SensoreSoglie entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISensoreSoglieDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SensoreSoglie entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(SensoreSoglie entity);

	/**
	 * Delete a persistent SensoreSoglie entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISensoreSoglieDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            SensoreSoglie entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(SensoreSoglie entity);

	/**
	 * Persist a previously saved SensoreSoglie entity and return it or a copy
	 * of it to the sender. A copy of the SensoreSoglie entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ISensoreSoglieDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            SensoreSoglie entity to update
	 * @return SensoreSoglie the persisted SensoreSoglie entity instance, may
	 *         not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public SensoreSoglie update(SensoreSoglie entity);

	public SensoreSoglie findById(Integer id);

	/**
	 * Find all SensoreSoglie entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the SensoreSoglie property to query
	 * @param value
	 *            the property value to match
	 * @return List<SensoreSoglie> found by query
	 */
	public List<SensoreSoglie> findByProperty(String propertyName, Object value);

	/**
	 * Find all SensoreSoglie entities.
	 * 
	 * @return List<SensoreSoglie> all SensoreSoglie entities
	 */
	public List<SensoreSoglie> findAll();
}