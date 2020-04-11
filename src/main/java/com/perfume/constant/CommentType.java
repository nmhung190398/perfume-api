package com.perfume.constant;

public enum CommentType {
    NEWS("news"),PRODUCT("product");

    private String _value;
    CommentType(String value) {
        this._value = value;
    }

    public String value(){
        return _value;
    }

}
