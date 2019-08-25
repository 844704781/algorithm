package com.watermelon.num003LinkList;

import java.util.Stack;

public class SingleLinkListDemo {

    public static void main(String[] args) {
        SingleLinkList singleLinkList1 = new SingleLinkList();

        HeroNode node1 = new HeroNode(10, "张三", "zhangsan");
        HeroNode node2 = new HeroNode(8, "李四", "lisi");
        HeroNode node3 = new HeroNode(7, "王老五", "wanglaowu");
        HeroNode node4 = new HeroNode(4, "小明", "xiaoming");
        HeroNode node5 = new HeroNode(1, "小黄", "xiaohuang");

        //        singleLinkList.add(node1);
        //        singleLinkList.add(node2);
        //        singleLinkList.add(node3);
        //        singleLinkList.add(node4);
        //        singleLinkList.add(node5);

        singleLinkList1.addByOrder(node2);
        singleLinkList1.addByOrder(node1);
        singleLinkList1.addByOrder(node4);
        singleLinkList1.addByOrder(node3);
        singleLinkList1.addByOrder(node5);

        singleLinkList1.list();
        System.out.println("-------------left-----------------");

        SingleLinkList singleLinkList2 = new SingleLinkList();
        HeroNode node6 = new HeroNode(9, "张九", "zhangjiu");
        HeroNode node7 = new HeroNode(6, "李六", "liliu");
        HeroNode node8 = new HeroNode(5, "王五", "wangwu");
        HeroNode node9 = new HeroNode(3, "小三", "xiaosan");
        HeroNode node10 = new HeroNode(2, "小二", "xiaoer");


        singleLinkList2.addByOrder(node6);
        singleLinkList2.addByOrder(node7);
        singleLinkList2.addByOrder(node8);
        singleLinkList2.addByOrder(node9);
        singleLinkList2.addByOrder(node10);

        singleLinkList2.list();
        System.out.println("-------------right-----------------");

        SingleLinkList.merge(singleLinkList1,singleLinkList2).list();


        //SingleLinkList.revertPrint(singleLinkList.getHead());
        // SingleLinkList.revertList(singleLinkList.getHead());
        //singleLinkList.list();
        //        HeroNode newHero = new HeroNode(5, "小红", "xionghong");
        //        singleLinkList.update(newHero);
        //        singleLinkList.list();
        //
        //        singleLinkList.delete(newHero);
        //        singleLinkList.delete(node1);
        //        singleLinkList.list();
        //
        //        System.out.println(SingleLinkList.getLength(singleLinkList.getHead()));
        //
        //        HeroNode node = SingleLinkList.findLastIndexNode(singleLinkList.getHead(), 3);
        //        System.out.println(node);
    }
}

