package com.jack.lant.ui.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                case "95580": {
                    int codeIndexStart = msg.indexOf("尾号");
                    int codeIndexEnd = msg.lastIndexOf("账户");
                    System.out.println(codeIndexStart + "-----" + codeIndexEnd);
                    String code = msg.substring(codeIndexStart + 2, codeIndexEnd);
                    System.out.println(code);

                    int moneyIndexStart = msg.indexOf("提现金额");
                    int moneyIndexEnd = msg.lastIndexOf("元，");
                    System.out.println(moneyIndexStart + "-----" + moneyIndexEnd);
                    String money = msg.substring(moneyIndexStart + 4, moneyIndexEnd);
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
                case "106980096319": {
                    //TODO JACK 厦门 银行 支付宝有点问题

                    int moneyIndexStart222 = msg.indexOf("转入");
                    int moneyIndexEnd222 = msg.indexOf("元,");

                    String money222 = msg.substring(moneyIndexStart222 + 2, moneyIndexEnd222);

                    if (isContainChinese(money222)) {
                        int codeIndexStart = msg.indexOf("尾数为");
                        int codeIndexEnd = msg.lastIndexOf("的账号");
                        System.out.println(codeIndexStart + "-----" + codeIndexEnd);
                        String code = msg.substring(codeIndexStart + 3, codeIndexEnd);
                        System.out.println(code);

                        int moneyIndexStart = msg.indexOf("转入");
                        int moneyIndexEnd = moneyIndexStart + 2 + 4;
                        System.out.println(moneyIndexStart + "-----" + moneyIndexEnd);

                        int numberAas = -1;
                        for (int i = 0; i < 10; i++) {
                            numberAas = msg.indexOf(i + "", moneyIndexEnd);
                            if (numberAas > 0)
                                break;
                        }

                        System.out.println( "``````````````````````````````" + numberAas);
                        int moneyIndexStart2 = msg.indexOf("元,");
                        String money = msg.substring(numberAas, moneyIndexStart2);
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
                    } else {
                        int codeIndexStart = msg.indexOf("尾数为");
                        int codeIndexEnd = msg.lastIndexOf("的账号");
                        System.out.println(codeIndexStart + "-----" + codeIndexEnd);
                        String code = msg.substring(codeIndexStart + 3, codeIndexEnd);
                        System.out.println(code);

                        int moneyIndexStart = msg.indexOf("转入");
                        int moneyIndexEnd = msg.indexOf("元,");
                        System.out.println(moneyIndexStart + "-----" + moneyIndexEnd);
                        String money = msg.substring(moneyIndexStart + 2, moneyIndexEnd);
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



                }
                break;
                case "95555": {
                    int codeIndexStart = msg.indexOf("您账户");
                    int codeIndexEnd = msg.lastIndexOf("于");
                    System.out.println(codeIndexStart + "-----" + codeIndexEnd);
                    String code = msg.substring(codeIndexStart + 3, codeIndexEnd);
                    System.out.println(code);

                    int moneyIndexStart = msg.indexOf("收款人民币");
                    int moneyIndexEnd =  msg.indexOf("，余额");
                    System.out.println(moneyIndexStart + "-----" + moneyIndexEnd);
                    String money = msg.substring(moneyIndexStart + 5, moneyIndexEnd);
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

                }break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }



        return bankEntity;

    }

    /**
     * 判断字符串中是否包含中文
     * @param str
     * 待校验字符串
     * @return 是否为中文
     * @warn 不能校验是否为中文标点符号
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

}
