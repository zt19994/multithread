package com.multithread.book1.chapter02;

/**
 * stack size 测试
 *
 * @author zt1994 2020/3/2 20:45
 */
public class StackSizeTest {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("请输入stack size");
            System.exit(1);
        }

        ThreadGroup testGroup = new ThreadGroup("TestGroup");

        Runnable runnable = new Runnable() {
            final int MAX = Integer.MAX_VALUE;

            @Override
            public void run() {
                int i = 0;
                recurse(i);
            }

            private void recurse(int i) {
                System.out.println(i);
                if (i < MAX) {
                    recurse(i + 1);
                }
            }

        };

        Thread thread = new Thread(testGroup, runnable, "Test", Integer.parseInt(args[0]));
        thread.start();
    }
}
