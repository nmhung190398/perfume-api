package com.perfume.dto.mapper;

import com.perfume.entity.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;

import java.lang.reflect.ParameterizedType;

public class AbstractConver<E, D> {
    protected static ModelMapper modelMapper = new ModelMapper();

    public D toDTO(E entity) {
        return modelMapper.map(entity, getTypeDTO());
    }

    public E toEntity(D dto) {
        return modelMapper.map(dto, getTypeEntity());
    }

    public String[] converToArray(String data) {
        if (StringUtils.isNotBlank(data)) {
            return data.split("~");
        }
        return null;
    }

    public String converToString(String[] data) {
        if (data != null) {
            return StringUtils.join(data, "~");
        }
        return null;
    }

    protected E toEntity(Class<E> type, D source) {
        return modelMapper.map(source, type);
    }

    protected D toDTO(Class<D> type, E source) {
        return modelMapper.map(source, type);
    }

    private Class<D> getTypeDTO() {
        return getType(1);
    }

    private Class<E> getTypeEntity() {
        return getType(0);
    }

    private Class getType(int index) {
        Class type = (Class<E>)
                ((ParameterizedType) getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[index];
        return type;
    }

}
