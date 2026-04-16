package marketMediator;

public class Market {
	/*
	 * Agisce come generatore di eventi esterno, che al suo interno crea una istanza
	 * del Mediator e che ottiene da esso riferimenti a code e server
	 */

	private Queue queue1, queue2;
	private Cashier cashier1, cashier2;

	public Market() {
		MarketServiceOrganizerImpl marketServiceOrganizer = new MarketServiceOrganizerImpl();

		marketServiceOrganizer.createEntities();
		queue1 = marketServiceOrganizer.getQueue1();
		queue2 = marketServiceOrganizer.getQueue2();
		cashier1 = marketServiceOrganizer.getCashier1();
		cashier2 = marketServiceOrganizer.getCashier2();
		cashier1.initService(queue1);
		cashier2.initService(queue2);
	}

	public void customerArriveToQ1() {
		queue1.enqueue();
	}

	public void customerArriveToQ2() {
		queue2.enqueue();
	}

	public void endServiceCassa1() {
		cashier1.endService();
	}

	public void endServiceCassa2() {
		cashier2.endService();
	}

	public void showStatus() {

		System.out.println("cassa 1 status: " + (cashier1.isFree() ? "FREE" : "BUSY"));
		System.out.println("cassa 2 status: " + (cashier2.isFree() ? "FREE" : "BUSY"));
		System.out.println("Queue 1 size: " + queue1.getSize());
		System.out.println("Queue 2 size: " + queue2.getSize());
		System.out.println("----------------------------------");

	}

}
