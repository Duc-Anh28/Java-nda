package Java6.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Java6.DAO.CategoryDAO;
import Java6.Entity.Category;
import Java6.Service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryDAO cdao;
	
	@Override
	public List<Category> findAll() {  
		return cdao.findAll();
	}

	@Override
	public Category create(Category category) {
		// TODO Auto-generated method stub
		return cdao.save(category);
	}

	@Override
	public Category update(Category category) {
		// TODO Auto-generated method stub
		return cdao.save(category);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		cdao.deleteById(id);
	}

}
