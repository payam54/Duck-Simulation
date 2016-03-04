package ducksim.Blings;

import ducksim.ducks.Duck;

public class CrossBling extends Bling {

	public CrossBling(Duck duck) {
		super(duck);
	}

	@Override
	public String display() {
		return duck.display() + ":+";
	}

}
