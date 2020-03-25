package com.perfume.dto;

import com.nmhung.anotation.QueryField;
import com.nmhung.anotation.TableName;
import com.perfume.dto.mapper.AmountDTO;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class ProductDTO extends BaseDTO {
    public String name;

    public String code;
    public String highlight;
    public String gender;
    public Date MFG;
    public Date EXP;
    public String image;
    public String description;

    public CategoryDTO category;

    public Long categoryId;

    public List<VersionDTO> versions;

//    public List<CartItemDTO> cartItems;

    public ProducerDTO producer;

    public Long producerId;

    public AmountDTO amount;

    public Long amountId;

    public FragrantDTO fragrant;

    public Long fragrantId;

    private List<TargetDTO> targets;
}
