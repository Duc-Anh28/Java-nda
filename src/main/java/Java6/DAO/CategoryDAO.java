package Java6.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import Java6.Entity.Category;

public interface CategoryDAO  extends JpaRepository<Category, String>{}

