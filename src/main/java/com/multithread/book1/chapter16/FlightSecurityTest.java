package com.multithread.book1.chapter16;

/**
 * 登机检查测试
 *
 * 由于boardingPass 和 idCard 的赋值可能交叉，不能保证原子性操作
 *
 * @author zt1994 2020/5/18 21:16
 */
public class FlightSecurityTest {

    static class Passengers extends Thread {
        /**
         * 机场安检类
         */
        private final FlightSecurity flightSecurity;

        /**
         * 旅客的身份证
         */
        private final String idCard;

        /**
         * 旅客的登机牌
         */
        private final String boardingPass;

        /**
         * 构造旅客时传入身份证、登机牌以及机场安检类
         *
         * @param flightSecurity
         * @param idCard
         * @param boardingPass
         */
        public Passengers(FlightSecurity flightSecurity, String idCard, String boardingPass) {
            this.flightSecurity = flightSecurity;
            this.idCard = idCard;
            this.boardingPass = boardingPass;
        }

        @Override
        public void run() {
            while (true) {
                // 旅客不断的过安检
                flightSecurity.pass(boardingPass, idCard);
            }
        }
    }


    public static void main(String[] args) {
        final FlightSecurity flightSecurity = new FlightSecurity();
        new Passengers(flightSecurity, "A123", "AF123").start();
        new Passengers(flightSecurity, "B123", "BF123").start();
        new Passengers(flightSecurity, "C123", "CF123").start();
    }
}
