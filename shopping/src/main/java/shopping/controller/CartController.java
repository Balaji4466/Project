package shopping.controller;

import java.security.Principal;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import shopping.dao.CartDAO;
import shopping.dao.CartItemDAO;
import shopping.dao.ProductDAO;
import shopping.dao.UserDAO;
import shopping.model.Cart;
import shopping.model.CartItem;
import shopping.model.Product;
import shopping.model.User;

@Controller
public class CartController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private CartItemDAO cartItemDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CartDAO cartDAO;
	
	@RequestMapping(value = ("/addtocart"), method = RequestMethod.GET)
	public ModelAndView addtocart(@RequestParam("productPid") Integer pid,Principal principal)
	{
		User user= userDAO.getuser(principal.getName());
		Cart cart= user.getCart();
		Product product = productDAO.getproduct(pid);
		List<CartItem>cartItems= cart.getCartItem();
		
		  for (int i=0; i<cartItems.size(); i++)
		  {
	          if(product.getPid()==cartItems.get(i).getProduct().getPid())
	          {
	                CartItem cartItem = cartItems.get(i);
	                cartItem.setName(product.getPname());
	                cartItem.setPrice(product.getPrice());
	                cartItem.setQuantity(cartItem.getQuantity()+1);
	                cartItem.setTotalprice(product.getPrice()*cartItem.getQuantity());
	                cartItemDAO.addCart(cartItem);
	          }
	      }
		  
		  CartItem cartItem = new CartItem();
	      cartItem.setProduct(product);
	      cartItem.setName(product.getPname());
	      cartItem.setPrice(product.getPrice());
	      cartItem.setQuantity(1);
	      cartItem.setTotalprice(product.getPrice()*cartItem.getQuantity());
	      cartItem.setCart(cart);
	      cartItemDAO.addCart(cartItem);
	      	
	      return new ModelAndView("viewproduct");
	}
}
