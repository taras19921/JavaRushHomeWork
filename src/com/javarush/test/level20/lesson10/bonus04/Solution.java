package com.javarush.test.level20.lesson10.bonus04;

import java.io.Serializable;
import java.util.*;

/* Свой список
Посмотреть, как реализован LinkedList.
Элементы следуют так: 1->2->3->4  и так 4->3->2->1
По образу и подобию создать Solution.
Элементы должны следовать так:
1->3->7->15
    ->8...
 ->4->9
    ->10
2->5->11
    ->12
 ->6->13
    ->14
Удалили 2 и 9
1->3->7->15
    ->8
 ->4->10
Добавили 16,17,18,19,20 (всегда добавляются на самый последний уровень к тем элементам, которые есть)
1->3->7->15
       ->16
    ->8->17
       ->18
 ->4->10->19
        ->20
Удалили 18 и 20
1->3->7->15
       ->16
    ->8->17
 ->4->10->19
Добавили 21 и 22 (всегда добавляются на самый последний уровень к тем элементам, которые есть.
Последний уровень состоит из 15, 16, 17, 19. 19 последний добавленный элемент, 10 - его родитель.
На данный момент 10 не содержит оба дочерних элемента, поэтому 21 добавился к 10. 22 добавляется в следующий уровень.)
1->3->7->15->22
       ->16
    ->8->17
 ->4->10->19
        ->21

Во внутренней реализации элементы должны добавляться по 2 на каждый уровень
Метод getParent должен возвращать элемент, который на него ссылается.
Например, 3 ссылается на 7 и на 8, т.е.  getParent("8")=="3", а getParent("13")=="6"
Строки могут быть любыми.
При удалении элемента должна удаляться вся ветка. Например, list.remove("5") должен удалить "5", "11", "12"
Итерироваться элементы должны в порядке добавления
Доступ по индексу запрещен, воспользуйтесь при необходимости UnsupportedOperationException
Должно быть наследование AbstractList<String>, List<String>, Cloneable, Serializable
Метод main в тестировании не участвует
*/
public class Solution extends AbstractList<String> implements Cloneable, Serializable
{
    private static final long serialVersionUID = -2229529516148899423L;

