package it.icarcnr.integration.dao.generated;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

/**
 * AbstractHoltwinters entity provides the base persistence definition of the
 * Holtwinters entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractHoltwinters implements java.io.Serializable {

	// Fields

	private HoltwintersId id;
	private Double sampleValue;
	private Double cleanedValue;
	private Double estimatedValue;
	private Double at;
	private Double ft;
	private Double st;
	private Double cleanSigmat;
	private Double lowerBound;
	private Double upperBound;
	private Double bandSigmat;

	// Constructors

	/** default constructor */
	public AbstractHoltwinters() {
	}

	/** minimal constructor */
	public AbstractHoltwinters(HoltwintersId id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractHoltwinters(HoltwintersId id, Double sampleValue,
			Double cleanedValue, Double estimatedValue, Double at, Double ft,
			Double st, Double cleanSigmat, Double lowerBound,
			Double upperBound, Double bandSigmat) {
		this.id = id;
		this.sampleValue = sampleValue;
		this.cleanedValue = cleanedValue;
		this.estimatedValue = estimatedValue;
		this.at = at;
		this.ft = ft;
		this.st = st;
		this.cleanSigmat = cleanSigmat;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.bandSigmat = bandSigmat;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "idServiceOperation", column = @Column(name = "ID_ServiceOperation", nullable = false)),
			@AttributeOverride(name = "date", column = @Column(name = "date", nullable = false, length = 19)) })
	public HoltwintersId getId() {
		return this.id;
	}

	public void setId(HoltwintersId id) {
		this.id = id;
	}

	@Column(name = "sampleValue", precision = 22, scale = 0)
	public Double getSampleValue() {
		return this.sampleValue;
	}

	public void setSampleValue(Double sampleValue) {
		this.sampleValue = sampleValue;
	}

	@Column(name = "cleanedValue", precision = 22, scale = 0)
	public Double getCleanedValue() {
		return this.cleanedValue;
	}

	public void setCleanedValue(Double cleanedValue) {
		this.cleanedValue = cleanedValue;
	}

	@Column(name = "estimatedValue", precision = 22, scale = 0)
	public Double getEstimatedValue() {
		return this.estimatedValue;
	}

	public void setEstimatedValue(Double estimatedValue) {
		this.estimatedValue = estimatedValue;
	}

	@Column(name = "at", precision = 22, scale = 0)
	public Double getAt() {
		return this.at;
	}

	public void setAt(Double at) {
		this.at = at;
	}

	@Column(name = "ft", precision = 22, scale = 0)
	public Double getFt() {
		return this.ft;
	}

	public void setFt(Double ft) {
		this.ft = ft;
	}

	@Column(name = "st", precision = 22, scale = 0)
	public Double getSt() {
		return this.st;
	}

	public void setSt(Double st) {
		this.st = st;
	}

	@Column(name = "clean_sigmat", precision = 22, scale = 0)
	public Double getCleanSigmat() {
		return this.cleanSigmat;
	}

	public void setCleanSigmat(Double cleanSigmat) {
		this.cleanSigmat = cleanSigmat;
	}

	@Column(name = "lower_bound", precision = 22, scale = 0)
	public Double getLowerBound() {
		return this.lowerBound;
	}

	public void setLowerBound(Double lowerBound) {
		this.lowerBound = lowerBound;
	}

	@Column(name = "upper_bound", precision = 22, scale = 0)
	public Double getUpperBound() {
		return this.upperBound;
	}

	public void setUpperBound(Double upperBound) {
		this.upperBound = upperBound;
	}

	@Column(name = "band_sigmat", precision = 22, scale = 0)
	public Double getBandSigmat() {
		return this.bandSigmat;
	}

	public void setBandSigmat(Double bandSigmat) {
		this.bandSigmat = bandSigmat;
	}

}