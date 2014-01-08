package be.vdab.web;


import be.vdab.constraints.Postcode;

public class VanTotPostcodeForm {
	@Postcode
	private int vanpostcode;
	@Postcode
	private int totpostcode;

	VanTotPostcodeForm() { // default constructor (package visibility)
	}

	// constructor om command object te initialiseren vanuit Controller:
	VanTotPostcodeForm(int vanpostcode, int totpostcode) {
		this.vanpostcode = vanpostcode;
		this.totpostcode = totpostcode;
	}

	public int getVanpostcode() {
		return vanpostcode;
	}

	public int getTotpostcode() {
		return totpostcode;
	}

	public void setVanpostcode(int vanpostcode) {
		this.vanpostcode = vanpostcode;
	}

	public void setTotpostcode(int totpostcode) {
		this.totpostcode = totpostcode;
	}
	
	public boolean isValid() {
		if (vanpostcode == 0 || totpostcode == 0) {
		return false;
		}
		return vanpostcode <= totpostcode;
		}

	@Override
	public String toString() {
		return String.format("%s-%s", vanpostcode, totpostcode);
	}
}
