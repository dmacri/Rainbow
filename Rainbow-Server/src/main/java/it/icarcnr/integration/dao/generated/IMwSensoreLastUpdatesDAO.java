package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import java.util.List;

/**
 * Interface for MwSensoreLastUpdatesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IMwSensoreLastUpdatesDAO {
	/**
	 * Perform an initial save of a previously unsaved MwSensoreLastUpdates
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IMwSensoreLastUpdatesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            MwSensoreLastUpdates entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(MwSensoreLastUpdates entity);

	/**
	 * Delete a persistent MwSensoreLastUpdates entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IMwSensoreLastUpdatesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            MwSensoreLastUpdates entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(MwSensoreLastUpdates entity);

	/**
	 * Persist a previously saved MwSensoreLastUpdates entity and return it or a
	 * copy of it to the sender. A copy of the MwSensoreLastUpdates entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IMwSensoreLastUpdatesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            MwSensoreLastUpdates entity to update
	 * @return MwSensoreLastUpdates the persisted MwSensoreLastUpdates entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public MwSensoreLastUpdates update(MwSensoreLastUpdates entity);

	public MwSensoreLastUpdates findById(Integer id);

	/**
	 * Find all MwSensoreLastUpdates entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the MwSensoreLastUpdates property to query
	 * @param value
	 *            the property value to match
	 * @return List<MwSensoreLastUpdates> found by query
	 */
	public List<MwSensoreLastUpdates> findByProperty(String propertyName,
			Object value);

	public List<MwSensoreLastUpdates> findByLastValue(Object lastValue);

	/**
	 * Find all MwSensoreLastUpdates entities.
	 * 
	 * @return List<MwSensoreLastUpdates> all MwSensoreLastUpdates entities
	 */
	public List<MwSensoreLastUpdates> findAll();
}