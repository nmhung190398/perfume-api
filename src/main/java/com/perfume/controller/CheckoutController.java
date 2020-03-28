package com.perfume.controller;

import com.perfume.dto.ResponseMsg;
import com.perfume.entity.CartItem;
import com.perfume.entity.Category;
import com.perfume.entity.Checkout;
import com.perfume.entity.CheckoutItem;
import com.perfume.repository.CartItemRepository;
import com.perfume.repository.CheckoutItemRepository;
import com.perfume.repository.CheckoutRepository;
import com.perfume.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CheckoutController {

    @Autowired
    CheckoutRepository checkoutRepository;

    @Autowired
    CheckoutItemRepository checkoutItemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartItemRepository cartItemRepository;


    public ResponseEntity<ResponseMsg<Checkout>> checkout(@RequestBody Checkout checkout) {
        ResponseMsg<Checkout> responseMsg = new ResponseMsg<>(checkout, 200, "");

        Long idUser = checkout.getUser().getId();

        List<CartItem> carts = cartItemRepository.findByUserId(idUser);
        if (carts.size() > 0) {
            cartItemRepository.deleteAll(carts);
            checkoutRepository.save(checkout);
        } else {
            responseMsg.setStatus(300);
            responseMsg.setMsg("Giỏ hàng không có hàng");
        }
        return ResponseEntity.status(responseMsg.getStatus()).body(responseMsg);
    }

}
