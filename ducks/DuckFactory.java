package ducksim.ducks;

import java.util.Observable;

import ducksim.Blings.CrossBling;
import ducksim.Blings.MoonBling;
import ducksim.Blings.StarBling;
import ducksim.goose.Goose;
import ducksim.goose.GooseAdapter;

// Observable

public class DuckFactory extends Observable {
	
	private static DuckFactory duckFactory = new DuckFactory();
	
	public Duck createDuck(String duckType, int starCount, int moonCount, int crossCount) {
		Duck duck = null;
		
		if (duckType == "Decoy") duck = new DecoyDuck();
		else if (duckType == "Mallard") duck = new MallardDuck();
		else if (duckType == "Redhead") duck = new RedheadDuck();
		else if (duckType == "Rubber") duck = new RubberDuck();
		else if (duckType == "Goose") duck = new GooseAdapter(new Goose());
		
		duck = addBling(duck, starCount, moonCount, crossCount);
		
		notifyDSWC();
		
		return duck;
	}
	
	private Duck addBling(Duck duck, int starCount, int moonCount, int crossCount) {
		while (starCount > 0) {
			duck = new StarBling(duck);
			starCount--;
		}
		while (moonCount > 0) {
			duck = new MoonBling(duck);
			moonCount--;
		}
		while (crossCount > 0) {
			duck = new CrossBling(duck);
			crossCount--;
		}
		return duck;
	}
	
	public static DuckFactory getInstance() {
		return duckFactory;
	}
	
	private void notifyDSWC() {
		this.setChanged();
		this.notifyObservers();
		this.clearChanged();
	}
	
}
