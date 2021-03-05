package com.sssg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sssg.dao.ItemDao;
import com.sssg.dao.ManageDao;
import com.sssg.po.Cart;
import com.sssg.po.Item;
import com.sssg.po.Order;
import com.sssg.po.Shape;
import com.sssg.po.item_category;
import com.sssg.po.item_category_detail;
import com.sssg.service.ManageService;
import com.sssg.utils.Page;
@Service("manageServiceImpl")
public class ManageServiceImpl implements ManageService {

	@Autowired
	private ManageDao manageDao;
	@Autowired
	private ItemDao itemDao;
	
	public Page<Item> MerchantFindAllItem(Integer page, Integer rows) {
	    Item item = new Item();
        // ��ǰҳ
         item.setStart((page-1) * rows) ;
		// ÿҳ��
        item.setRows(rows);
		// ��ѯ��Ʒ�б�
		List<Item> items = manageDao.MerchantFindAllItem(item);
		System.out.println("�̼Ҳ�ѯ������Ʒ"+items);
		// ��ѯ��Ʒ�б��ܼ�¼��
		Integer count = manageDao.selectItemListCount();
		// ����Page���ض���
		Page<Item> result = new Page<Item>();
		result.setPage(page);
		result.setRows(items);
		result.setSize(rows);
		result.setTotal(count);
        return result;
	
	}
	
	public int updateItem(Shape shape) {
		int result = manageDao.updateItem(shape);
		return result;
	}

	public int deleteItem(int shape_id) {
		int result = manageDao.deleteItem(shape_id);
		return result;
	}

	public int merchantAddItem(Item item) {
		int result = manageDao.merchantAddItem(item);
		for(Shape shape:item.getShapeList()){
			manageDao.merchantAddItemShape(shape,item.getItem_name());
		}
		int item_id = manageDao.selectItemIdByItemName(item.getItem_name());
		manageDao.setItemMinPriceAndSumAmount(item_id,item.getItem_name());
		return result;
	}

	public Page<Order> merchantFindAllOrders(Integer page, Integer rows) {
		
	    Order order= new Order();
        // ��ǰҳ
	    order.setStart((page-1) * rows) ;
		// ÿҳ��
	    order.setRows(rows);
		// ��ѯ��Ʒ�б�
		List<Order> orders = manageDao.findAllOrders(order);
		// ��ѯ��Ʒ�б��ܼ�¼��
		Integer count = manageDao.selectOrderListCount();
		// ����Page���ض���
		Page<Order> result = new Page<Order>();
		result.setPage(page);
		result.setRows(orders);
		result.setSize(rows);
		result.setTotal(count);
           return result;
		
	}

	public List<item_category> selectItemCategory() {
		List<item_category> categoryList = manageDao.selectItemCategory();
		return categoryList;
	}

	public List<item_category_detail> selectCategoryDetailByItemCategory(
			String item_category) {
		List<item_category_detail> categoryDetailList = manageDao.selectCategoryDetailByItemCategory(item_category);
		return categoryDetailList;
	}

	public Page<Item> findItemByCondition(Integer page, Integer rows,
			Item item, String item_category) {
		 Item item1 = new Item();
	        // ��ǰҳ
	         item1.setStart((page-1) * rows) ;
	         System.out.println("��ǰҳ�棺"+page);
			// ÿҳ��
	        item1.setRows(rows);
			// ��ѯ��Ʒ�б�
			List<Item> items = manageDao.findItemByCondition(item1,item,item_category);
			System.out.println("������ѯ������Ʒ����Ϊ"+items.size());
			
			// ��ѯ��Ʒ�б��ܼ�¼��
			Integer count = manageDao.selectItemByConditionListCount(item1,item,item_category);
			System.out.println("��ѯ����������Ʒ����Ϊ+"+count);
			// ����Page���ض���
			Page<Item> result = new Page<Item>();
			result.setPage(page);
			result.setRows(items);
			result.setSize(rows);
			result.setTotal(count);
	        return result;
	}

	public int recoverItem(int shape_id) {
		int result = manageDao.recoverItem(shape_id);
		return result;
	}



}
