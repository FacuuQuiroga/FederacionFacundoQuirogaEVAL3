package entidades;

public class Tiempo {
	private int horas;
	private int min;
	private int seg;
	private int centesimas;

	public Tiempo() {
	}

	public Tiempo(int horas, int min, int seg, int cent) {
		this.horas = horas;
		this.min = min;
		this.seg = seg;
		this.centesimas = cent;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getSeg() {
		return seg;
	}

	public void setSeg(int seg) {
		this.seg = seg;
	}

	public int getCentesimas() {
		return centesimas;
	}

	public void setCentesimas(int centesimas) {
		this.centesimas = centesimas;
	}

	@Override
	public String toString() {
		return "Tiempo: " + horas + ":" + min + ":" + seg + "," + centesimas + "]";
	}

}
