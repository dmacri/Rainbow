package it.icarcnr.integration.dao.servicestatus.impl;

import it.icarcnr.business.maps.bean.MapsBean;
import it.icarcnr.integration.dao.generated.EntityManagerHelper;
import it.icarcnr.integration.dao.generated.LocationGeoSen;
import it.icarcnr.integration.dao.generated.NodeSen;
import it.icarcnr.integration.dao.generated.Position;
import it.icarcnr.integration.dao.generated.Q1fPerMappa;
import it.icarcnr.integration.dao.generated.Q2fPerGrafici;
import it.icarcnr.integration.dao.generated.Sensore;
import it.icarcnr.integration.dao.generated.SensoreSoglie;
import it.icarcnr.integration.dao.generated.TipoSoglie;
import it.icarcnr.integration.dao.generated.Valori;
import it.icarcnr.integration.dao.servicestatus.bean.CriteriaHistoryBean;
import it.icarcnr.integration.dao.servicestatus.bean.CriteriaSensorValue;
import it.icarcnr.integration.dao.servicestatus.service.ICriteriaHistoryAdvancedDAO;
import it.icarcnr.integration.dao.util.INetworkConstants;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jfree.data.Value;

public class CriteriaHistoryAdvancedDAO implements ICriteriaHistoryAdvancedDAO {

