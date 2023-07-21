package it.icarcnr.integration.dao.generated;

import java.util.List;

/**
 * Interface for HoltwintersDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IHoltwintersDAO {
	/**
	 * Perform an initial save of a previously unsaved Holtwinters entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IHoltwintersDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Holtwinters entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Holtwinters entity);

	/**
	 * Delete a persistent Holtwinters entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IHoltwintersDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Holtwinters entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Holtwinters entity);

	/**
	 * Persist a previously saved Holtwinters entity and return it or a copy of
	 * it to the sender. A copy of the Holtwinters entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IHoltwintersDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Holtwinters entity to update
	 * @return Holtwinters the persisted Holtwinters entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Holtwinters update(Holtwinters entity);

	public Holtwinters findById(HoltwintersId id);

	/**
	 * Find all Holtwinters entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Holtwinters property to query
	 * @param value
	 *            the property value to match
	 * @return List<Holtwinters> found by query
	 */
	public List<Holtwinters> findByProperty(String propertyName, Object value);

	public List<Holtwinters> findBySampleValue(Object sampleValue);

	public List<Holtwinters> findByCleanedValue(Object cleanedValue);

	public List<Holtwinters> findByEstimatedValue(Object estimatedValue);

	public List<Holtwinters> findByAt(Object at);

	public List<Holtwinters> findByFt(Object ft);

	public List<Holtwinters> findBySt(Object st);

	public List<Holtwinters> findByCleanSigmat(Object cleanSigmat);

	public List<Holtwinters> findByLowerBound(Object lowerBound);

	public List<Holtwinters> findByUpperBound(Object upperBound);

	public List<Holtwinters> findByBandSigmat(Object bandSigmat);

	/**
	 * Find all Holtwinters entities.
	 * 
	 * @return List<Holtwinters> all Holtwinters entities
	 */
	public List<Holtwinters> findAll();
}