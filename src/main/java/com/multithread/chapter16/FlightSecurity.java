package com.multithread.chapter16;

/**
 * 登机检查
 *
 * @author zt1994 2020/5/18 21:06
 */
public class FlightSecurity {

    private int count = 0;

    /**
     * 登机牌
     */
    private String boardingPass = "null";

    /**
     * 身份证
     */
    private String idCard = "null";

    public synchronized void pass(String boardingPass, String idCard) {
        this.boardingPass = boardingPass;
        this.idCard = idCard;
        this.count++;
        check();
    }

    /**
     * 检验登机信息
     */
    private void check() {
        if (boardingPass.charAt(0) != idCard.charAt(0)) {
            throw  new RuntimeException("====Exception====" + toString());
        }
        System.out.println("检查通过");
    }


    @Override
    public String toString() {
        return "The " + count + " passengers,boardingPass [" + boardingPass + "],idCard [" + idCard + "]";
    }
}
