package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import appConfigs.Constants;

public class RandomDataGenerator {

	/**
	 * Generates the Random Date value
	 * 
	 * @param Margin is passed to control random date as per Test Cases
	 * @return
	 */
	private String getRandomDate(int Margin) {
		// Given Date in String format
		String todayDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(todayDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Number of Days to add
		c.add(Calendar.DAY_OF_MONTH, generateRandomNumber() + Margin);

		// Date after adding the days to the given date
		return sdf.format(c.getTime()).toString();
	}

	// Return Random Number between 0-2
	private int generateRandomNumber() {
		return (int) Math.floor(Math.random() * Constants.RANDOM_NUMBER_GENERATOR_LIMIT);
	}

	private static final ObjectMapper mapper = new ObjectMapper();

	//Creating JSonObjectDynamically
	public String createJSon(String checkInDate, String chekOutDate) {
		ObjectNode root = mapper.createObjectNode();

		ObjectNode dates = mapper.createObjectNode();
		dates.put("checkin", checkInDate);
		dates.put("checkout", chekOutDate);
		root.set("dates", dates);

		root.put("destination", "paris");

		ArrayNode roomsArr = mapper.createArrayNode();
		int roomsCount = generateRandomNumber() + 1;
		for (int i = 1; i <= roomsCount; i++) {
			ObjectNode room = mapper.createObjectNode();
			int guestForRoom = generateRandomNumber() + 1;

			ArrayNode guestRooms = mapper.createArrayNode();
			for (int j = 0; j < guestForRoom; j++) {
				ObjectNode guest = mapper.createObjectNode();
				guest.put("type", "ADT");
				guestRooms.add(guest);
			}
			room.set("guest", guestRooms);
			roomsArr.add(room);
		}

		root.set("room", roomsArr);
		root.put("placeId", "ChIJD7fiBh9u5kcRYJSMaMOCCwQ");

		return root.toString();
	}

	public String getJSonBody(Integer checkInDayMargin, Integer checkOutDayMargin) {
		String checkInDate = getRandomDate((int) checkInDayMargin);
		String chekOutDate = getRandomDate((int) checkOutDayMargin);
		return createJSon(checkInDate, chekOutDate);
	}

	public String getJSonBodyWithEmptyCheckInDate(String checkInDate, Integer checkOutDayMargin) {
		String chekOutDate = getRandomDate((int) checkOutDayMargin);
		return createJSon(checkInDate, chekOutDate);
	}

	public String getJSonBodyWithEmptyCheckOutDate(Integer checkInDayMargin, String checkOutDate) {
		String checkInDate = getRandomDate((int) checkInDayMargin);
		return createJSon(checkInDate,checkOutDate);
	}

}
