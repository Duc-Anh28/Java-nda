package Java6.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Java6.DAO.ProductDAO;
import Java6.Entity.Product;
import Java6.Service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDAO productDAO;

	@Override
	public List<Product> findAll() {
		return productDAO.findAll();
	}

	@Override
	public Product findByid(Integer id) {
		return productDAO.findById(id).get();
	}

	@Override
	public List<Product> findByCategory(String cid) {
		// TODO Auto-generated method stub
		return productDAO.findByCategoryID(cid);
	}

	@Override
	public Product create(Product product) {
		// TODO Auto-generated method stub
		return productDAO.save(product);
	}

	@Override
	public Product update(Product product) {
		// TODO Auto-generated method stub
		return productDAO.save(product);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		productDAO.deleteById(id);
	}

	@Override
	public Page<Product> finPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return productDAO.findAll(pageable);
	}

	@Override
	public Page<Product> findByCategoryPage(String cid,Pageable pageable) {
		// TODO Auto-generated method stub
		return productDAO.findByCategoryPage(cid, pageable);
	}

	@Override
	public Page<Product> findByKeywords(String kw, Pageable pageable) {
		// TODO Auto-generated method stub
		return productDAO.findByKeywords(kw,pageable);
	}

	@Override
	public List<Product> findByKeywords1(String kw) {
		// TODO Auto-generated method stub
		return productDAO.findByKeywords1(kw);
	}

	
}
