package fr.d2factory.librayapp.utility;

public abstract class ParamLibrary {

	private static final int FIRST_YEAR_FREE = 15;
	private static final int MEMBRE_PRICE_BEFORE_LATE = 10;
	private static final int RESIDENT_PRICE_AFTER_LATE = 20;
	private static final int STUDENT_PRICE_AFTER_LATE = 15;
	private static final int DAYS_BEFORE_LATE_STUDENT = 30;
	private static final int DAYS_BEFORE_LATE_RESIDENT = 60;

	public static int getFirstYearFree() {
		return FIRST_YEAR_FREE;
	}

	public static int getMembrePriceBeforeLate() {
		return MEMBRE_PRICE_BEFORE_LATE;
	}

	public static int getResidentPriceAfterLate() {
		return RESIDENT_PRICE_AFTER_LATE;
	}

	public static int getStudentPriceAfterLate() {
		return STUDENT_PRICE_AFTER_LATE;
	}

	public static int getDaysBeforeLateStudent() {
		return DAYS_BEFORE_LATE_STUDENT;
	}

	public static int getDaysBeforeLateResident() {
		return DAYS_BEFORE_LATE_RESIDENT;
	}

}
