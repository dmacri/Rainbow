package it.icarcnr.integration.dao.generated;

import java.util.List;

/**
 * Interface for CriteriaHistoryPk3DAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICriteriaHistoryPk3DAO {
	/**
	 * Perform an initial save of a previously unsaved CriteriaHistoryPk3
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICriteriaHistoryPk3DAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CriteriaHistoryPk3 entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CriteriaHistoryPk3 entity);

	/**
	 * Delete a persistent CriteriaHistoryPk3 entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICriteriaHistoryPk3DAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            CriteriaHistoryPk3 entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CriteriaHistoryPk3 entity);

	/**
	 * Persist a previously saved CriteriaHistoryPk3 entity and return it or a
	 * copy of it to the sender. A copy of the CriteriaHistoryPk3 entity
	 * parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#merge(Object)
	 * EntityManager#merge} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ICriteriaHistoryPk3DAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CriteriaHistoryPk3 entity to update
	 * @return CriteriaHistoryPk3 the persisted CriteriaHistoryPk3 entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CriteriaHistoryPk3 update(CriteriaHistoryPk3 entity);

	public CriteriaHistoryPk3 findById(CriteriaHistoryPk3Id id);

	/**
	 * Find all CriteriaHistoryPk3 entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CriteriaHistoryPk3 property to query
	 * @param value
	 *            the property value to match
	 * @return List<CriteriaHistoryPk3> found by query
	 */
	public List<CriteriaHistoryPk3> findByProperty(String propertyName,
			Object value);

	public List<CriteriaHistoryPk3> findByStatus(Object status);

	public List<CriteriaHistoryPk3> findByValue(Object value);

	public List<CriteriaHistoryPk3> findByValueCheckMajor(Object valueCheckMajor);

	public List<CriteriaHistoryPk3> findByValueCheckCritical(
			Object valueCheckCritical);

	public List<CriteriaHistoryPk3> findByValueCheckSecondaryMajor(
			Object valueCheckSecondaryMajor);

	public List<CriteriaHistoryPk3> findByValueCheckSecondaryCritical(
			Object valueCheckSecondaryCritical);

	/**
	 * Find all CriteriaHistoryPk3 entities.
	 * 
	 * @return List<CriteriaHistoryPk3> all CriteriaHistoryPk3 entities
	 */
	public List<CriteriaHistoryPk3> findAll();
}