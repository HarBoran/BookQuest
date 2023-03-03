package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Book;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Payment;
import com.example.demo.entity.User;
import com.example.demo.service.BookService;
import com.example.demo.service.CartService;
import com.example.demo.service.OrderDetailService;
import com.example.demo.service.OrderService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.UserService;


@Controller
@RequestMapping(value="/order")
public class OrderDetailsController {

   @Autowired
   private OrderDetailService orderDetailService;
   
   @Autowired
   private UserService userService;
   
   @Autowired
   private OrderService orderService;
   
   @Autowired
   private CartService cartService;
   
   @Autowired
   private PaymentService paymentService;
   
   @Autowired
   private BookService bookService;
   
   @GetMapping("/")
   public String OrderDetails(Model theModel, Principal principal) {
      
        String userEmail = principal.getName(); 
        User user = userService.getUserByEmail(userEmail); 
        //int userId = user.getUserId();
        List<Order> orders = orderService.findOrderByUser(user);
        
        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
        for(int i =0;  i<orders.size(); i++) {
           orderDetails.addAll(orderDetailService.findOrderDetailsByOrder(orders.get(i)));   
        
        }
     
        theModel.addAttribute("orderDetails", orderDetails);
   
         /*
          * System.out.println("order=========="+orders);
          * System.out.println("orderDetails=========="+orderDetails);
          */
      return "orderDetails";
   }
   
   @GetMapping("/buy/{cartId}")
   public String buyBook(@PathVariable(name="cartId")int cartId,Model model,Principal principal) {
        String userEmail = principal.getName(); 
        User user = userService.getUserByEmail(userEmail); 
        
        List<Payment> paymentList = paymentService.findPaymentByUser(user);
        List<Cart> cartListq = cartService.findCartByUser(user);
         
        System.out.println("test total List =" +cartListq);
        Cart cartList = cartService.findById(cartId);
        System.out.println("test one List =" +cartList);
        
       
        
        
      model.addAttribute("cartList", cartList);
      model.addAttribute("paymentList",paymentList);
      model.addAttribute("order", new Order());
      
      return "buyInfoDetails";
   }
   
   @PostMapping("/buytotal")
   public String buyTotal(Model model,Principal principal) {
         int totalPrice=0;
        String userEmail = principal.getName(); 
        User user = userService.getUserByEmail(userEmail); 
        List<Payment> paymentList = paymentService.findPaymentByUser(user);      
        List<Cart> cartList = cartService.findCartByUser(user);
        
        for(int i=0 ; i<cartList.size() ; i++) {
        int Price = cartList.get(i).getBook().getPrice() * cartList.get(i).getBookQuantity();
           
           totalPrice += Price;
        }
        
        model.addAttribute("paymentList",paymentList);
        model.addAttribute("cartList", cartList);
        model.addAttribute("order", new Order());
        model.addAttribute("totalPrice", totalPrice);
      return "buyInfoDetailsTotal";
   }
   
   @PostMapping("/save")
   public String saveOrder(@ModelAttribute("order") Order order, @RequestParam("totalPrice")int totalPrice, Principal principal,
         @RequestParam("bookId") int bookId,@RequestParam("cartId") int cartId) {
      
        String userEmail = principal.getName(); 
        User user = userService.getUserByEmail(userEmail); 
      order.setTotalPrice(totalPrice);
      order.setUser(user);
      orderService.save(order);   
      
      Optional<Book> book = bookService.findById(bookId);
      Book book1= book.get();
      int bookPrice = book.get().getPrice();

      Cart cart = cartService.findById(cartId);
      
      int orderQuantity = cart.getBookQuantity();
      orderDetailService.saveOrderDetails(order,book1,bookPrice,orderQuantity);
       cartService.deletecartId(cartId);
      return "home";
   }
   
   @PostMapping("/saveTotal")
   public String saveOrderTotal(@ModelAttribute("order") Order order,Principal principal,
         @RequestParam("totalPrice") int totalPrice) {
        String userEmail = principal.getName(); 
        User user = userService.getUserByEmail(userEmail); 
        
        order.setTotalPrice(totalPrice);
        order.setUser(user);
        orderService.save(order);
        
        List<Cart> cart = cartService.findCartByUser(user);
     
        for(int i =0; i<cart.size(); i++) {
      
           int orderQuantity = cart.get(i).getBookQuantity();
           Book book = cart.get(i).getBook();
           int price = (cart.get(i).getBookQuantity()*cart.get(i).getBook().getPrice());
           orderDetailService.saveOrderDetails(order,book,price,orderQuantity);
        }
    
        cartService.deleteCartByUser(user);
        
      
      return "home";
   }   
   
   
}