class SingleLinkList {
    private final HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return this.head;
    }

    /**
     * 合并两个有序的单链表，合并之后的链表依然有序
     * left为小到大的链表
     * right为从小到大的链表
     *
     * @return
     */
    public static SingleLinkList merge(SingleLinkList leftLinkList, SingleLinkList rightLinkList) {
        /**
         * 如果存在head.next为null的，则直接返回另外一个，如果两个head.next都为null，则无需合并
         */
        if (leftLinkList == null && rightLinkList == null) {
            System.out.println("链表为null");
            return null;
        }

        if (leftLinkList == null) {
            //如果时无序，则可以使用插入排序进行排序
            if(rightLinkList.getHead().getNext() == null){
                return null;
            }
            return rightLinkList;
        }

        if (rightLinkList == null) {
            if(leftLinkList.getHead().getNext() == null){
                return null;
            }

            return leftLinkList;
        }


        SingleLinkList singleLinkList = new SingleLinkList();

        HeroNode leftHead = leftLinkList.getHead();
        //左节点的当前遍历节点
        HeroNode leftTemp = leftHead.getNext();
        while(leftTemp!=null){
            //复制当前节点的数据
            HeroNode curr = new HeroNode(leftTemp.getNo(), leftTemp.getName(), leftTemp.getNickName());
            //将当前节点的数据有序的插入到新链表
            singleLinkList.addByOrder(curr);
            leftTemp = leftTemp.getNext();
        }

        HeroNode rightHead = rightLinkList.getHead();
        //右节点的当前遍历节点
        HeroNode rightTemp = rightHead.getNext();
        while(rightTemp!=null){
            //复制当前节点的数据
            HeroNode curr = new HeroNode(rightTemp.getNo(), rightTemp.getName(), rightTemp.getNickName());
            //将当前节点的数据有序的插入到新链表
            singleLinkList.addByOrder(curr);
            rightTemp = rightTemp.getNext();
        }

        return singleLinkList;
    }

    /**
     * 将单链表从尾到头打印
     */
    public static void revertPrint(HeroNode head) {
        if (head.getNext() == null) {
            System.out.println("链表为空");
            return;
        }

        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.getNext();
        while (cur != null) {
            stack.push(cur);
            cur = cur.getNext();
        }

        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }

    }

    /**
     * 将单链表反转
     *
     * @param head
     */
    public static void revertList(HeroNode head) {
        //只有一个头节点，或者只有一个节点则无需反转

        if (head.getNext() == null || head.getNext().getNext() == null) {
            return;
        }

        HeroNode cur = head.getNext(); //当前节点
        HeroNode next = null; //当前节点的下一个节点
        HeroNode revertHead = new HeroNode(0, "", "");//反转时的临时头节点

        while (cur != null) {
            next = cur.getNext(); //动态赋值当前节点的下一个节点
            cur.setNext(revertHead.getNext()); //将当前节点的下一个节点设置为头节点的下一个节点，也就是将当前节点插入到最前面
            revertHead.setNext(cur);//将反转头节点的下一个节点设置为刚刚要插入到最前面的那个节点
            cur = next;//将当前节点后移
        }

        //将头节的下一个节点只想反转头节点的下一个节点。
        head.setNext(revertHead.getNext());
    }

    /**
     * 获取单链表的节点个数(不统计头节点)
     *
     * @param head
     * @return
     */
    public static int getLength(HeroNode head) {
        if (head.getNext() == null) {
            return 0;
        }

        HeroNode temp = head.getNext();
        int length = 0;

        while (true) {
            if (temp == null) {
                break;
            }
            length = length + 1;
            temp = temp.getNext();
        }

        return length;
    }

    /**
     * 查找单链表单倒数第k个节点
     *
     * @param head
     * @param index
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.getNext() == null) {
            return null;
        }

        int length = getLength(head);

        if (index <= 0 || index > length) {
            return null;
        }
        int k = length - index;

        HeroNode temp = head.getNext();
        for (int i = 0; i < k; i++) {
            temp = temp.getNext();
        }

        return temp;
    }

    public void delete(HeroNode currentNode) {
        HeroNode temp = head;
        boolean flag = false;

        while (true) {
            if (temp.getNext() == null) {
                break;
            }

            if (temp.getNext().getNo() == currentNode.getNo()) {
                temp.setNext(temp.getNext().getNext());
                flag = true;
                break;
            }

            temp = temp.getNext();

        }

        if (!flag) {
            System.out.println("没有找到要删除的节点");
        } else {
            System.out.println("删除成功");
        }
    }

    public void add(HeroNode node) {
        /**
         * 追加到链表尾部
         */
        HeroNode temp = head;
        while (true) {
            if (temp.getNext() == null) {
                temp.setNext(node);
                break;
            }
            temp = temp.getNext();
        }
    }

    public void update(HeroNode newHero) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.getNo() == newHero.getNo()) {
                flag = true;
                break;
            }
            if (temp.getNext() == null) {
                break;
            }

            temp = temp.getNext();

        }

        if (!flag) {
            System.out.printf("没有找到此编号对应的英雄:编号%d\n", newHero.getNo());
        } else {
            temp.setName(newHero.getName());
            temp.setNickName(newHero.getNickName());
        }
    }

    public void addByOrder(HeroNode node) {
        HeroNode temp = head;
        boolean flag = false;

        while (true) {
            if (temp.getNext() == null) {
                break;
            }

            //如果当前插入的no小于当前遍历的节点的后一个元素,则需要插入
            if (node.getNo() < temp.getNext().getNo()) {
                break;
            }

            if (temp.getNo() == node.getNo()) {
                flag = true;
                break;
            }

            temp = temp.getNext();
        }

        if (flag) {
            System.out.printf("该编号的英雄已经存在，编号:%d.", temp.getNo());
        } else {
            node.setNext(temp.getNext());
            temp.setNext(node);
        }
    }

    public void list() {
        HeroNode temp = head;
        while (true) {
            System.out.println(temp.toString());

            if (temp.getNext() == null) {
                return;
            }
            temp = temp.getNext();

        }
    }
}

class HeroNode {
    /**
     * 序号，可用于排序
     */
    private int no;
    /**
     * 节点名称
     */
    private String name;
    /**
     * 节点昵称
     */
    private String nickName;
    /**
     * 指向下一个节点
     */
    private HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

