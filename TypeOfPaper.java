public enum TypeOfPaper {
	Normal(0.5), Glossy(1.0), Newspaper(0.3);
	private double price;
	private TypeOfPaper(double price){
		this.price = price;
	}
	public double getPrice() {
		return price;
	}
}
