package com.perfume.dto;

import com.nmhung.sql.BaseDAO;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class CouponDTO extends BaseDTO {

    public String code;

    public Date MFG;

    public int total;

//    List<CheckoutDTO> checkouts;
}
