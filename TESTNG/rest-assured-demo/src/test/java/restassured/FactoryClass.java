package restassured;


import java.util.List;

import org.testng.annotations.Factory;

public class FactoryClass {
  @Factory
	public Object[] factoryMethod() throws Exception {
		String path = "./src/files/Tasks.csv";
    ContentReader reader = new ContentReader();
		List<String[]> lines = reader.readAllLines(path);
		lines.remove(0);
		Object[] data = new Object[lines.size()];
		int index = 0;
		for(String[] line : lines) {
			String Route = line[0];
      String Status = line[1];
			data[index] = new TestClassForFactory(Route,Status);
			index++;
		}
		return data;
	}
}
