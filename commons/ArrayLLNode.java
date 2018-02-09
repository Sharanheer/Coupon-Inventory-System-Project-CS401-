package couponInventorySystem.commons;

public class ArrayLLNode {

	protected Coupon data;
	protected int link;
	
	public ArrayLLNode() {
		this.data = null;
		link = -1;
	}
	
	public ArrayLLNode(Coupon data) {
		this.data = data;
		link = -1;
	}

	public Coupon getData() {
		return data;
	}

	public void setData(Coupon data) {
		this.data = data;
	}

	public int getLink() {
		return link;
	}

	public void setLink(int link) {
		this.link = link;
	}
}
