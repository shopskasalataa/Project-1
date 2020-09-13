import java.util.ArrayList;
import java.util.List;

public class PrintingHouse {
	private static final int PRODUCTS_TO_CREATE = 10_0;
	private String name;
	private List<PrintedProduct> productList;
	private List<Employee> employeeList;
	private double income = 0;
	private double wantedIncome;
	private List<PrintingMachine> printersList;
	private List<Runnable> runnables = new ArrayList<Runnable>();
	private Data data = new Data();

	public PrintingHouse(String name, double wantedIncome){
		productList = new ArrayList<PrintedProduct>();
		employeeList = new ArrayList<Employee>();
		printersList = new ArrayList<PrintingMachine>();
	}
	public Data getData(){
		return data;
	}
	public void addPrinter(PrintingMachine printer){
		if(printersList.contains(printer) == false){
			printersList.add(printer);
		}
	}
	public void addPrintedProduct(PrintedProduct product){
		if(productList.contains(product) == false){
			productList.add(product);
		}
	}
	public void recruitEmployee(Employee employee){
		if(employeeList.contains(employee) == false){
			employeeList.add(employee);
		}
	}
	public void fireEmployee(Employee employee){
		if(this.employeeList.contains(employee) == true){
			this.employeeList.remove(employee);
		}
	}
	public void removePrintedProduct(PrintedProduct product){
		if(this.productList.contains(product) == true){
			this.productList.remove(product);
		}
	}
	public double getCurrentCostsProducts(){
		double cost = 0;
		for(PrintedProduct product : productList){
			cost = cost + (product.getCost());
		}
		return cost;
	}


	public double getIncome(int minimumNumber, double percent){
		income = 0;
		for(PrintedProduct product : productList){
			if(minimumNumber < product.getQuantity()){
				income = income + ((1.5 - percent) * product.getCost());
			}
			else {
				income = income + (1.5 * product.getCost());
			}
		}
		return income;
	}
	public double getCurrentCostsSalaries(){
		double salaries = 0;
		for(Employee employee : employeeList){
			salaries = salaries + employee.getSalary(this.getIncome(5, 5), this.getIncome(5, 5) - 1);
		}
		return salaries;
	}
	public double getCurrentCosts(){
		return getCurrentCostsProducts() + getCurrentCostsSalaries();
	}
	public void addToData(){
		for(PrintedProduct printedProduct : productList){
			data.addProduct(printedProduct);
		}
	}
	public void startPrinting() {
		for(PrintingMachine printer : printersList){
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					for(PrintedProduct printedProduct : productList){
						synchronized (printedProduct) {
							if(printedProduct.getIsPrinted() == false){
								try {
									printedProduct.printing(printer);
								} catch (NotLoadedPrinterException e) {
									continue;
								}
							}
						}
					}
				}
			};

			this.runnables .add(runnable);
		}
	}

	private void start() {
		for (Runnable runnable : runnables) {
			new Thread(runnable).start();
		}
	}

	public static void main(String[] args) {
		PrintingHouse house = new PrintingHouse("asdasd", 234234);
		PrintingMachine printer1 = new PrintingMachine(
				100, TypeOfPrinter.Colored,  33);
		house.addPrinter(printer1);
		PrintingMachine printer2 = new PrintingMachine(
				140, TypeOfPrinter.Colored,  43);
		house.addPrinter(printer2);

		house.startPrinting();

		for (int i = 0; i < PRODUCTS_TO_CREATE; i++) {
			house.addPrintedProduct(
					new PrintedProduct(
							TypeOfProduct.Newspaper, "Prod " + i,
							10, SizeOfPaper.A4, TypeOfPaper.Newspaper));
		}

		Employee gosho = new Employee("Gosho", TypeOfEmployee.Manager, 100, 5);
		Employee sasho = new Employee("Sasho", TypeOfEmployee.Operator, 100);
		house.recruitEmployee(gosho);
		house.recruitEmployee(sasho);
		house.start();
		house.addToData();

		Data newData = house.getData();
		newData.writeData(house);
		List<String> dataList = newData.readData("data.txt");
	}
}