package Java6.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Java6.DAO.RoleDAO;
import Java6.Entity.Role;
import Java6.Service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleDAO dao;

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	
	
}
