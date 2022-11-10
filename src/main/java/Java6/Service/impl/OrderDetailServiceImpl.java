package Java6.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Java6.DAO.OrderDetailDAO;
import Java6.Entity.OrderDetail;
import Java6.Service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{

	@Autowired
	OrderDetailDAO dao;

	@Override
	public List<OrderDetail> findByIDOrder(Long id) {
		// TODO Auto-generated method stub
		return dao.findByIDOrder(id);
	}

	@Override
	public List<Object> findBycreateDate(String date) {
		// TODO Auto-generated method stub
		return dao.findBycreateDate(date);
	}
	
	
}
