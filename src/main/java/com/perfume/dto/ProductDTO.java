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

    public ProductDTO() {
    }

    public ProductDTO(String name, String code, String highlight, String gender, Date MFG, Date EXP, String image, String description, CategoryDTO category, Long categoryId, List<VersionDTO> versions, ProducerDTO producer, Long producerId, AmountDTO amount, Long amountId, FragrantDTO fragrant, Long fragrantId, List<TargetDTO> targets) {
        this.name = name;
        this.code = code;
        this.highlight = highlight;
        this.gender = gender;
        this.MFG = MFG;
        this.EXP = EXP;
        this.image = image;
        this.description = description;
        this.category = category;
        this.categoryId = categoryId;
        this.versions = versions;
        this.producer = producer;
        this.producerId = producerId;
        this.amount = amount;
        this.amountId = amountId;
        this.fragrant = fragrant;
        this.fragrantId = fragrantId;
        this.targets = targets;
    }
}
