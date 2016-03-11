package com.food.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.food.dao.CustomerDao;
import com.food.model.Customer;
import com.opensymphony.xwork2.ActionSupport;

@Controller @Scope("prototype")
public class CustomerAction extends ActionSupport{
	


	/*业务层对象*/
    @Resource CustomerDao customerDao;
    
    private Customer customer;
    

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String reg() throws Exception{
		customerDao.AddCustomer(customer);
		return "show_view";
		
	}
	
	private String errMessage;
	
	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
    
	/* 验证用户登录 */
	public String login() {
		Customer db_customer = (Customer)customerDao.QueryCustomerInfo(customer.getName()).get(0);
		if(db_customer == null) { 
			this.errMessage = " 账号不存在 ";
			System.out.print(this.errMessage);
			return INPUT;
		} else if( !db_customer.getPassword().equals(customer.getPassword())) {
			this.errMessage = " 密码不正确! ";
			System.out.print(this.errMessage);
			return INPUT;
		}
		return "show_view";
	}



}
