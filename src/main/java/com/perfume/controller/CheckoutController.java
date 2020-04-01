package com.perfume.controller;

import com.perfume.constant.CheckoutStatus;
import com.perfume.constant.StatusEnum;
import com.perfume.dto.ResponseMsg;
import com.perfume.entity.*;
import com.perfume.repository.CartItemRepository;
import com.perfume.repository.CheckoutItemRepository;
import com.perfume.repository.CheckoutRepository;
import com.perfume.repository.UserRepository;
import com.perfume.sercurity.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    @Autowired
    CheckoutRepository checkoutRepository;

    @Autowired
    CheckoutItemRepository checkoutItemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartItemRepository cartItemRepository;


    @Autowired
    private JwtToken jwtToken;


//    @PutMapping("/delivery/{id}")
//    public ResponseEntity<ResponseMsg<Object>> changeStatusDelivery(@PathVariable) {
//        return checkout(null, null);
//    }
//
//    private ResponseEntity<ResponseMsg<Object>> changeStatus(Long id, CheckoutStatus status) {
//        Checkout checkout = new Checkout();
//        checkout.setStatus(status.getValue());
//
//        return null;
//
//
//    }

    @PostMapping("")
    public ResponseEntity<ResponseMsg<Checkout>> checkout(@RequestBody Checkout checkout, HttpServletRequest request) {
        ResponseMsg<Checkout> responseMsg = new ResponseMsg<>(null, 200, "");


        User user = jwtToken.getUserLogin(request);
        List<CartItem> carts = user.getCartItems();
        if (carts.size() > 0) {

            checkout.setId(null);
            checkout.setStatus(CheckoutStatus.ACTIVE.getValue());


            List<CheckoutItem> checkoutItems = carts.stream().map(cartItem -> {
                CheckoutItem checkoutItem = new CheckoutItem(cartItem.getQuantity(), cartItem.getVersion(), checkout);
                return checkoutItem;
            }).collect(Collectors.toList());
            checkout.setCheckoutItems(checkoutItems);
            checkout.setUser(user);

            if (checkoutRepository.save(checkout) != null) {
                cartItemRepository.deleteAll(carts);
            } else {
                responseMsg.setStatus(400);
                responseMsg.setMsg("checkout thất bại");
            }
            System.out.println();

        } else {
            responseMsg.setStatus(300);
            responseMsg.setMsg("Giỏ hàng không có hàng");
        }
        return ResponseEntity.status(responseMsg.getStatus()).body(responseMsg);
    }

}
