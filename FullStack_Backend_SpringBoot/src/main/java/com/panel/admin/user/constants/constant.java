package com.panel.admin.user.constants;

public enum constant {
    LEAD_ID("lead_id"),
    CUSTOMER_ID("customer_id"),
    PRODUCT_TYPE("product_type"),
    LOAN_ACCOUNT_NUMBER("loan_account_number");

    constant(String id) {
        this.id = id;
    }
    private String id;
}
