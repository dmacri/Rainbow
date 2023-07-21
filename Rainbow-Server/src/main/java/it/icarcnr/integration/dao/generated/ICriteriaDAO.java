package it.icarcnr.integration.dao.generated;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Interface for CriteriaDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICriteriaDAO {
	/**
	 * Perform an initial save of a previously unsaved Criteria entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICriteriaDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Criteria entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Criteria entity);

	/**
	 * Delete a persistent Criteria entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICriteriaDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Criteria entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Criteria entity);

	/**
	 * Persist a previously saved Criteria entity and return it or a copy of it
	 * to the sender. A copy of the Criteria entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ICriteriaDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Criteria entity to update
	 * @return Criteria the persisted Criteria entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Criteria update(Criteria entity);

	public Criteria findById(Integer id);

	/**
	 * Find all Criteria entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Criteria property to query
	 * @param value
	 *            the property value to match
	 * @return List<Criteria> found by query
	 */
	public List<Criteria> findByProperty(String propertyName, Object value);

	public List<Criteria> findByValue(Object value);

	public List<Criteria> findByMonday(Object monday);

	public List<Criteria> findByTuesday(Object tuesday);

	public List<Criteria> findByWednesday(Object wednesday);

	public List<Criteria> findByThursday(Object thursday);

	public List<Criteria> findByFriday(Object friday);

	public List<Criteria> findBySaturday(Object saturday);

	public List<Criteria> findBySunday(Object sunday);

	public List<Criteria> findByValueCheckMajor(Object valueCheckMajor);

	public List<Criteria> findByDescription(Object description);

	public List<Criteria> findByStatus(Object status);

	public List<Criteria> findByTypeCheck(Object typeCheck);

	public List<Criteria> findByValueCheckCritical(Object valueCheckCritical);

	public List<Criteria> findByEnabled(Object enabled);

	public List<Criteria> findByValueCheckSecondaryMajor(
			Object valueCheckSecondaryMajor);

	public List<Criteria> findByValueCheckSecondaryCritical(
			Object valueCheckSecondaryCritical);

	public List<Criteria> findBySuspended(Object suspended);

	public List<Criteria> findByCalculation(Object calculation);

	/**
	 * Find all Criteria entities.
	 * 
	 * @return List<Criteria> all Criteria entities
	 */
	public List<Criteria> findAll();
}