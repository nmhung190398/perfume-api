package com.perfume.dto.mapper;

import com.perfume.entity.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractConver<E,D> {
    protected static ModelMapper modelMapper = new ModelMapper();

//    abstract D toDTO(E entity);
//    abstract E toEntity(D dto);

    public String[] converToArray(String data){
        if(StringUtils.isNotBlank(data)){
            return data.split("~");
        }
        return null;
    }

    public  String converToString(String[] data){
        if(data != null){
            return StringUtils.join(data,"~");
        }
        return null;
    }

    protected E toEntity(Class<E> type, D source){
        return modelMapper.map(source,type);
    }

    protected D toDTO(Class<D> type, E source){
        return modelMapper.map(source,type);
    }

    public Class<D> test(){
        Class type = (Class)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[1];

        return type;
    }

}
