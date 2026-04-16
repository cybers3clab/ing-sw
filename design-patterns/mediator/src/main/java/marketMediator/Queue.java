package marketMediator;

public class Queue extends MarketEntity {//MarketEntity � il Colleague
	//ConcreteColleague

	private int size;

	public Queue(MarketServiceOrganizer organizer) {
		super(organizer);
	}

	public void enqueue() {
		size++;
		changed();
	}

	public void dequeue() {
		if (size > 0)
			size--;
	}

	public int getSize() {
		return size;

	}
}
