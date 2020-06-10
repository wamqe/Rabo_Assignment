package employee.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

public class RestUtils {
	public static String formatPrint(String jsonData) {
		ObjectMapper mapper = new ObjectMapper();
		String formattedJson = "";

		try {
			Object json = mapper.readValue(jsonData, Object.class);
			formattedJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return formattedJson;
	}

	public static String generateEmail(String domain, int length) {
		return RandomStringUtils.random(length, "abcdefghijklmnopqrstuvwxyz") + "@" + domain;
	}

	public static long generateRandomId() {
		  Date today = new Date();
		  SimpleDateFormat df = new SimpleDateFormat("ddHHmmss");
		  long tvalue = Integer.valueOf(df.format(today).toString());
		  return tvalue;
		 }

	public static int generateRandomBetween(int min,int max){
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public static String generatePhoneNumber(){
		Faker faker = new Faker();
		return faker.number().numberBetween(100,999)+"-"+faker.number().digits(3)+"-"+faker.number().digits(4);
	}


}
