package resources;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
           
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestDataFromJson {

	public List<HashMap<String, String>> getDataFromJson(String filePath) throws IOException {
		// read json data and convert to String
		File file = new File(filePath);
		String data = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

		// convert string to hashmap
		ObjectMapper objMap = new ObjectMapper();
		// class ObjectMapper provides functionality for reading and writing JSON
		List<HashMap<String, String>> tData = objMap.readValue(data,
				new TypeReference<List<HashMap<String, String>>>() {

				});
		return tData;
	}
}
