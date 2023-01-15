package com.ty.one_to_many_uni.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.boot.model.source.spi.CascadeStyleSource;
import org.hibernate.internal.build.AllowSysOut;

import com.ty.one_to_many_uni.dao.CompanyDao;
import com.ty.one_to_many_uni.dto.Company;
import com.ty.one_to_many_uni.dto.Employee;

public class ComapnyMain {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		boolean condition=true;
		Employee employee=new Employee();
		Employee employee2=new Employee();
		Company company=new Company();
		CompanyDao dao=new CompanyDao();
		
		do {
			System.out.println("Enter your choice \n1 for save company\n2 for update company\n3 for delete company\n4 for get details on company id\n5 get total details of company\n6 for exit");
			int choice=sc.nextInt();
			switch(choice) {
			case 1:{
				System.out.println("1 st employe details");
				System.out.println("enter employe name");
				String ename=sc.next();
				employee.setName(ename);
				System.out.println("enter employe address");
				String address=sc.next();
				employee.setAddress(address);
				System.out.println("enter employe phone number");
				long phone=sc.nextLong();
				employee.setPhone(phone);
				System.out.println("2nd employe details");
				System.out.println("enter employe name");
				String ename1=sc.next();
				employee2.setName(ename1);
				System.out.println("enter employe address");
				String address1=sc.next();
				employee2.setAddress(address1);
				System.out.println("enter employe phone number");
				long phone1=sc.nextLong();
				employee2.setPhone(phone1);
				List<Employee> list=new ArrayList<Employee>();
				list.add(employee);
				list.add(employee2);
				System.out.println("Enter company details");
//				System.out.println("Enter compamy id");
//				int id=sc.nextInt();
//				company.setId(id);
				System.out.println("enetr name");
				String name=sc.next();
				company.setName(name);
				System.out.println("enetr company gst");
				String gst=sc.next();
				company.setGst(gst);
				company.setList(list);
				dao.saveCompany(company);
				
			}break;
			case 2:{
				System.out.println("eneter the company id");
				int id=sc.nextInt();
				System.out.println("enter company name to be updated");
				String name=sc.next();
				company.setName(name);
				System.out.println("emter gst to be updated");
				String gst=sc.next();
				company.setGst(gst);
				dao.updateCompany(company, id);
				
			}break;
			case 3:{
				System.out.println("enter the company id to be delete");
				int id=sc.nextInt();
				dao.deleteCompany(id);
			}break;
			case 4:{
				System.out.println("enetr company id to get details");
				int id=sc.nextInt();
				dao.getCompanyDetailsOnId(id);
			}break;
			case 5:{
		         dao.getAllCompanyDetails();
			}break;
			case 6:{
				condition=false;
			}break;
			default:{
				System.out.println("invalid choice");
			}
			}
			
		} while (condition);

       System.out.println("thank you");
		
	}

}
