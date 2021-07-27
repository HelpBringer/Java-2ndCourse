//import java.lang.Comparable;


public class Tree <T extends Comparable<T>> {
    class Node {
        T data;
        Node left;
        Node right;
        Node(T value){
            data=value;
            left=null;
            right=null;
        }
    }
    Node root;
    Tree(){
        root=null;
    }
    Tree(T value){
        root=new Node(value);
    }
    boolean find(T b){
        Node a=root;
        while(a!=null && a.data.compareTo(b)!=0){
            if(a.data.compareTo(b)>0){
                a=a.left;
            }
            else{
                a=a.right;
            }
        }
        return a != null;
    }
    public void lvr(){
        System.out.print("lvr ");
        lvr(this.root);
    }
    private void lvr(Node a){
        if(a!=null){
            lvr(a.left);
            System.out.print(a.data+" ");
            lvr(a.right);
        }
    }

    void add(T b){
        Node a=root;
        while(a!=null && a.data.compareTo(b)!=0){
            if(a.data.compareTo(b)>0){
                if(a.left==null){
                    a.left=new Node(b);
                    break;
                }
                a=a.left;
            }
            else{
                if(a.right==null){
                    a.right=new Node(b);
                    break;
                }
                a=a.right;
            }
        }
    }

   private T findSmallestValue(Node root) {
       return root.left == null ? root.data : findSmallestValue(root.left);
   }



   public void delete(T value) {
       Node current = root;
       while (current.data.compareTo(value)!=0) {
           if (current.data.compareTo(value)>0)
           {
               current = current.left;
           } else
           {
               current = current.right;
           }

       }

      deleteRecursive(current, value);
   }

    private Node deleteRecursive(Node current, T value) {
        if (current == null) {
            return null;
        }

        if (value == current.data) {
            // no children
            if (current.left == null && current.right == null) {
                Node parent= root;
                Node tmp=root;
                while (tmp.data.compareTo(value)!=0) {
                    parent=tmp;
                    if (tmp.data.compareTo(value)>0)
                    {
                        tmp = tmp.left;
                    } else
                    {
                        tmp = tmp.right;
                    }

                }
                if (parent.left.data.compareTo(value)==0){
                    parent.left=null;
                }
                else{
                    parent.right=null;
                }
                return parent;
            }

            // only 1 child
            if (current.right == null) {
                current.data=current.left.data;
                current.right=current.left.right;
                current.left=current.left.left;
                return current/*.left*/;
            }

            if (current.left == null) {
                current.data=current.right.data;
                current.left=current.right.left;
                current.right=current.right.right;
                return current/*.right*/;
            }

            // 2 children
            T smallestValue = findSmallestValue(current.right);
            //current.right = deleteRecursive(current.right, smallestValue);

            Node parrent= root;
            Node tmp=root;
            while (tmp.data.compareTo(smallestValue)!=0) {
                parrent=tmp;
                if (tmp.data.compareTo(smallestValue)>0)
                {
                    tmp = tmp.left;
                } else
                {
                    tmp = tmp.right;
                }
            }
            if   ((parrent.left.left == null && parrent.left.right == null) || (parrent.right.left == null && parrent.right.right == null)) {
                if (parrent.left!= null && parrent.left.data.compareTo(smallestValue)==0){
                    parrent.left=null;
                    current.data=smallestValue;
                }
                if(parrent.right!=null && parrent.right.data.compareTo(smallestValue)==0){
                    parrent.right=null;
                    current.data=smallestValue;
                }
            }
            else {
                current.data = smallestValue;
                current.right = deleteRecursive(current.right, smallestValue);
            }
            return current;
        }
        if (current.data.compareTo(value)>0) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }

        current.right = deleteRecursive(current.right, value);
        return current;
    }

    public void lrv(){
        System.out.print("lrv ");
        lrv(this.root);
    }
    private void lrv(Node a){
        if(a!=null){
            lrv(a.left);
            lrv(a.right);
            System.out.print(a.data+" ");
        }
    }
    public void vlr(){
        System.out.print("vlr ");
        vlr(this.root);
    }
    private void vlr(Node a){
        if(a!=null){
            System.out.print(a.data.toString()+" ");
            vlr(a.left);
            vlr(a.right);
        }
    }

    public static void main(String[] args){
        Tree<Integer> example = new Tree(12);
        for( int i=1;i<5;i++){
            if (i/2==1) {
                example.add(12 - i);
            }
            else{
                example.add(12+i);
            }
        }
        example.add(11);
        example.add(20);
        example.add(14);
        example.add(15);
        example.add(3);
        example.add(6);
        example.add(8);
        example.add(7);
        example.add(5);
        example.add(2);
        example.add(17);
        example.vlr();
        System.out.println();
        example.delete(13);
        example.delete(12);
        example.delete(5);
        example.lvr();
        System.out.print("         ");
        example.lrv();
        System.out.print("         ");
        example.vlr();
        System.out.print("         ");

        System.out.print(example.find(11));
        System.out.print("         ");
        example.delete(3);
        example.delete(16);
        System.out.println();
        example.vlr();

        Student ex= new Student(11,"Ivan","FAMCS");
        Tree<Student> example1= new Tree(ex);
        ex= new Student(13);
        example1.add(ex);
        Student stud;
        int j=-1;
        for (int i=0; i<7;i++){
            stud=new Student(13+(i*2)*j);
            if(j==-1){
                j=1;
            }
            else{
                j=-1;
            }
            example1.add(stud);
        }
        System.out.println();
        example1.lvr();
        System.out.print("         ");
        example1.vlr();
    }

}