    public static void main(String[] args)
    {
        List<String> list = new Solution();
        for (int i = 1; i < 16; i++)
        {
            list.add(String.valueOf(i));
        }
        System.out.println("Expected 3, actual is " + ((Solution) list).getParent("8"));
        list.remove("5");
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("11"));

    }

    private int size=0;

    Node root = new Node ();

    public Solution() {
        super();
    }

    public class Node implements Serializable
    {
        private static final long serialVersionUID = 2745814552751892045L;
        String element;
        Node parent;
        Node leftChild;
        Node rightChild;



        public Node()
        {       }

        public Node(String element)
        {
            this.element = element;
        }

        public Node(String element, Node leftChild, Node rightChild)
        {
            this.element = element;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public Node clone() {
            Node left = null;
            Node right = null;

            if (this.leftChild != null) {
                left = this.leftChild.clone();
                left.parent = this;
            }
            if (this.rightChild != null) {
                right = this.rightChild.clone();
                right.parent = this;
            }
            return new Node(element, left, right);
        }
    }

    public boolean add(String name){

        if (name==null)
        {
            return false;
        }
        Node top = root;
        Node current = new Node(name);
        Queue<Node> queue = new LinkedList<> ();

        do{
            if (top.leftChild!=null) queue.add(top.leftChild);
            else
            {
                top.leftChild = current;

                current.parent = top;
                size++;
                modCount++;

                queue.clear();
                return true;
            }
            if (top.rightChild!=null) queue.add(top.rightChild);
            else
            {
                top.rightChild = current;

                current.parent = top;
                size++;
                modCount++;

                queue.clear();
                return true;
            }
            if (!queue.isEmpty()) top=queue.poll();
        }while (!queue.isEmpty());
        queue.clear();
        return true;
    }

    public boolean addAll(Collection<? extends String> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        for (String t : c)
        {
            add(t);
            modCount++;
        }
        return numNew != 0;
    }

    public boolean remove(Object o)
    {
        if (this.contains(o))
        {
            Node top = root;
            Node search = null;
            String str = (String) o;
            Queue<Node> queue = new LinkedList<>();
            queue.add(top);
            do
            {
                if (!queue.isEmpty()) top = queue.poll();
                if (top.element != null)
                {
                    if (top.element.equals(str))
                    {
                        search = top;
                        break;
                    }
                }
                if (top.leftChild != null) queue.add(top.leftChild);
                if (top.rightChild != null) queue.add(top.rightChild);

            }
            while (!queue.isEmpty());
            queue.clear();
            top = search;
            search = top.parent;
            if (search != null && search.leftChild == top)
            {
                search.leftChild = null;

            } else if (search != null && search.rightChild == top)
            {
                search.rightChild = null;
            }
            queue.add(top);
            do
            {
                top = queue.poll();
                if (top.leftChild != null) queue.add(top.leftChild);
                if (top.rightChild != null) queue.add(top.rightChild);

                top = null;
                modCount++;
                size--;

            }
            while (!queue.isEmpty());
            queue.clear();
            return true;
        }
        else
            return false;
    }

    public boolean removeAll(Collection<?> c)
    {
        for (Object o : c)
        {
            remove(o);
            modCount++;
        }
        return true;
    }

    public void clear() {
        Node top = root;
        Queue<Node> queue = new LinkedList<> ();
        queue.add(top);
        do{
            if (top.leftChild!=null) queue.add(top.leftChild);
            if (top.rightChild!=null) queue.add(top.rightChild);
            top.element = null;
            top.leftChild = null;
            top.rightChild = null;
            top.parent = null;
            top=null;
            modCount++;
            if (!queue.isEmpty()) top=queue.poll();
        }while (!queue.isEmpty());
    }

    public String getParent(String value) {
        Node top = root;
        String result=null;
        Queue<Node> queue = new LinkedList<> ();
        queue.add(top);
        do{
            if (top.element !=null)
            {
                if (top.element.equals(value))
                {
                    result = top.parent.element;
                    break;
                }
            }
            if (top.leftChild!=null) queue.add(top.leftChild);
            if (top.rightChild!=null) queue.add(top.rightChild);
            if (!queue.isEmpty()) top=queue.poll();
        }while (!queue.isEmpty());
        return result;
    }

    public boolean contains(String value) {
        Node top = root;
        boolean result = false;
        Queue<Node> queue = new LinkedList<> ();
        queue.add(top);
        do{
            top=queue.poll();
            if (top.element !=null)
            {
                if (top.element.equals(value))
                {
                    result = true;
                    break;
                }
            }
            if (top.leftChild!=null) queue.add(top.leftChild);
            if (top.rightChild!=null) queue.add(top.rightChild);

        }while (!queue.isEmpty());
        return result;
    }

    public Solution clone()
    {
        Solution res = new Solution();
        res.root = this.root.clone();
        res.size = this.size();
        return res;
    }

    public boolean retainAll(Collection<?> c) {
        Node top = root;
        boolean result = false;
        Queue<Node> queue = new LinkedList<> ();
        queue.add(top);
        Solution res = new Solution();
        do{
            if (!queue.isEmpty()) top=queue.poll();
            if (top.element !=null)
            {
                if (c.contains(top.element))
                {
                    if (!res.contains(top.element))
                    {
                        res.add(top.element);
                        modCount++;
                    }

                }
            }
            if (top.leftChild!=null) queue.add(top.leftChild);
            if (top.rightChild!=null) queue.add(top.rightChild);

        }while (!queue.isEmpty());

        this.root = res.root;
        this.size = res.size();
        return result;
    }

    public boolean containsAll(Collection<?> c) {
        int k = 0;
        for (Object o : c)
        {
            if (contains(o))
            {
                k++;
            }
        }
        if (c.size()==k)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Object[] toArray() {
        ArrayList<Object> sss = new ArrayList<>();
        Node top = root;
        Queue<Node> queue = new LinkedList<> ();
        queue.add(top);
        do{
            if (top.element !=null )
            {
                sss.add(top.element);
            }
            if (top.leftChild!=null) queue.add(top.leftChild);
            if (top.rightChild!=null) queue.add(top.rightChild);
            if (!queue.isEmpty()) top=queue.poll();
        }while (!queue.isEmpty());
        return sss.toArray();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Solution strings = (Solution) o;
        if (size != strings.size) return false;
        Node top = root;
        ArrayList<String> a1 = new ArrayList<>();
        ArrayList<String> a2 = new ArrayList<>();
        Queue<Node> queue = new LinkedList<> ();
        queue.add(top);
        do{
            if (top.element !=null && !a1.contains(top.element))
            {
                a1.add(top.element);
            }
            if (top.leftChild!=null) queue.add(top.leftChild);
            if (top.rightChild!=null) queue.add(top.rightChild);

            if (!queue.isEmpty()) top=queue.poll();
        }while (!queue.isEmpty());
        top = strings.root;
        queue.clear();
        queue.add(top);
        do{
            if (top.element !=null && !a2.contains(top.element))
            {
                a2.add(top.element);
            }
            if (top.leftChild!=null) queue.add(top.leftChild);
            if (top.rightChild!=null) queue.add(top.rightChild);
            if (!queue.isEmpty()) top=queue.poll();
        }while (!queue.isEmpty());
        if (!a1.equals(a2)) return false;
        return true;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + size;
        result = 31 * result + (root != null ? root.hashCode() : 0);
        return result;
    }




    public Iterator<String> iterator() {
        return new Itr();
    }

    public class Itr implements Iterator<String>  {
        Node top = root;

        Queue<Node> queue_fin = new LinkedList<> ();

        int expectedModCount = modCount;


        public Itr()
        {
            Queue<Node> queue = new LinkedList<> ();

            do{
                if (top.leftChild!=null)
                {
                    queue.add(top.leftChild);
                    if (!queue_fin.contains(top.leftChild))
                        queue_fin.add(top.leftChild);
                }
                if (top.rightChild!=null)
                {
                    queue.add(top.rightChild);
                    if (!queue_fin.contains(top.rightChild))
                        queue_fin.add(top.rightChild);
                }
                if (!queue.isEmpty()) top=queue.poll();
            }while (!queue.isEmpty());
        }

        public boolean hasNext() {

            return !queue_fin.isEmpty();

        }

        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException("All nodes have been visited!");
            }
            checkForComodification();
            try {
                top = queue_fin.poll();
                String next = top.element;

                return next;
            } catch (IndexOutOfBoundsException e) {
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            throw new UnsupportedOperationException("remove() is not supported.");
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }





    @Override
    public String get(int index)
    {
        throw new UnsupportedOperationException();
    }
    @Override
    public String set(int index, String element)
    {
        throw new UnsupportedOperationException();
    }
    @Override
    public String remove(int index)
    {
        throw new UnsupportedOperationException();
    }
    @Override
    public void add(int index, String element)
    {
        throw new UnsupportedOperationException();
    }
    @Override
    public List<String> subList(int fromIndex, int toIndex)
    {
        throw new UnsupportedOperationException();
    }
    @Override
    public int lastIndexOf(Object o)
    {
        throw new UnsupportedOperationException();
    }
    @Override
    public ListIterator<String> listIterator(int index)
    {
        throw new UnsupportedOperationException();
    }
    @Override
    protected void removeRange(int fromIndex, int toIndex)
    {
        throw new UnsupportedOperationException();
    }
    @Override
    public int indexOf(Object o) { throw new UnsupportedOperationException(); }
    @Override
    public boolean addAll(int index, Collection<? extends String> c) {

        throw new UnsupportedOperationException();
    }
}