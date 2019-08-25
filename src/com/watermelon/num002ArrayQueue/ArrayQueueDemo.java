package com.watermelon.num002ArrayQueue;

import java.util.Scanner;

/**
 * 数组模拟队列
 * 注意:	目前数组使用一次就不能用， 没有达到复用的效果
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQuque arrayQuque = new ArrayQuque(3);
        Scanner scanner = new Scanner(System.in);
        boolean finish = true;
        char input = ' ';
        while (finish) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            input = scanner.next().charAt(0);
            try {
                switch (input) {
                    case 'a': {
                        System.out.println("请输入一个数字:");
                        int value = scanner.nextInt();

                        arrayQuque.addQueue(value);
                        break;
                    }

                    case 's': {
                        arrayQuque.showQueue();
                        break;
                    }
                    case 'g': {
                        int head = arrayQuque.getQueue();
                        System.out.println("取出头部,取出数据为:");
                        System.out.println(head);
                        break;
                    }
                    case 'h': {
                        int head = arrayQuque.headQueue();
                        System.out.println("头部为:");
                        System.out.println(head);
                        break;
                    }
                    case 'e': {
                        finish = false;
                        break;
                    }
                    default: {
                        System.out.println("暂不支持此功能");
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }


}


class ArrayQuque {
    private int front;// 队列头
    private int rear;// 队列尾
    private int maxSize;//表示数组的最大容量
    private int[] arr;// 该数据用于存放数据,  模拟队列

    public ArrayQuque(int size) {
        this.arr = new int[size];
        this.front = -1;
        this.maxSize = size;
        this.rear = -1;
    }

    public boolean isFull() {
        return this.rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return this.front == this.rear;
    }

    public void addQueue(int value) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        this.rear = this.rear + 1;
        arr[this.rear] = value;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        this.front = this.front + 1;
        int value = arr[this.front];
        return value;
    }

    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        return arr[this.front + 1];
    }

    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("a[%d]=%d\n", i, arr[i]);
        }
    }
}