package com.ty.one_to_many_uni.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.ty.one_to_many_uni.dto.Company;
import com.ty.one_to_many_uni.dto.Employee;

public class CompanyDao {
	public EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vinod");
		return entityManagerFactory.createEntityManager();
	}

	public void saveCompany(Company company) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		List<Employee> list = company.getList();
		for (Employee employee : list) {
			entityManager.persist(employee);
		}
		entityManager.persist(company);
		entityTransaction.commit();
	}

	public void updateCompany(Company company, int id) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		Company  result= entityManager.find(Company.class, id);
		
		if(result!=null){
			company.setId(id);
			company.setList(result.getList());
			entityTransaction.begin();
			entityManager.merge(company);
			entityTransaction.commit();
		}
		else { 
			System.out.println("the comnpany does not exist");
		}

	}

	public void deleteCompany(int id) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Company company = entityManager.find(Company.class, id);
		List<Employee> list = company.getList();
		entityTransaction.begin();
		for (Employee employee : list) {
			entityManager.remove(employee);
		}
		entityManager.remove(company);
		entityTransaction.commit();
	}

	public void getCompanyDetailsOnId(int id) {
		EntityManager entityManager = getEntityManager();
		Company company = entityManager.find(Company.class, id);
		System.out.println(company);
	}

	public void getAllCompanyDetails() {
		EntityManager entityManager=getEntityManager();
		Query query=entityManager.createQuery("Select com from Company com");
		List<Company> list=query.getResultList();
		System.out.println(list);
	}

}
