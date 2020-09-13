public class Employee {
	private String name;
	private int ID;
	private TypeOfEmployee type;
	private double baseSalary;
	private double percentBonus = 0;
	private static int numberOfEmployees = 0;

	public Employee(String name, TypeOfEmployee type, double baseSalary, double percentBonus){
		numberOfEmployees++;
		this.ID = numberOfEmployees;
		this.name = name;
		this.type = type;
		this.baseSalary = baseSalary;
		this.percentBonus = percentBonus;
	}
	public Employee(String name, TypeOfEmployee type, double baseSalary){
		numberOfEmployees++;
		this.ID = numberOfEmployees;
		this.name = name;
		this.type = type;
		this.baseSalary = baseSalary;
	}
	public String getName(){
		return name;
	}
	public int getID(){
		return ID;
	}
	public TypeOfEmployee getType(){
		return type;
	}
	public double getBaseSalary(){
		return baseSalary;
	}
	public double getSalary(double currentIncome, double wantedIncome){
		if(type == TypeOfEmployee.Manager && (currentIncome > wantedIncome)){
			return (baseSalary + (baseSalary*(percentBonus/100)));
		}
		return baseSalary;
	}

}
