package Java6.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Java6.Entity.Product;


public interface ProductDAO extends JpaRepository<Product, Integer>{

	@Query("select p from Product p where p.category.id=?1")
	List<Product> findByCategoryID(String cid);
	
	@Query("select p from Product p where p.category.id=?1")
	Page<Product> findByCategoryPage(String cid,Pageable pageable);

	@Query("SELECT o FROM Product o WHERE o.name LIKE ?1")
	Page<Product> findByKeywords(String kw, Pageable pageable);

	@Query("SELECT o FROM Product o WHERE o.name LIKE ?1")
	List<Product> findByKeywords1(String kw);
	
}
