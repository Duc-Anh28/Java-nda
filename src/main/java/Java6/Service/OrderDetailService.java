package Java6.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import Java6.Entity.OrderDetail;


public interface OrderDetailService {

	List<OrderDetail> findByIDOrder(Long id);

	List<Object>  findBycreateDate(String date);
}
