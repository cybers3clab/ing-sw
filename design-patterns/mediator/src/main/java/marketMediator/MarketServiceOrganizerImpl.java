package marketMediator;

public class MarketServiceOrganizerImpl implements MarketServiceOrganizer {
	// ConcreteMediator

	private Queue queue1, queue2;
	private Cashier cashier1, cashier2;

	public void createEntities() {
		queue1 = new Queue(this);
		queue2 = new Queue(this);
		cashier1 = new Cashier(this);
		cashier2 = new Cashier(this);
	}

	public Queue getQueue1() {
		return queue1;
	}

	public Queue getQueue2() {
		return queue2;
	}

	public Cashier getCashier1() {
		return cashier1;
	}

	public Cashier getCashier2() {
		return cashier2;
	}

	@Override
	public void entityChanged(MarketEntity entity) {
		if( entity == cashier1 ) {
			if( queue1.getSize() > 0 )
				cashier1.takeClient( queue1 );
			else
				if( queue2.getSize() > 0 ) 
					cashier1.takeClient( queue2 );
		}
		else if( entity == cashier2 ) { 
			if( queue2.getSize() > 0 ) 
				cashier2.takeClient( queue2 );
			else
				if( queue1.getSize() > 0 )
					cashier2.takeClient( queue1 ); 
		}
		else if( entity == queue1 ) {
			if( cashier1.isFree() )
				cashier1.takeClient( queue1 ); 
			else
				if( cashier2.isFree() ) 
					cashier2.takeClient( queue1 );
		}
		else if( entity == queue2 ) { 
			if( cashier2.isFree() ) 
				cashier2.takeClient( queue2 );
			else
				if( cashier1.isFree() )
					cashier1.takeClient( queue2 );
		}
	}

}
