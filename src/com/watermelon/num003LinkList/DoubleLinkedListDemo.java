package com.watermelon.num003LinkList;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode2 node0 = new HeroNode2(1, "李一", "liyi");
        HeroNode2 node1 = new HeroNode2(2, "李二", "lier");
        HeroNode2 node2 = new HeroNode2(3, "李三", "lisan");
        HeroNode2 node3 = new HeroNode2(4, "李四", "lisi");
        HeroNode2 node4 = new HeroNode2(5, "李五", "liwu");

        DoubleLinkList linkList1 = new DoubleLinkList();
        linkList1.add(node0);
        linkList1.add(node1);
        linkList1.add(node2);
        linkList1.add(node3);
        linkList1.add(node4);

        linkList1.list();

        System.out.println("-------------------开始执行update--------------------------");
        HeroNode2 node5 = new HeroNode2(3, "王三", "wangsan");
        linkList1.update(node5);
        linkList1.list();

        System.out.println("-------------------开始执行删除操作--------------------------");

        linkList1.delete(node5.getNo());
        linkList1.list();

        System.out.println("-------------------开始执行有序插入操作操作--------------------------");


        HeroNode2 node6 = new HeroNode2(2, "李二", "lier");
        HeroNode2 node7 = new HeroNode2(1, "李一", "liyi");
        HeroNode2 node8 = new HeroNode2(6, "李六", "liliu");
        HeroNode2 node9 = new HeroNode2(5, "李五", "liwu");
        HeroNode2 node10 = new HeroNode2(3, "李三", "lisan");

        DoubleLinkList linkList2 = new DoubleLinkList();
        linkList2.addByOrder(node6);
        linkList2.addByOrder(node7);
        linkList2.addByOrder(node8);
        linkList2.addByOrder(node9);
        linkList2.addByOrder(node10);

        linkList2.list();
    }

    static class DoubleLinkList {
        private HeroNode2 head = new HeroNode2(0, "", "");

        public HeroNode2 getHead() {
            return head;
        }

        public void add(HeroNode2 node) {
            HeroNode2 temp = head;
            while (true) {
                if (temp.next == null) {
                    temp.next = node;
                    node.prev = temp;
                    break;
                }
                temp = temp.next;
            }
        }

        public void addByOrder(HeroNode2 node) {
            HeroNode2 temp = head;
            while (true) {
                if (temp == null) {
                    break;
                }

                if (temp.next == null) {
                    node.prev = temp;
                    temp.next = node;
                    break;
                } else if (temp.next.getNo() > node.getNo()) {
                    node.prev = temp;
                    node.next = temp.next;
                    temp.next.prev = node;
                    temp.next = node;
                    break;
                }
                temp = temp.next;
            }
        }

        public void delete(int no) {
            HeroNode2 temp = head.next;
            if(temp == null){
                System.out.println("此链表为空链表");
                return ;
            }

            while(true){
                if(temp == null){
                    break;
                }

                if(temp.getNo() == no){
                    temp.prev.next=temp.next;
                    temp.next.prev=temp.prev;
                    break;
                }
                temp = temp.next;
            }
        }

        public void update(HeroNode2 node) {

            HeroNode2 temp = head.next;
            while (true) {
                if (temp == null) {
                    break;
                }
                if (temp.getNo() == node.getNo()) {
                    temp.setName(node.getName());
                    temp.setNickName(node.getNickName());
                }
                temp = temp.next;
            }

        }

        public void list() {
            HeroNode2 temp = head;
            while (true) {
                if (temp == null) {
                    break;
                }
                System.out.println(temp);
                temp = temp.next;
            }
        }
    }

    static class HeroNode2 {
        private int no;
        private String name;
        private String nickName;
        private HeroNode2 next;
        private HeroNode2 prev;

        public HeroNode2(int no, String name, String nickName) {
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

        public HeroNode2 getNext() {
            return next;
        }

        public void setNext(HeroNode2 next) {
            this.next = next;
        }

        public HeroNode2 getPrev() {
            return prev;
        }

        public void setPrev(HeroNode2 prev) {
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "HeroNode2{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickName='" + nickName + '\'' +
                    '}';
        }
    }
}
