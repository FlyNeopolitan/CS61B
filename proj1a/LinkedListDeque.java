public class LinkedListDeque<T>{
    private static class node<T>{
          T item;
          node<T> next;
          node<T> prev;
          node(T x,node<T> y,node<T> z){
             item=x;
             prev=y;
             next=z;
        }
    }
    node<T> sentinel;
    int size;
    LinkedListDeque(){
        sentinel=new node<T>(T 0,sentinel,sentinel);
        size=0;
    }
    public void addFirst(T newitem){
        sentinel.next=new node<T>(newitem,sentinel,sentinel.next);
        sentinel.next.next.prev=sentinel.next;
        size+=1;
    }
    public void addLast(T newitem){
        sentinel.prev.next=new node<T>(newitem,sentinel.prev,sentinel);
        sentinel.prev=sentinel.prev.next;
        size+=1;
    }
    public boolean isEmpty(){
        if(size==0) {
            return true;
        }
        else{
            return false;
        }
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        node<T> q=sentinel;
        while(q.next!=sentinel){
            q=q.next;
            System.out.print(q.item+" ");
        }
    }
    public T removeFirst(){
        if(size==0){
            return null;
        }
        else{
            T firstItem=sentinel.next.item;
            sentinel.next=sentinel.next.next;
            sentinel.next.prev=sentinel;
            return firstItem;
        }
    }
    public T removeLast(){
        if(size==0){
            return null;
        }
        else{
            T lastItem=sentinel.prev.item;
            sentinel.prev=sentinel.prev.prev;
            sentinel.prev.next=sentinel;
            return lastItem;
        }
    }
    public T get(int index){
        if(size==0){
            return null;
        }
        else{
            
        }
    }
}