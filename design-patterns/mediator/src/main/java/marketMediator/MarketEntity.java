package marketMediator;

public abstract class MarketEntity {
	//Colleague

	MarketServiceOrganizer organizer; //riferimento al mediator

	public MarketEntity(MarketServiceOrganizer organizer) {
		this.organizer = organizer;
	}

	/*
	 * utilizzato dai Colleagues per notificare al Mediator i propri cambiamenti di
	 * stato
	 */
	public void changed() {
		organizer.entityChanged(this);
	}

}
