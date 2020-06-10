package employee.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
static Properties prop = new Properties();
	
	public static void readProperty(String configFile){
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(new File(configFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getKey(String key){		
		String value = prop.getProperty(key);
		return value;
	}

}
