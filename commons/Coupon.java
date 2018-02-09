package couponInventorySystem.commons;

public class Coupon {

	protected String coupon_site;
	protected String product_name;
	protected double product_price;
	protected double discount_rate;
	protected int exp_period;
	protected String status_of_coupon;
	
	//Default Constructor//
	public Coupon() {}
	
	//Parametric Constructor//
	public Coupon(String coupon_site, String product_name, double product_price, double discount_rate, int exp_period,
			String status_of_coupon) {
		
		this.coupon_site = coupon_site;
		this.product_name = product_name;
		this.product_price = product_price;
		this.discount_rate = discount_rate;
		this.exp_period = exp_period;
		this.status_of_coupon = status_of_coupon;
	}
	// Getter and Setter//
	public String getCoupon_site() {
		return coupon_site;
	}

	public void setCoupon_site(String coupon_site) {
		this.coupon_site = coupon_site;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public double getProduct_price() {
		return product_price;
	}

	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}

	public double getDiscount_rate() {
		return discount_rate;
	}

	public void setDiscount_rate(double discount_rate) {
		this.discount_rate = discount_rate;
	}

	public int getExp_period() {
		return exp_period;
	}

	public void setExp_period(int exp_period) {
		this.exp_period = exp_period;
	}

	public String getStatus_of_coupon() {
		return status_of_coupon;
	}

	public void setStatus_of_coupon(String status_of_coupon) {
		this.status_of_coupon = status_of_coupon;
	}
	
}
