package cn.shoppingcart.model;

public class Cart extends Product {
	
	//when we will add the product in the cart we will increase the quantity
	private int quantity;

	public Cart() {
	
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
