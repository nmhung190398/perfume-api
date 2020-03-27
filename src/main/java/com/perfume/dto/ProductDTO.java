package com.perfume.dto;

import com.nmhung.anotation.QueryField;
import com.nmhung.anotation.TableName;
import com.perfume.dto.mapper.AmountDTO;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ProductDTO{
    private Long id;
    public String name;

    public String code;
    public String highlight;
    public String gender;
    public Date MFG;
    public Date EXP;
    public String image;
    public String description;

    public CategoryDTO category = new CategoryDTO();

    public Long categoryId;

    public List<VersionDTO> versions = new ArrayList<>();

//    public List<CartItemDTO> cartItems;

    public ProducerDTO producer = new ProducerDTO();

    public Long producerId;

    public AmountDTO amount = new AmountDTO();

    public Long amountId;

    public FragrantDTO fragrant = new FragrantDTO();

    public Long fragrantId;

    private List<TargetDTO> targets = new ArrayList<>();

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String code, String highlight, String gender, Date MFG, Date EXP, String image, String description, CategoryDTO category, Long categoryId, List<VersionDTO> versions, ProducerDTO producer, Long producerId, AmountDTO amount, Long amountId, FragrantDTO fragrant, Long fragrantId, List<TargetDTO> targets) {
        this.id = id;
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
