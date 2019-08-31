package com.jack.lant.ui.Data;

public class BankEntity {
    public BankEntity() {

    }


    public String phone;
    public String code;
    public String money;
    public String bankName;

    public BankEntity(String phone, String code, String money, String bankName) {
        this.phone = phone;
        this.code = code;
        this.money = money;
        this.bankName = bankName;
    }
}
