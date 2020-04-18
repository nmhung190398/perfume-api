package com.perfume.repository.custom.impl;

import com.perfume.entity.Checkout;
import com.perfume.entity.User;
import com.perfume.repository.custom.CheckoutRepositoryCustom;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CheckoutRepositoryCustomImpl extends BaseRepositoryCustom<Checkout> implements CheckoutRepositoryCustom {
    public CheckoutRepositoryCustomImpl() {
        super("CO");
    }

    @Override
    public String createWhereQuery(Map<String, Object> queryParams, Map<String, Object> values) {
        StringBuilder sql = new StringBuilder(" where 1 = 1 ");
        List<String> queryByClass = this.getQueryParamByClass(this.searchType);
        queryByClass.forEach((item) -> {
            if (queryParams.containsKey(item)) {
                String tmp = item.replace(".", "");

                if(item.equalsIgnoreCase("user.id")){
                    User user = (User) queryParams.get(item);
                    sql.append(" and " + this.asName + "." + item);
                    if(user.getId() == null){
                        sql.append( " is null");
                    }else{
                        sql.append(" = :" + tmp);
                        values.put(tmp,user.getId());
                    }
                }else{
                    sql.append(" and " + this.asName + "." + item + " = :" + tmp);
                    values.put(tmp, queryParams.get(item));
                }
            }

        });

        return sql.toString();
    }
}
