package com.perfume.dto.mapper;

import com.nmhung.mapper.BaseConver;
import com.perfume.dto.*;
import com.perfume.entity.Producer;
import com.perfume.entity.Product;
import com.perfume.entity.Target;
import com.perfume.entity.Version;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductMapper extends AbstractConver<Product, ProductDTO> {

    public ProductMapper() {
        super();
//        this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
    }

//    @Override
//    public ProductDTO toDTO(Product entity) {
//        ProductDTO productDTO = new ProductDTO();
//        BeanUtils.copyProperties(entity, productDTO);
//        VersionDTO[] versions = modelMapper.map(entity.getVersions().toArray(), VersionDTO[].class);
//        TargetDTO[] targets = modelMapper.map(entity.getTargets().toArray(), TargetDTO[].class);
//        productDTO.setVersions(Arrays.asList(versions));
//        productDTO.setTargets(Arrays.asList(targets));
//
//
//
//        productDTO.setAmount(entity.getAmount() == null ? null : modelMapper.map(entity.getAmount(), AmountDTO.class));
//        productDTO.setFragrant(entity.getFragrant() == null ? null : modelMapper.map(entity.getFragrant(), FragrantDTO.class));
//
//        productDTO.setProducer(entity.getProducer() == null ? null : modelMapper.map(entity.getProducer(), ProducerDTO.class));
//
//        productDTO.setCategory(entity.getCategory() == null ? null : modelMapper.map(entity.getCategory(), CategoryDTO.class));
//
//        return productDTO;
//    }

    @Override
    public Product toEntity(ProductDTO dto) {

        return super.toEntity(dto);
    }
}
