package com.perfume.dto;

import lombok.Data;

import javax.persistence.*;

@Data
public class CartItemDTO extends BaseDTO {

    public Long userId;

    public Long productId;

    public int quantity;

//    public UserDTO user;

    public ProductDTO product;
}
