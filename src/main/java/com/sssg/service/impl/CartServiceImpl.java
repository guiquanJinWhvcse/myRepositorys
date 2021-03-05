package com.sssg.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sssg.dao.CartDao;
import com.sssg.po.Cart;
import com.sssg.po.CartItem;
import com.sssg.service.CartService;

@Service("cartServiceImpl")
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false)
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;
//	public int addItem(CartItem cartItem,int amount,int id) {
//		Cart cart = cartDao.findCartById(id);
//		if(cart==null){
//			cartDao.addCart(id);
//		}
//		System.out.println("���ﳵid"+id);
//		int result = cartDao.addItem(cartItem,id);
//		cartDao.figureCartItemSum(cartItem,amount,id);
//		System.out.println("���"+result);
//		return result;
//	}
//	
	
	public int addItem(int shape_id, int cartitem_amount,int user_id) {
		Cart cart = cartDao.findCartById(user_id);//�����Ƿ��й��ﳵ
		if(cart==null){
			cartDao.addCart(user_id);  //���޹��ﳵ���½����ﳵ
	}
		int result;
		int amount = 0;
		Cart cart1 = cartDao.findCartById(user_id);//��ѯ�½��Ĺ��ﳵ
		CartItem cartitem = cartDao.findCartItem(shape_id,cart1.getCart_id());//��ѯ���ﳵ���Ƿ��Ѿ��и���Ʒ
		System.out.println(shape_id+""+user_id+"!!!!!");
		double shape_price = cartDao.findItemPriceByItemId(shape_id);//����item_id��ѯ�������Ʒ�ļ۸�
		if(cartitem!=null){
			int currentamount = cartDao.findCartItemAmountById(shape_id,cart1.getCart_id());//��ѯ�Ѿ��������Ʒ�Ķ�Ӧ������
			 amount = cartitem_amount+currentamount;//��Ҫ�޸ĵ�������ֵ
			result = cartDao.updateCartItem(amount,shape_id,cart1.getCart_id());//������Ʒ����
			cartDao.updateCartItem(amount, shape_id, cart1.getCart_id());
			System.out.println("��Ҫ����۸�"+ cart1.getCart_id()+""+shape_price+""+amount+""+shape_id);
			cartDao.figureCartItemSum(shape_price,amount,shape_id,cart1.getCart_id());//���㹺�ﳵ��ļ۸�sum
		}else{
			result = cartDao.addItem(shape_id,cartitem_amount, user_id,cart1.getCart_id());//��һ�ι����ʱ�������Ʒ
			cartDao.figureCartItemSum(shape_price,cartitem_amount,shape_id,cart1.getCart_id());//���㹺�ﳵ��ļ۸�sum
			
		}
		return result;
		
	}
	
//	public CartItem findCartItemByName(String CartItem_name,int id){
//		System.out.println(CartItem_name);
//		CartItem cartItem = cartDao.findCartItemByName(CartItem_name,id);
//		return cartItem;
//	}
//
//	
//	public int updateCartItem(CartItem cartItem, int amount,int id) {
//		int result = cartDao.updateCartItem(cartItem, amount);
//		cartDao.figureCartItemSum(cartItem,amount,id);
//		return result;
//		
//	}

	public List<CartItem> findCartItemById(int cart_id) {
		//Cart cart = cartDao.findCartById(id);
		List<CartItem> cartitems= cartDao.findCartItemById(cart_id);
		return cartitems;
	}

	public int cartSubmit(int user_id,int cart_id) {
		cartDao.updateItemAmount(cart_id);
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy��MM��dd�� HHʱmm��ss��");
		Date date = new Date();
		String now = sdf1.format(date);
		cartDao.addCartToOrder(user_id,cart_id,now);
		double totalPrice = cartDao.findCartTotalPrice(cart_id);
		int result = cartDao.updateCartItemStateByCartId(cart_id,totalPrice);
		
		
		return result;
	}

	public Cart findCartById(int user_id) {
		Cart cart = cartDao.findCartById(user_id);
		return cart;
	}

	public int addCart(int id) {
		int result = cartDao.addCart(id);
		return result;
	}

	public int deleteCartItem(int cartitem_id) {
		int result = cartDao.deleteCartItemById(cartitem_id);
		return 0;
	}

	public double findCartTotalPrice(int cart_id) {
		double totalPrice = cartDao.findCartTotalPrice(cart_id);
		return totalPrice;
	}







	




	

}
