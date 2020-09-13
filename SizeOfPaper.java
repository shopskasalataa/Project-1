public enum SizeOfPaper {
	A1(0.1), A2(0.2), A3(0.3), A4(0.4), A5(0.5);
	private double price;
	private SizeOfPaper(double price){
		this.price = price;
	}
	public double getPrice(){
		return price;
	}
}
