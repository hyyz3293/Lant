package com.jack.lant.ui.Data;

public class BankUtils {

    public static BankEntity messageBank(String phone, String msg) {
        BankEntity bankEntity = new BankEntity();

        try {
            switch (phone) {
                case "95588": {

                    int codeIndexStart = msg.indexOf("号");
                    int codeIndexEnd = msg.indexOf("卡");
                    System.out.println(codeIndexStart + "-----" + codeIndexEnd);
                    String code = msg.substring(codeIndexStart + 1, codeIndexEnd);
                    System.out.println(code);

                    int moneyIndexStart = msg.indexOf(")");
                    if (moneyIndexStart == -1)
                        moneyIndexStart = msg.indexOf("收入");
                    int moneyIndexEnd = msg.lastIndexOf("元");
                    System.out.println(moneyIndexStart + "-----" + moneyIndexEnd);
                    String money = msg.substring(moneyIndexStart + 1, moneyIndexEnd);
                    System.out.println(money);


                    int nameIndexStart = msg.indexOf("【");
                    int nameIndexEnd = msg.indexOf("】");

                    String name = msg.substring(nameIndexStart + 1, nameIndexEnd);
                    System.out.println(nameIndexStart + "-----" + nameIndexEnd);
                    System.out.println(name);

                    bankEntity.phone = phone;
                    bankEntity.bankName = name;
                    bankEntity.code = code;
                    bankEntity.money = money;

                }
                break;

                case "95528": {
                    int codeIndexStart = msg.indexOf("号");
                    int codeIndexEnd = msg.indexOf("卡");
                    System.out.println(codeIndexStart + "-----" + codeIndexEnd);
                    String code = msg.substring(codeIndexStart + 1, codeIndexEnd);
                    System.out.println(code);

                    int moneyIndexStart = msg.indexOf("可用余额");
                    int moneyIndexEnd = msg.lastIndexOf("。");
                    System.out.println(moneyIndexStart + "-----" + moneyIndexEnd);
                    String money = msg.substring(moneyIndexStart + 4, moneyIndexEnd);
                    System.out.println(money);

                    int nameIndexStart = msg.indexOf("[");
                    int nameIndexEnd = msg.indexOf("]");

                    String name = msg.substring(nameIndexStart + 1, nameIndexEnd);
                    System.out.println(nameIndexStart + "-----" + nameIndexEnd);
                    System.out.println(name);

                    bankEntity.phone = phone;
                    bankEntity.bankName = name;
                    bankEntity.code = code;
                    bankEntity.money = money;
                }
                break;

                case "95561": {
                    int codeIndexStart = msg.indexOf("*");
                    int codeIndexEnd = msg.lastIndexOf("*");
                    System.out.println(codeIndexStart + "-----" + codeIndexEnd);
                    String code = msg.substring(codeIndexStart + 1, codeIndexEnd);
                    System.out.println(code);

                    int moneyIndexStart = msg.indexOf("汇入收入");
                    int moneyIndexEnd = msg.lastIndexOf("元，");
                    System.out.println(moneyIndexStart + "-----" + moneyIndexEnd);
                    String money = msg.substring(moneyIndexStart + 4, moneyIndexEnd);
                    System.out.println(money);

                    int nameIndexStart = msg.indexOf("[");
                    int nameIndexEnd = msg.indexOf("]");

                    String name = msg.substring(nameIndexStart + 1, nameIndexEnd);
                    System.out.println(nameIndexStart + "-----" + nameIndexEnd);
                    System.out.println(name);

                    bankEntity.phone = phone;
                    bankEntity.bankName = name;
                    bankEntity.code = code;
                    bankEntity.money = money;

                }
                break;

                case "106980096336": {
                    int codeIndexStart = msg.indexOf("你的");
                    int codeIndexEnd = msg.lastIndexOf("账号");
                    System.out.println(codeIndexStart + "-----" + codeIndexEnd);
                    String code = msg.substring(codeIndexStart + 2, codeIndexEnd);
                    System.out.println(code);

                    int moneyIndexStart = msg.indexOf("转入收入");
                    int moneyIndexEnd = msg.lastIndexOf("人民币");
                    System.out.println(moneyIndexStart + "-----" + moneyIndexEnd);
                    String money = msg.substring(moneyIndexStart + 4, moneyIndexEnd);
                    System.out.println(money);

                    int nameIndexStart = msg.indexOf("[");
                    int nameIndexEnd = msg.indexOf("]");

                    String name = msg.substring(nameIndexStart + 1, nameIndexEnd);
                    System.out.println(nameIndexStart + "-----" + nameIndexEnd);
                    System.out.println(name);

                    bankEntity.phone = phone;
                    bankEntity.bankName = name;
                    bankEntity.code = code;
                    bankEntity.money = money;
                }
                break;
                case "95533": {
                    int codeIndexStart = msg.indexOf("尾号");
                    int codeIndexEnd = msg.lastIndexOf("的");
                    System.out.println(codeIndexStart + "-----" + codeIndexEnd);
                    String code = msg.substring(codeIndexStart + 2, codeIndexEnd);
                    System.out.println(code);

                    int moneyIndexStart = msg.indexOf("人民币");
                    int moneyIndexEnd = msg.lastIndexOf("元,");
                    System.out.println(moneyIndexStart + "-----" + moneyIndexEnd);
                    String money = msg.substring(moneyIndexStart + 3, moneyIndexEnd);
                    System.out.println(money);

                    int nameIndexStart = msg.indexOf("[");
                    int nameIndexEnd = msg.indexOf("]");

                    String name = msg.substring(nameIndexStart + 1, nameIndexEnd);
                    System.out.println(nameIndexStart + "-----" + nameIndexEnd);
                    System.out.println(name);

                    bankEntity.phone = phone;
                    bankEntity.bankName = name;
                    bankEntity.code = code;
                    bankEntity.money = money;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }



        return bankEntity;

    }

}
