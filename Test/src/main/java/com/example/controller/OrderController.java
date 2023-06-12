package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.model.Book;

import com.example.model.Order;
import com.example.model.User;
import com.example.service.BookService;
import com.example.service.OrderService;
import com.example.service.UserService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/list")
	public String ListOrder(Model model) {
		List<Order> orderList = orderService.getAllOrder();
		model.addAttribute("orderList", orderList);
		return "order/list";
	}
	
	@GetMapping("/add")
	public String addOrder(Model model) {
		Order order = new Order();
		List<Book> bookList = bookService.getAllBook();
		List<User> userList = userService.getAllUser();
		
		model.addAttribute("books", bookList);
		model.addAttribute("users", userList);
		model.addAttribute("order", order);
		return "order/add";
	}
	
	@PostMapping("/save")
	public String saveOrder(@ModelAttribute("order") Order order){
		orderService.addOneOrder(order);
		return "redirect:/order/list";
	}
	
	@GetMapping("/list/delete/{id}")
	public String deleteOrder(@PathVariable("id") int id) {
		orderService.deleteOneOrder(id);
		return "redirect:/order/list";
	}
	
	@GetMapping("/list/edit/{id}")
	public String editOrder(@PathVariable("id") int id, Model model) {
		Order order = orderService.findOneOrder(id);
		List<Book> bookList = bookService.getAllBook();
		List<User> userList = userService.getAllUser();
		model.addAttribute("books", bookList);
		model.addAttribute("users", userList);
		model.addAttribute("order", order);
		return "order/update";
	}

	@PostMapping("/list/update")
	public String updateOrder(int id, @ModelAttribute("order") Order order) {
		orderService.updateOneOrder(id, order);
		return "redirect:/order/list";
	}

}
