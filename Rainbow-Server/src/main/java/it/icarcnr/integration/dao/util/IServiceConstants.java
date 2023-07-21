package it.icarcnr.integration.dao.util;

public interface IServiceConstants {

	String ON_TIME = "ontime";
	String STEP_BY_STEP = "stepbystep";
	String RANGE = "range";
	String ABOVE = "above";
	String BELOW = "below";

	public enum Status {
		SUSPENDED (1, "suspended"),
		NORMAL (2, "normal"),
		MAJOR   (3, "major"),
		CRITICAL   (4, "critical");

		private final int severity;   
		private final String severityName;
		Status(int severity, String severityName) {
			this.severity = severity;
			this.severityName = severityName;
		}

		public int getSeverity() {
			return severity;
		}
		public String getSeverityName() {
			return severityName;
		}

		public static Status fromInt(int num) {
			switch (num) {
			case 1: { return SUSPENDED; }
			case 2: { return NORMAL; }
			case 3: { return MAJOR; }
			case 4: { return CRITICAL; }
			default: { return null; }
			}
		}

		/**
		 * @param status
		 * @return
		 */
		public static Status fromInteger(Integer status) {
			if(status == null){
				return null;
			}
			switch (status) {
			case 1: { return SUSPENDED; }
			case 2: { return NORMAL; }
			case 3: { return MAJOR; }
			case 4: { return CRITICAL; }
			default: { return null; }
			}
		}
	}

	String CRITICAL = "critical";
	String MAJOR = "major";
	String NORMAL = "normal";
	String SUSPENDED = "suspended";
}
