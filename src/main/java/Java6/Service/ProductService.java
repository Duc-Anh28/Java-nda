package Java6.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import Java6.Entity.Product;

public interface ProductService {

	List<Product> findAll();
	List<Product> findByKeywords1(String kw);

	Product findByid(Integer id);

	List<Product> findByCategory(String cid);

	Product create(Product product);

	Product update(Product product);

	void delete(Integer id);
	
	
	Page<Product> finPage(Pageable pageable);
	Page<Product> findByCategoryPage(String cid,Pageable pageable);
	Page<Product> findByKeywords(String kw,Pageable pageable);
}
