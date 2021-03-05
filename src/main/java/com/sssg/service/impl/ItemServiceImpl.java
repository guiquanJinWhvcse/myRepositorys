package com.sssg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;







import org.springframework.transaction.annotation.Transactional;

import com.sssg.dao.ItemDao;
import com.sssg.po.Item;
import com.sssg.po.Shape;
import com.sssg.po.item_category;
import com.sssg.service.ItemService;
import com.sssg.utils.Page;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDao itemDao; 
	public Page<Item> findAllItem(Integer page,Integer rows) {
		         Item item = new Item();
		        // ��ǰҳ
		         item.setStart((page-1) * rows) ;
				// ÿҳ��
		        item.setRows(rows);
				// ��ѯ��Ʒ�б�
				List<Item> items = itemDao.findAllItem(item);
				// ��ѯ��Ʒ�б��ܼ�¼��
				Integer count = itemDao.selectItemListCount();
				// ����Page���ض���
				Page<Item> result = new Page<Item>();
				result.setPage(page);
				result.setRows(items);
				result.setSize(rows);
				result.setTotal(count);
		//List<Item> items = itemDao.findAllItem();
		return result;
	}

	
	public Page<Item> findItemByCategory(Integer page,Integer rows,String item_categorydetail){
		  Item item = new Item();
	        // ��ǰҳ
	         item.setStart((page-1) * rows) ;
			// ÿҳ��
	        item.setRows(rows);
			// ��ѯ��Ʒ�б�
			List<Item> items = itemDao.findItemByCategory(item,item_categorydetail);
			// ��ѯ��Ʒ�б��ܼ�¼��
			Integer count = itemDao.selectItemListCountByCategory(item_categorydetail);
			// ����Page���ض���
			Page<Item> result = new Page<Item>();
			result.setPage(page);
			result.setRows(items);
			result.setSize(rows);
			result.setTotal(count);
	        return result;
		
	}

	public int findItemAmountByName(int shape_id) {
		int amount = itemDao.findItemAmountByName(shape_id);
		return amount;
	}

	public int updateItemAmountByName(String cartItem_name, int currentamount) {
		int result = itemDao.updateItemAmountByName(cartItem_name, currentamount);
		return result;
	}


	public Page<Item> findItemByName(Integer page,Integer rows,String item_name) {
	      Item item = new Item();
	        // ��ǰҳ
	         item.setStart((page-1) * rows) ;
			// ÿҳ��
	        item.setRows(rows);
			// ��ѯ��Ʒ�б�
	        System.out.println("�����б�"+item+item_name);
			List<Item> items = itemDao.findItemByName(item, item_name);
			// ��ѯ��Ʒ�б��ܼ�¼��
			Integer count = itemDao.selectItemNameListCount(item_name);
			// ����Page���ض���
			Page<Item> result = new Page<Item>();
			result.setPage(page);
			result.setRows(items);
			result.setSize(rows);
			result.setTotal(count);
			System.out.println("���ǵ�"+item.getStart()+"ҳ");
			System.out.println("��"+item.getRows()+"������");
			System.out.println("�鵽"+items.size()+"����¼");
			System.out.println(result.getTotal());
	return result;
		
	}


	public List<Shape> selectItemDetailByItemId(int item_id) {
		List<Shape> shapeList = itemDao.selectItemDetailByItemId(item_id);
		System.out.println("service"+shapeList);
		return shapeList;
	}


	public List<item_category> selectCategoryAndDetail() {
		List<item_category> item_categoryList = itemDao.selectCategoryAndDetail();
		return item_categoryList;
	}


	public List selectAllItemName() {
		
		return itemDao.selectAllItemName();
	}







	
}
