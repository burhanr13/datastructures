import java.util.Iterator;

import datastructures.*;

class Main {
    public static void main(String[] args) {

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        int[] nums = { 5,2,7,1,4,6,8 };

        for (int i : nums)
            tree.add(i);

        System.out.println(tree);
        tree.inOrder(e -> System.out.print(e));
        System.out.println();
        tree.preOrder(e -> System.out.print(e));
        System.out.println();
        tree.postOrder(e -> System.out.print(e));
        System.out.println();
        for (int i : tree)
            System.out.print(i);
        














        List<Integer> alist = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            alist.add(i);
        }
        System.out.println(alist);
        System.out.println(alist.indexOf(4));
        alist.remove(4);
        System.out.println(alist.indexOf(4));
        System.out.println(alist.indexOf(3));
        for (int i = 0; i < 34; i++) {
            alist.add(700+i);
        }
        System.out.println(alist);
        System.out.println(alist.size());
        alist.remove(Integer.valueOf(735));

        List<Integer> blist = new ArrayList<>();
        while (!alist.isEmpty()) {
            blist.add(alist.remove((int) (Math.random() * alist.size())));
        }
        System.out.println(alist);
        System.out.println(blist);
        Iterator<Integer> it = blist.iterator();
        while (it.hasNext()) {
            if (it.next() % 2 == 0)
                it.remove();
        }
        System.out.println(blist);
        blist.sort((a, b) -> a.compareTo(b));
        System.out.println(blist);
        ArrayList<Integer> clist = new ArrayList<>();
        int[] n = { 1,2,5,20,4,7,3,2,10,20 };
        for (int i : n) {
            clist.add(i);
        }
        clist.sort((a, b) -> a.compareTo(b));
        System.out.println(clist);
    }
}
