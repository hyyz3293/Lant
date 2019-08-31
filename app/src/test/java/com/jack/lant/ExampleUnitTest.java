package com.jack.lant;

import com.blankj.utilcode.util.LogUtils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        ///assertEquals(4, 2 + 2);
        //尾号、到账金额、银行名称
//        String msg = "你尾号3441卡4月18快捷支付收入(退款xxxxxxxxx公司)500元【工商银行】";
        //String bankName = msg.s

//        int codeIndexStart = msg.indexOf("号");
//        int codeIndexEnd = msg.indexOf("卡");
//        System.out.println(codeIndexStart + "-----" + codeIndexEnd);
//        String code = msg.substring(codeIndexStart + 1, codeIndexEnd);
//        System.out.println(code);


//        int moneyIndexStart = msg.indexOf(")");
//        if (moneyIndexStart == -1)
//            moneyIndexStart = msg.indexOf("收入");
//        int moneyIndexEnd = msg.lastIndexOf("元");
//        System.out.println(moneyIndexStart + "-----" + moneyIndexEnd);
//        String money = msg.substring(moneyIndexStart + 1, moneyIndexEnd);
//        System.out.println(money);
//
//
//        int nameIndexStart = msg.indexOf("【");
//        int nameIndexEnd = msg.indexOf("】");
//
//        String name = msg.substring(nameIndexStart + 1, nameIndexEnd);
//        System.out.println(nameIndexStart + "-----" + nameIndexEnd);
//        System.out.println(name);

//        String msg = "你尾号344aa1卡4月18快捷支付收入(退款xxxxxxxxx公可用余额51100。【工商银行】";
//
//        int codeIndexStart = msg.indexOf("号");
//        int codeIndexEnd = msg.indexOf("卡");
//        System.out.println(codeIndexStart + "-----" + codeIndexEnd);
//        String code = msg.substring(codeIndexStart + 1, codeIndexEnd);
//        System.out.println(code);
//
//        int moneyIndexStart = msg.indexOf("可用余额");
//        int moneyIndexEnd = msg.lastIndexOf("。");
//        System.out.println(moneyIndexStart + "-----" + moneyIndexEnd);
//        String money = msg.substring(moneyIndexStart + 4, moneyIndexEnd);
//        System.out.println(money);
//
//        int nameIndexStart = msg.indexOf("【");
//        int nameIndexEnd = msg.indexOf("】");
//
//        String name = msg.substring(nameIndexStart + 1, nameIndexEnd);
//        System.out.println(nameIndexStart + "-----" + nameIndexEnd);
//        System.out.println(name);




//        String msg = "你尾号*5371*卡4月18快捷支付(退款xxxxxxxxx公可用汇入收入5110110元。【工商银行】";
//
//        int codeIndexStart = msg.indexOf("*");
//        int codeIndexEnd = msg.lastIndexOf("*");
//        System.out.println(codeIndexStart + "-----" + codeIndexEnd);
//        String code = msg.substring(codeIndexStart + 1, codeIndexEnd);
//        System.out.println(code);
//
//        int moneyIndexStart = msg.indexOf("汇入收入");
//        int moneyIndexEnd = msg.lastIndexOf("元。");
//        System.out.println(moneyIndexStart + "-----" + moneyIndexEnd);
//        String money = msg.substring(moneyIndexStart + 4, moneyIndexEnd);
//        System.out.println(money);
//
//        int nameIndexStart = msg.indexOf("【");
//        int nameIndexEnd = msg.indexOf("】");
//
//        String name = msg.substring(nameIndexStart + 1, nameIndexEnd);
//        System.out.println(nameIndexStart + "-----" + nameIndexEnd);
//        System.out.println(name);





        String msg = "你的537xxxx1账号4月18快捷支付(退款xxxxxxxxx公可用转入收入5110110人民币。【工商银行】";

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

        int nameIndexStart = msg.indexOf("【");
        int nameIndexEnd = msg.indexOf("】");

        String name = msg.substring(nameIndexStart + 1, nameIndexEnd);
        System.out.println(nameIndexStart + "-----" + nameIndexEnd);
        System.out.println(name);






    }

}