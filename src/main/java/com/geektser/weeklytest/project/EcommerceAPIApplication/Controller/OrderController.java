package com.geektser.weeklytest.project.EcommerceAPIApplication.Controller;

import com.geektser.weeklytest.project.EcommerceAPIApplication.Model.Orders;
import com.geektser.weeklytest.project.EcommerceAPIApplication.Service.OrderService;
import com.geektser.weeklytest.project.EcommerceAPIApplication.Service.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    SqsService sqsService;

    //Post
    @PostMapping("order")
    public String placeAnOrder(@RequestBody Orders orders)
    {
        Orders newOrder = orderService.placeAnOrder(orders);
        // 2. Send a message to SQS
        sqsService.sendMessage("New order created with ID: " + newOrder.getOrderId()
                + " ,userId: " +newOrder.getUser().getUserId());
        return "Order Placed and message sent to SQS.";
    }

    @GetMapping("orders")
    public List<Orders> getAllOrders()
    {
        return orderService.getAllOrders();
    }
    @GetMapping("order/{id}")
    public Optional<Orders> getOrderById(@PathVariable Integer id)
    {
        return orderService.getOrderById(id);
    }

    @DeleteMapping("order/{id}")
    public String deleteOrderById(@PathVariable Integer id)
    {
        return orderService.deleteOrderById(id);
    }
}


