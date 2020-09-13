import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Data {
	private List<PrintedProduct> productDataList;

	public Data(){
		this.productDataList = new ArrayList<>();
	}
	public void addProduct(PrintedProduct product){
		productDataList.add(product);
	}
	public void writeData(PrintingHouse printingHouse) {
		File file = new File("D:\\ \\School\\Programming\\Java\\Java tasks\\Project-1\\data.txt");
		try {
			FileWriter fileWriter = new FileWriter(file.getName());
			fileWriter.write("Printed products:" + System.lineSeparator());
			for (PrintedProduct product : this.productDataList) {
				fileWriter.write(product.toString() + System.lineSeparator());
			}

			fileWriter.write("Salaries: " + printingHouse.getCurrentCostsSalaries() + System.lineSeparator());
			fileWriter.write("Product costs: " + printingHouse.getCurrentCostsProducts() + System.lineSeparator());
			fileWriter.write("Total costs: " + printingHouse.getCurrentCosts() + System.lineSeparator());
			fileWriter.write("Total income: " + printingHouse.getIncome(20, 10) + System.lineSeparator());
			fileWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	public List<String> readData(String fileName) {
		List<String> dataLines = new ArrayList<>();
		try (FileReader fileReader = new FileReader(fileName)) {
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				dataLines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataLines;
	}
}
