package appConfigs;

public interface Constants {

	// Constants used for generating random dates
	public static final Integer RANDOM_NUMBER_GENERATOR_LIMIT = 2;

	// This combination will generated proper checking and checkout date
	public static final Integer POSITIVE_TC_CHECKIN_DAY_MARGIN = 2;
	public static final Integer POSITIVE_TC_CHECKOUT_DAY_MARGIN = 5;

	/*
	 * This combination will generate invalid scenario as Checkout will be earlier
	 * than CheckIn date
	 */
	public static final Integer NEGATIVE_TC_CHECKIN_DAY_MARGIN = 5;
	public static final Integer NEGATIVE_TC_CHECKOUT_DAY_MARGIN = 2;

	public static final String HOTEL_PLACE = "Paris";
	public static final String PLACE_ID = "ChIJD7fiBh9u5kcRYJSMaMOCCwQ";
	
	
	public static final Integer SUCCESS_STATUS_CODE_200 = 200;
	public static final Integer FAILED_BAD_REQUEST_STATUS_CODE_400 = 400;

	

}
