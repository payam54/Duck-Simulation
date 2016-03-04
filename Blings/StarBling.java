package ducksim.Blings;

import ducksim.ducks.Duck;

public class StarBling extends Bling {

	public StarBling(Duck duck) {
		super(duck);
	}

	@Override
	public String display() {
		return duck.display() + ":*";
	}

}
