package Java6.Service;

import java.util.List;

import Java6.Entity.Category;

public interface CategoryService {

	List<Category> findAll();

	Category create(Category category);

	Category update(Category category);

	void delete(String id);

}
