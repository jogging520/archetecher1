package com.sishuok.transaction;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sishuok.consistence.MyTest;

@Service
public class TransactionTest1 {
	@Autowired
	@Qualifier("jtaDataSource1")
	private DataSource ds1;
	@Autowired
	@Qualifier("jtaDataSource2")
	private DataSource ds2;
	
	public void testAdd(){
		JdbcTemplate jt1 = new JdbcTemplate(ds1);
		JdbcTemplate jt2 = new JdbcTemplate(ds2);
		
		List list = jt1.queryForList("select * from tbl_store");
		System.out.println("list===="+list);
		
		jt1.execute("insert into tbl_store values(8,8,8)");
		
		jt2.execute("insert into tbl_store values(8,8,8)");
		
		int i = 5/0;
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml","applicationContext-tx.xml");
		
		TransactionTest1 test = (TransactionTest1)ctx.getBean("transactionTest1");
		
		test.testAdd();	
	}

}