	public List<CriteriaHistoryBean> getServiceHistory(
			Integer serviceOperationId, Date startDate, Date endDate,
			Integer networkId) {

		EntityManagerHelper
				.log("getServiceHistory (Integer serviceOperationId, Date startDate, Date endDate)",
						Level.INFO, null);

		Timestamp startSqlDate = new Timestamp(startDate.getTime());
		Timestamp endSqlDate = new Timestamp(endDate.getTime());
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			StringBuilder queryString = new StringBuilder();

			queryString
					.append("SELECT NEW "
							+ CriteriaHistoryBean.class.getName()
							+ "(criteriahistory.id.idServiceOperation, criteriahistory.id.date, "
							+ "criteriahistory.value, criteriahistory.valueCheckMajor, "
							+ "criteriahistory.valueCheckCritical,"
							+ "criteriahistory.valueCheckSecondaryMajor,"
							+ "criteriahistory.valueCheckSecondaryCritical"
							+ ")");

			queryString.append(" FROM ");

			queryString
					.append(INetworkConstants.CRITERIA_HISTORY_CLASS_NAME[networkId - 1]);

			queryString.append(" as criteriahistory ");

			queryString
					.append(" WHERE criteriahistory.id.idServiceOperation = :serviceOperationId ");
			queryString
					.append(" AND (criteriahistory.id.date BETWEEN :startDate AND :endDate) ");
			//
			queryString.append(" ORDER BY criteriahistory.id.date ASC");

			Query query = em.createQuery(queryString.toString());
			query.setParameter("serviceOperationId", serviceOperationId);
			query.setParameter("startDate", startSqlDate);
			query.setParameter("endDate", endSqlDate);
			return query.getResultList();

		} catch (RuntimeException re) {
			EntityManagerHelper
					.log("getServiceHistory (Integer serviceOperationId, Date startDate, Date endDate, Integer networkId)",
							Level.SEVERE, re);
			throw re;
		}
	}

	// public List<CriteriaSensorValue> getSensorValue(Integer sensoreId, Date
	// startDate, Date endDate) {
	//
	// EntityManagerHelper.log("getSensorValue (Integer sensoreId, Date startDate, Date endDate)  ",
	// Level.INFO, null);
	//
	// Timestamp startSqlDate = new Timestamp(startDate.getTime());
	// Timestamp endSqlDate = new Timestamp(endDate.getTime());
	// try {
	// EntityManager em = EntityManagerHelper.getEntityManager();
	// StringBuilder queryString = new StringBuilder();
	//
	// queryString.append("SELECT NEW "+CriteriaSensorValue.class.getCanonicalName()+" (valoriSensori.sensore.id, "
	// +
	// "valoriSensori.timestamp,"
	// +
	// "valoriSensori.value,valoriSensori.id,valoriSensori.sensore.description"+")");
	// queryString.append(" FROM ");
	// queryString.append(Valori.class.getName());
	// queryString.append(" as valoriSensori ");
	// queryString.append(" WHERE valoriSensori.sensore.id = :sensoreId ");
	// queryString.append(" AND (valoriSensori.timestamp BETWEEN :startDate AND :endDate) ");
	// queryString.append(" ORDER BY valoriSensori.timestamp ASC");
	//
	// Query query = em.createQuery(queryString.toString());
	// query.setParameter("sensoreId", sensoreId);
	// query.setParameter("startDate", startSqlDate);
	// query.setParameter("endDate", endSqlDate);
	// return query.getResultList();
	//
	// } catch (RuntimeException re) {
	// EntityManagerHelper.log("getSensorValue (Integer sensoreId, Date startDate, Date endDate)",
	// Level.SEVERE, re);
	// throw re;
	// }
	// }

	// public List<CriteriaSensorValue> getSensorValue(Integer sensoreId, Date
	// startDate, Date endDate) {
	//
	// EntityManagerHelper.log("getSensorValue (Integer sensoreId, Date startDate, Date endDate)  ",
	// Level.INFO, null);
	//
	// Timestamp startSqlDate = new Timestamp(startDate.getTime());
	// Timestamp endSqlDate = new Timestamp(endDate.getTime());
	// try {
	// EntityManager em = EntityManagerHelper.getEntityManager();
	// StringBuilder queryString = new StringBuilder();
	//
	// queryString.append("SELECT NEW "+CriteriaSensorValue.class.getCanonicalName()+" (valoriSensori.sensore.id, "
	// +
	// "valoriSensori.timestamp,"
	// +
	// "valoriSensori.value,valoriSensori.id,valoriSensori.sensore.description,positionGeo.lat,positionGeo.lng"+")");
	// queryString.append(" FROM ");
	// queryString.append(Valori.class.getName());
	// queryString.append(" as valoriSensori, ");
	// queryString.append(LocationGeoSen.class.getName());
	// queryString.append(" as locationGeoSen,");
	// queryString.append(Position.class.getName());
	// queryString.append(" as positionGeo ");
	// queryString.append(" WHERE valoriSensori.sensore.id = :sensoreId ");
	// queryString.append(" AND (valoriSensori.timestamp BETWEEN :startDate AND :endDate) ");
	// queryString.append(" AND valoriSensori.sensore.nodeSen.id = locationGeoSen.nodeSen.id AND ");
	// queryString.append(" locationGeoSen.position.id = positionGeo.id ");
	// queryString.append(" ORDER BY valoriSensori.timestamp ASC");
	//
	// Query query = em.createQuery(queryString.toString());
	// query.setParameter("sensoreId", sensoreId);
	// query.setParameter("startDate", startSqlDate);
	// query.setParameter("endDate", endSqlDate);
	// return query.getResultList();
	//
	// } catch (RuntimeException re) {
	// EntityManagerHelper.log("getSensorValue (Integer sensoreId, Date startDate, Date endDate)",
	// Level.SEVERE, re);
	// throw re;
	// }
	// }

	public List<CriteriaSensorValue> getSensorValue(Integer sensoreId,Date startDate, Date endDate) {

		EntityManagerHelper
				.log("getSensorValue (Integer sensoreId, Date startDate, Date endDate)  ",
						Level.INFO, null);

		Timestamp startSqlDate = new Timestamp(startDate.getTime());
		Timestamp endSqlDate = new Timestamp(endDate.getTime());
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			StringBuilder queryString = new StringBuilder();

			queryString
					.append("SELECT NEW "
							+ CriteriaSensorValue.class.getCanonicalName()
							+ " (q2.id.sensoreId, "
							+ "q2.id.valoriTimestamp,"
							+ "q2.id.valoriValue,q2.id.valoriId,q2.id.sensoreDescription,q2.id.positionLat,q2.id.positionLng"
							+ ")");
			queryString.append(" FROM ");
			queryString.append(Q2fPerGrafici.class.getName());
			queryString.append(" as q2 ");
			queryString.append(" WHERE q2.id.sensoreId = :sensoreIdFrom ");
			queryString
					.append(" AND (q2.id.valoriTimestamp BETWEEN :startDate AND :endDate) ");
			startSqlDate.setTime(startSqlDate.getTime() + 1000*60*60*2);
			endSqlDate.setTime(endSqlDate.getTime() + 1000*60*60*2);
			Query query = em.createQuery(queryString.toString());
			query.setParameter("sensoreIdFrom", sensoreId);
			query.setParameter("startDate", startSqlDate);
			query.setParameter("endDate", endSqlDate);
			System.out.println(query.toString());
			return query.getResultList();

		} catch (RuntimeException re) {
			EntityManagerHelper
					.log("getSensorValue (Integer sensoreId, Date startDate, Date endDate)",
							Level.SEVERE, re);
			throw re;
		}
	}

	// public List<MapsBean> getAllLatestMapsValue () {
	//
	// EntityManagerHelper.log("getAllLatestMapsValue ()  ", Level.INFO, null);
	//
	//
	// try {
	// EntityManager em = EntityManagerHelper.getEntityManager();
	// StringBuilder queryString = new StringBuilder();
	//
	// queryString.append("SELECT NEW "+MapsBean.class.getCanonicalName()+" (valoriSensori.sensore.id,"
	// +
	// " MAX(valoriSensori.timestamp),valoriSensori.sensore.tipoSensore.name,"
	// +
	// "valoriSensori.value,soglieSensori.livello,soglieSensori.lowerBound,soglieSensori.upperBound,positionGeo.lat,positionGeo.lng"+")");
	// queryString.append(" FROM ");
	// queryString.append(Valori.class.getName());
	// queryString.append(" as valoriSensori, ");
	// queryString.append(TipoSensoreSoglie.class.getName());
	// queryString.append(" as soglieSensori,");
	// queryString.append(LocationGeoSen.class.getName());
	// queryString.append(" as locationGeoSen,");
	// queryString.append(Position.class.getName());
	// queryString.append(" as positionGeo");
	// queryString.append(" WHERE valoriSensori.sensore.tipoSensore.id = soglieSensori.tipoSensore.id AND ");
	// queryString.append(" valoriSensori.sensore.tipoSensore.id = locationGeoSen.nodeSen.id AND ");
	// queryString.append(" locationGeoSen.position.id = positionGeo.id ");
	// queryString.append(" GROUP BY valoriSensori.sensore.nodeSen.id,valoriSensori.timestamp ");
	// Query query = em.createQuery(queryString.toString());
	//
	// return query.getResultList();
	//
	// } catch (RuntimeException re) {
	// EntityManagerHelper.log("getAllLatestMapsValue()", Level.SEVERE, re);
	// throw re;
	// }
	// }

	// public List<MapsBean> getAllLatestMapsValue () {
	//
	// EntityManagerHelper.log("getAllLatestMapsValue ()  ", Level.INFO, null);
	//
	//
	// try {
	// EntityManager em = EntityManagerHelper.getEntityManager();
	// StringBuilder queryString = new StringBuilder();
	// queryString.append("SELECT NEW "+MapsBean.class.getCanonicalName()+" (valoriSensori.sensore.id,valoriSensori.sensore.nodeSen.id,valoriSensori.sensore.nodeSen.description,"
	// +
	// " valoriSensori.timestamp,valoriSensori.sensore.description,"
	// +
	// "valoriSensori.value,tipoSoglie.livello,tipoSoglie.lowerBound,tipoSoglie.upperBound,positionGeo.lat,positionGeo.lng,tipoSoglie.pathIcon"+")");
	// queryString.append(" FROM ");
	// queryString.append(Valori.class.getName());
	// queryString.append(" as valoriSensori, ");
	// queryString.append(SensoreSoglie.class.getName());
	// queryString.append(" as soglieSensori,");
	// queryString.append(LocationGeoSen.class.getName());
	// queryString.append(" as locationGeoSen,");
	// queryString.append(Position.class.getName());
	// queryString.append(" as positionGeo, ");
	// queryString.append(TipoSoglie.class.getName());
	// queryString.append(" as tipoSoglie");
	//
	// queryString.append(" WHERE valoriSensori.sensore.id = soglieSensori.sensore.id AND ");
	// queryString.append(" valoriSensori.sensore.nodeSen.id = locationGeoSen.nodeSen.id AND ");
	// queryString.append(" locationGeoSen.position.id = positionGeo.id AND tipoSoglie.id=soglieSensori.tipoSoglie.id ");
	// queryString.append(" AND soglieSensori.sensore.id= valoriSensori.sensore.id ");
	// queryString.append(" AND  valoriSensori.value > tipoSoglie.lowerBound  AND valoriSensori.value <=tipoSoglie.upperBound");
	// queryString.append(" AND (valoriSensori.sensore.id,valoriSensori.timestamp) ");
	// queryString.append(
	// " IN(select valoriSensori2.sensore.id,MAX(valoriSensori2.timestamp) ");
	// queryString.append(" FROM ");
	// queryString.append(Valori.class.getName());
	// queryString.append(" as valoriSensori2");
	// queryString.append(" group by (valoriSensori2.sensore.id))");
	//
	//
	// Query query = em.createQuery(queryString.toString());
	//
	// return query.getResultList();
	//
	// } catch (RuntimeException re) {
	// EntityManagerHelper.log("getAllLatestMapsValue()", Level.SEVERE, re);
	// throw re;
	// }
	// }

	public List<MapsBean> getAllLatestMapsValue() {

		EntityManagerHelper.log("getAllLatestMapsValue ()  ", Level.INFO, null);

		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			StringBuilder queryString = new StringBuilder();
			queryString
					.append("SELECT NEW "
							+ MapsBean.class.getCanonicalName()
							+ " (q1PerMappa.id.sensoreId,q1PerMappa.id.nodeSenId,q1PerMappa.id.nodeSenDescription,"
							+ " q1PerMappa.id.valoriTimestamp,q1PerMappa.id.sensoreDescription,"
							+ "q1PerMappa.id.valoriValue,q1PerMappa.id.tipoSoglieLivello,q1PerMappa.id.tipoSoglieLowerBound,q1PerMappa.id.tipoSoglieUpperBound,q1PerMappa.id.positionLat,q1PerMappa.id.positionLng,q1PerMappa.id.path"
							+ ")");
			queryString.append(" FROM ");
			queryString.append(Q1fPerMappa.class.getName());
			queryString.append(" as q1PerMappa ");

			Query query = em.createQuery(queryString.toString());

			return query.getResultList();

		} catch (RuntimeException re) {
			EntityManagerHelper
					.log("getAllLatestMapsValue()", Level.SEVERE, re);
			throw re;
		}
	}

}
