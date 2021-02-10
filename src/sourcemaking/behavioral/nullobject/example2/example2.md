作为一个空对象模式的例子，来看一个非常简单且不可变的链表。

整个系统都是由这个 visitor 接口完成。

```java
interface ListVisitor {
    Object whenNonNullList(NonNullList host, Object param);
    Object whenNullList(NullList host, Object param);
}
```

链式表是一个有头有尾的列表或空（如，Null）。因此，在抽象的链表类 List 中定义 `getTail` 和 `accept` 两个方法是合理的。

````java
abstract class List {
    public abstract List getTail();
    public abstract Object accept(ListVisitor visitor, Object param);
}
````

接下来，我们定义一个非空的链表，NonNullList。它的实现是非常直接的。

```java
class NonNullList extends List {
    private Object head;
    private List tail;
    
    // 用头和尾创建一个链表。就像做连接
    public NonNullList(Object head, List tail) {
        this.head = head;
        this.tail = tail;
    }
    
    // 为了方便，我们可以新增一个只有 head 的构造方法
    // 创建一个只有一个元素的链表
    
    public Object getHead() {
        return head;
    }
    
    public List getTail() {
        return tail;
    }

    public Object accept(ListVisitor visitor, Object param) {
        return visitor.whenNonNullList(this. param);
    }
}
```

现在，让我们进入空对象模式的角色。我们创建一个 NullList 类来代表空链表，而不是用 Null。它清楚地知道该做什么。对链表头的访问会被故意地忽略，因为 NullList 无法实现这样的接口。

但是，空链表可以提供一个尾部（你可以有不同的看法，但我认为空不是没有），它可以接受一个访问者。因为所有空链表都是一样的，所以我们可以使用单例模式。

```java
class NullList extends List {
    private static final NullList instance = new NullList();
    
    private NullList() {}

    public static NullList singleton() {
        return instance;
    }
    
    public List getTail() {
        return this;
    }
    
    public Object accept(ListVisitor visitor, Object param) {
        return visitor.whenNullList(this, param);
    }
}
```

注意，只有在使用空对象模式时，使用访问者才是安全的。否则，在一个空链表上调用 accept 方法将会产生一个空指针异常。