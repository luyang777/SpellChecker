 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
 
/**
 * @author Crunchify.com
 * 
 */
 
public class Config {
	String DPath = "";
	InputStream inputStream;
 
	public String getPropValues(String string) throws IOException {
 
		try {
			Properties prop = new Properties();
			String propFileName = "Config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			Date time = new Date(System.currentTimeMillis());
 
			// get the property value and print it out
			 DPath = prop.getProperty("Path");
					
			System.out.println("\nProgram Ran on " + time + " by Path=" + DPath);
			//System.out.println(DPath);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		
		return DPath;
	}
}