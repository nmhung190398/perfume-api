package com.perfume.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CheckoutDTO extends BaseDTO {
    public UserDTO user;

    public CouponDTO coupon;

    public List<CheckoutItemDTO> checkoutItems;
}
