package marketMediator;

public class MediatorExample {

	public static void main(String[] args) {

		Market market = new Market();
		

		market.showStatus();
		
		System.out.println("A customer arrives to queue 1");
		market.customerArriveToQ1();
		
		market.showStatus();
		
		System.out.println("A customer arrives to queue 1");
		market.customerArriveToQ1();
		
		market.showStatus();
		
		System.out.println("A customer arrives to queue 1");
		market.customerArriveToQ1();
		
		market.showStatus();
		
		System.out.println("A customer arrives to queue 2");
		market.customerArriveToQ2();
		
		market.showStatus();
		
		System.out.println("End of service cassa 1");
		market.endServiceCassa1();
		
		market.showStatus();
		
		System.out.println("End of service cassa 2");
		market.endServiceCassa2();
		
		market.showStatus();
		
		System.out.println("End of service cassa 2");
		market.endServiceCassa2();
		
		market.showStatus();

	}

}
