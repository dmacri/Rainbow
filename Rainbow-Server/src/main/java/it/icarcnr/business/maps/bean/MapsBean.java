package it.icarcnr.business.maps.bean;
		
		import java.util.Date;
		
		public class MapsBean {
			
			
		  private Integer idSensore;
		  private Integer idNodeSensore;
		  private String  descrizioneNode;
		  private Date dateSampling;
		  private String nameDescription;
		  private Double value;
		  private Integer livello;
		  private Double lowerBound;
		  private Double upperBound;
		  private Double Lat;
		  private Double Lng;
		  private String colorLevel;
		  
		  
		 
		  
		  public MapsBean() {
		
		    }
		
		public MapsBean(Integer idSensore, Integer idNodeSensore,
				String descrizioneNode, Date dateSampling,
				String nameDescription, Double value, Integer livello,
				Double lowerBound, Double upperBound, Double lat, Double lng,
				String colorLevel) {
			super();
			this.idSensore = idSensore;
			this.idNodeSensore = idNodeSensore;
			this.descrizioneNode = descrizioneNode;
			this.dateSampling = dateSampling;
			this.nameDescription = nameDescription;
			this.value = value;
			this.livello = livello;
			this.lowerBound = lowerBound;
			this.upperBound = upperBound;
			Lat = lat;
			Lng = lng;
			this.colorLevel = colorLevel;
		}




		public Integer getIdSensore() {
			return idSensore;
		}
		
		
		
		
		public void setIdSensore(Integer idSensore) {
			this.idSensore = idSensore;
		}
		
		
		
		
		public Integer getIdNodeSensore() {
			return idNodeSensore;
		}
		
		
		
		
		public void setIdNodeSensore(Integer idNodeSensore) {
			this.idNodeSensore = idNodeSensore;
		}
		
		
		
		
		public String getDescrizioneNode() {
			return descrizioneNode;
		}
		
		
		
		
		public void setDescrizioneNode(String descrizioneNode) {
			this.descrizioneNode = descrizioneNode;
		}
		
		
		
		
		public Date getDateSampling() {
			return dateSampling;
		}
		
		
		
		
		public void setDateSampling(Date dateSampling) {
			this.dateSampling = dateSampling;
		}
		
		
		
		
		public String getNameDescription() {
			return nameDescription;
		}
		
		
		
		
		public void setNameDescription(String nameDescription) {
			this.nameDescription = nameDescription;
		}
		
		
		
		
		public Double getValue() {
			return value;
		}
		
		
		
		
		public void setValue(Double value) {
			this.value = value;
		}
		
		
		
		
		public Integer getLivello() {
			return livello;
		}
		
		
		
		
		public void setLivello(Integer livello) {
			this.livello = livello;
		}
		
		
		
		
		public Double getLowerBound() {
			return lowerBound;
		}
		
		
		
		
		public void setLowerBound(Double lowerBound) {
			this.lowerBound = lowerBound;
		}
		
		
		
		
		public Double getUpperBound() {
			return upperBound;
		}
		
		
		
		
		public void setUpperBound(Double upperBound) {
			this.upperBound = upperBound;
		}
		
		
		
		
		public Double getLat() {
			return Lat;
		}
		
		
		
		
		public void setLat(Double lat) {
			Lat = lat;
		}
		
		
		
		
		public Double getLng() {
			return Lng;
		}
		
		
		
		
		public void setLng(Double lng) {
			Lng = lng;
		}
		
		
		
		
		public String getColorLevel() {
			return colorLevel;
		}
		
		
		
		
		public void setColorLevel(String colorLevel) {
			this.colorLevel = colorLevel;
		}
		
		 
		
		
		  
		  
		
		}