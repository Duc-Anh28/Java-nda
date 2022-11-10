package Java6.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import Java6.Entity.Role;

public interface RoleDAO extends JpaRepository<Role, String>{

}
