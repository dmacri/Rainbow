package it.icarcnr.integration.dao.generated;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * LocationGeoRas entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see it.icarcnr.integration.dao.generated.LocationGeoRas
 * @author MyEclipse Persistence Tools
 */

public class LocationGeoRasDAO implements ILocationGeoRasDAO {
	// property constants

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

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
	 * LocationGeoRasDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LocationGeoRas entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(LocationGeoRas entity) {
		EntityManagerHelper.log("saving LocationGeoRas instance", Level.INFO,
				null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * LocationGeoRasDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            LocationGeoRas entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(LocationGeoRas entity) {
		EntityManagerHelper.log("deleting LocationGeoRas instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(LocationGeoRas.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 * entity = LocationGeoRasDAO.update(entity);
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
	public LocationGeoRas update(LocationGeoRas entity) {
		EntityManagerHelper.log("updating LocationGeoRas instance", Level.INFO,
				null);
		try {
			LocationGeoRas result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public LocationGeoRas findById(Integer id) {
		EntityManagerHelper.log("finding LocationGeoRas instance with id: "
				+ id, Level.INFO, null);
		try {
			LocationGeoRas instance = getEntityManager().find(
					LocationGeoRas.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all LocationGeoRas entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the LocationGeoRas property to query
	 * @param value
	 *            the property value to match
	 * @return List<LocationGeoRas> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<LocationGeoRas> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding LocationGeoRas instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from LocationGeoRas model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all LocationGeoRas entities.
	 * 
	 * @return List<LocationGeoRas> all LocationGeoRas entities
	 */
	@SuppressWarnings("unchecked")
	public List<LocationGeoRas> findAll() {
		EntityManagerHelper.log("finding all LocationGeoRas instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from LocationGeoRas model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}