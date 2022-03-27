package sklep.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PhotoUtil {
	
	public static byte[] readBytes(int productId) throws DBException, RecordNotFound {
		String dir = DBSettings.load().getProperty("photo_dir");
		String fileName = productId + ".jpg";
		
		try {
			return Files.readAllBytes(Paths.get(dir, fileName));
		} catch (IOException e) {
			throw new RecordNotFound("Cannot read photo for product id = " + productId);
		}
	}

}
