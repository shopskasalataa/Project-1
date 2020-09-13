public class NotLoadedPrinterException extends Exception{
	private int maxCapacity;

	public NotLoadedPrinterException(int maxCapacity){
		this.maxCapacity = maxCapacity;
	}
	public int getMaxCapacity(){
		return maxCapacity;
	}

	@Override
	public String toString(){
		return "The printer is overloaded, maximum capacity is: " + maxCapacity;
	}
}

