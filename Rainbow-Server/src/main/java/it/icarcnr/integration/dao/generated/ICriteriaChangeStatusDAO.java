package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import java.util.List;

/**
 * Interface for CriteriaChangeStatusDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICriteriaChangeStatusDAO {
	/**
	 * Perform an initial save of a previously unsaved CriteriaChangeStatus
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICriteriaChangeStatusDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CriteriaChangeStatus entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CriteriaChangeStatus entity);

	/**
	 * Delete a persistent CriteriaChangeStatus entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICriteriaChangeStatusDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            CriteriaChangeStatus entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CriteriaChangeStatus entity);

	/**
	 * Persist a previously saved CriteriaChangeStatus entity and return it or a
	 * copy of it to the sender. A copy of the CriteriaChangeStatus entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ICriteriaChangeStatusDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CriteriaChangeStatus entity to update
	 * @return CriteriaChangeStatus the persisted CriteriaChangeStatus entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CriteriaChangeStatus update(CriteriaChangeStatus entity);

	public CriteriaChangeStatus findById(Integer id);

	/**
	 * Find all CriteriaChangeStatus entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CriteriaChangeStatus property to query
	 * @param value
	 *            the property value to match
	 * @return List<CriteriaChangeStatus> found by query
	 */
	public List<CriteriaChangeStatus> findByProperty(String propertyName,
			Object value);

	public List<CriteriaChangeStatus> findByStatusFrom(Object statusFrom);

	public List<CriteriaChangeStatus> findByStatusTo(Object statusTo);

	public List<CriteriaChangeStatus> findByValueAfter(Object valueAfter);

	/**
	 * Find all CriteriaChangeStatus entities.
	 * 
	 * @return List<CriteriaChangeStatus> all CriteriaChangeStatus entities
	 */
	public List<CriteriaChangeStatus> findAll();
}