package com.sishuok.architecture1;

//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sishuok.architecture1.cartmgr.service.ICartService;
import com.sishuok.architecture1.cartmgr.vo.CartModel;
import com.sishuok.architecture1.cartmgr.vo.CartQueryModel;
import com.sishuok.architecture1.customermgr.vo.CustomerModel;
import com.sishuok.architecture1.goodsmgr.service.IGoodsService;
import com.sishuok.architecture1.goodsmgr.vo.GoodsModel;
import com.sishuok.architecture1.goodsmgr.vo.GoodsQueryModel;
import com.sishuok.architecture1.ordermgr.service.IOrderDetailService;
import com.sishuok.architecture1.ordermgr.service.IOrderService;
import com.sishuok.architecture1.storemgr.service.IStoreService;
import com.sishuok.pageutil.Page;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private IGoodsService igs = null;
	@Autowired
	private ICartService ics = null;
	@Autowired
	private IOrderService ios = null;
	@Autowired
	private IOrderDetailService iods = null;
	@Autowired
	private IStoreService iss = null;
//	
//	@Autowired
//	private SecurityManager sm = null;
//	
	@RequestMapping(value="/toIndex",method=RequestMethod.GET)
	public String toIndex(Model model){
//		SecurityUtils.setSecurityManager(sm);
//		Subject currentUser = SecurityUtils.getSubject();
		
//		CustomerModel cm = (CustomerModel)currentUser.getSession().getAttribute("Login_Customer");
//		
//		System.out.println("now cm==="+cm);
		
		GoodsQueryModel gqm = new GoodsQueryModel();
		gqm.getPage().setPageShow(100);

		Page<GoodsModel> page = igs.getByConditionPage(gqm);
		
		model.addAttribute("page",page);
		
		
		return "index";
	}
//	@RequestMapping(value="/toShowLoginUser",method=RequestMethod.GET)
//	public String toShowLoginUser(Model model){
//		SecurityUtils.setSecurityManager(sm);
//		Subject currentUser = SecurityUtils.getSubject();
//		
//		CustomerModel cm = (CustomerModel)currentUser.getSession().getAttribute("Login_Customer");
//		
//		model.addAttribute("c",cm);
//		
//		
//		return "common/loginUser";
//	}
	
	@RequestMapping(value="/getGoodsPrice/{goodsUuid}",method=RequestMethod.GET)
	@ResponseBody
	public PriceModel getGoodsPrice(Model model,@PathVariable("goodsUuid")int goodsUuid){
		PriceModel pm = new PriceModel();
		pm.setGoodsUuid(goodsUuid);
		pm.setPrice(100*Math.random());
		
		return pm;
	}
	@RequestMapping(value="/toGoodsDesc/{goodsUuid}",method=RequestMethod.GET)
	public String toGoodsDesc(Model model,@PathVariable("goodsUuid")int goodsUuid){
		GoodsModel gm = igs.getByUuid(goodsUuid);
		
		model.addAttribute("m",gm);
		return "goods/desc";
	}
	@RequestMapping(value="/addToCart/{goodsUuid}",method=RequestMethod.GET)
	public String addToCart(Model model,@PathVariable("goodsUuid")int goodsUuid,@CookieValue("MyLogin")String myLogin){
		int customerUuid = Integer.parseInt( myLogin.split(",")[0]);
		
		CartModel cm = new CartModel();
		cm.setBuyNum(1);
		cm.setCustomerUuid(customerUuid);
		cm.setGoodsUuid(goodsUuid);
		
		ics.create(cm);
		///////////////////////////
		CartQueryModel cqm = new CartQueryModel();
		cqm.getPage().setPageShow(1000);
		cqm.setCustomerUuid(customerUuid);
		
		
		Page<CartModel>  page = ics.getByConditionPage(cqm);
		
		model.addAttribute("page",page);
		
		return "cart/myCart";
	}
	@RequestMapping(value="/toCart",method=RequestMethod.GET)
	public String toCart(Model model,@CookieValue("MyLogin")String myLogin){
		int customerUuid = Integer.parseInt( myLogin.split(",")[0]);
		
		CartQueryModel cqm = new CartQueryModel();
		cqm.getPage().setPageShow(1000);
		cqm.setCustomerUuid(customerUuid);
		
		Page<CartModel>  page = ics.getByConditionPage(cqm);
		
		model.addAttribute("page",page);
		
		return "cart/myCart";
	}
	@RequestMapping(value="/order",method=RequestMethod.GET)
	public String order(){//@CookieValue("MyLogin")String myLogin){
		//1:查出这个人购物车所有的信息		
		int customerUuid = 1;//Integer.parseInt( myLogin.split(",")[0]);
		
		ios.order(customerUuid);
		
		return "success";
	}
}
