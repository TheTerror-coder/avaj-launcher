package avajlauncher.main;

public class Coordinates {
	
	private int	longitude;
	private int	latitude;
	private int	height;

	Coordinates(int p_longitude, int p_latitude, int p_height) {
		this.longitude = p_longitude;
		this.latitude = p_latitude;
		this.height = p_height;
	}

	public int	getLongitude() {
		return (this.longitude);
	}
	public int	getLatitude() {
		return (this.latitude);
	}
	public int	getHeight() {
		return (this.height);
	}

	public void	incrementLongitude(int p_n) {
		if (p_n > 0) {
			this.longitude += p_n;
		}
	}
	public void	incrementLatitude(int p_n) {
		if (p_n > 0) {
			this.latitude += p_n;
		}
	}
	public void	incrementHeight(int p_n) {
		if (p_n > 0) {
			this.height += p_n;
			if (this.height > 100)
				this.height = 100;
		}
	}

	public void	decrementLongitude(int p_n) {
		if (p_n > 0) {
			this.longitude -= p_n;
		}
	}
	public void	decrementLatitude(int p_n) {
		if (p_n > 0) {
			this.latitude -= p_n;
		}
	}
	public void	decrementHeight(int p_n) {
		if (p_n > 0) {
			this.height -= p_n;
			if (this.height < 0)
				this.height = 0;
		}
	}
}
