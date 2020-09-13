public class PrintedProduct {
	private TypeOfProduct typeOfProduct;
	private String title;
	private int numberOrder;
	private static int finalNumberOrder;
 	private SizeOfPaper size;
 	private TypeOfPaper typeOfPaper;
	private boolean isPrinted;

 	public PrintedProduct(TypeOfProduct typeOfProduct, String title, int numberOrder, SizeOfPaper size, TypeOfPaper typeOfPaper){
 		this.typeOfProduct = typeOfProduct;
 		this.title = title;
 		this.numberOrder = numberOrder;
 		finalNumberOrder = numberOrder;
 		this.size = size;
 		this.typeOfPaper = typeOfPaper;
 		this.isPrinted = false;
	}

	public TypeOfProduct getTypeOfProduct() {
		return typeOfProduct;
	}
	public String getTitle(){
 		return title;
	}
	public int getQuantity() {
		return finalNumberOrder;
	}
	public SizeOfPaper getSize(){
 		return size;
	}
	public TypeOfPaper getTypeOfPaper(){
 		return typeOfPaper;
	}
	public Boolean getIsPrinted(){
 		return isPrinted;
	}
	public double getCost(){
 		return numberOrder * (size.getPrice() + typeOfPaper.getPrice());
	}
	public synchronized void printing(PrintingMachine printer) throws NotLoadedPrinterException{ //точно това ли ми е аргументът?
		//System.out.println(Thread.currentThread().getName() + " is printing: " + title + " time: " + System.currentTimeMillis() + " cap left: " + numberOrder + " isPrinted ");
		try{
 			int milisPerPage = 60*1000/ printer.getSpeed();
 			Thread.sleep(milisPerPage);
		}catch(InterruptedException e){
 			throw new RuntimeException(e);
		}
		if(printer.getIsLoaded() == true) {
			if (printer.getCurrentCapacity() >= this.numberOrder) {
				if(this.isPrinted == false) {
					while(this.numberOrder >= printer.getSpeed()){
						if(printer.getCurrentCapacity() <= this.numberOrder) Thread.currentThread().interrupt();
						printer.setCapacity(printer.getSpeed());
						this.numberOrder -= printer.getSpeed();
					}
					this.numberOrder = 0;
					this.isPrinted = true;
					System.out.println(Thread.currentThread().getName() + " is printing: " + title);
				}
			}
		}else{
			throw new NotLoadedPrinterException(printer.getCurrentCapacity());
		}
 	}
 	@Override
	public String toString(){
 		return "Type of product: " + typeOfProduct + "; Title: " + title + "; Quantity: " + finalNumberOrder + "; Size: " + size + "; Type of paper: " + typeOfPaper + "; Cost: " + getCost() + "; Price: " + 1.5*getCost();
	}
}
