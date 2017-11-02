package shopping.service;

import java.util.List;

import shopping.model.CartItem;

public interface CartItemService {

	public void addCart(CartItem cartItem);
	public void removeCart(CartItem cartItem);
	public void removeAllCart(CartItem cart);
	public List<CartItem> list();
}
