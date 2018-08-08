/**
 *Copyright (c) 2014 Qburst Technologies, Inc. All Rights Reserved.
 */
package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileRead {
	/**
	 * Method Name: getProperty() Description: This function returns a property
	 * value when the property name is passed as parameter from
	 * config.properties file
	 */
	public String getProperty(String propertyName) {
		Properties prop = new Properties();
		String propertyValue = null;
		try {
			prop.load(new FileInputStream("config.properties"));
			propertyValue = prop.getProperty(propertyName);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return propertyValue;
	}

	/**
	 * Method Name: setProperty() Description: This function is used to set the
	 * property value for a property inside config.properties file
	 */
	public void setProperty(String propertyName, String propertyValue)
			throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.load(new FileInputStream("config.properties"));
		prop.setProperty(propertyName, propertyValue);
		prop.store(new FileOutputStream("config.properties"), null);

	}
}