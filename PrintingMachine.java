public class PrintingMachine {
	private int maxCapacity;
	private int capacity;
	private TypeOfPrinter type;
	private int speed;
	private boolean isLoaded = false;

	public PrintingMachine(int maxCapacity, TypeOfPrinter type, int speed){
		this.maxCapacity = maxCapacity;
		this.capacity = maxCapacity;
		this.type = type;
		this.speed = speed;
		this.isLoaded = true;
	}

	public int getMaxCapacity(){
		return maxCapacity;
	}
	public int getCurrentCapacity(){
		return capacity;
	}
	public TypeOfPrinter getType(){
		return type;
	}
	public int getSpeed(){
		return speed;
	}
	public boolean getIsLoaded(){
		return isLoaded;
	}
	public void setCapacity(int capacityWanted){
		this.capacity = this.capacity - capacityWanted;
		if(this.capacity > 0) this.isLoaded = true;
		else this.isLoaded = false;
	}
}
