package com.employee.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.employee.app.dto.Employee;
@Repository
public class EmployeeDaoImpl implements EmployeeDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public int saveEmp(Employee e) {
		// TODO Auto-generated method stub
		Session ses=sessionFactory.getCurrentSession();
		ses.save(e);
		
		return e.getEid();
	}

	@Transactional
	public List<Employee> findAllEmp() {
		// TODO Auto-generated method stub
		Session ses=sessionFactory.getCurrentSession();
		
		Query<Employee> query=ses.createQuery("from Employee",Employee.class);
		List<Employee> elist=query.getResultList();
		
		return elist;
	}

	@Transactional
	public Employee getByEmail(String email) {
		// TODO Auto-generated method stub
		Session ses=sessionFactory.getCurrentSession();
		
		Query<Employee> query=ses.createQuery("from Employee where email=:email",Employee.class);
		query.setParameter("email", email);
		
		List<Employee> elist=query.getResultList();
		
		if(elist!=null) 
		{
			
			Employee e1=elist.stream()
					.filter(emp->email.equals(emp.getEmail()))
					.findAny()
					.orElse(null);
			return e1;
		}
		else 
		{
			return null;
		}
	
	}


}
