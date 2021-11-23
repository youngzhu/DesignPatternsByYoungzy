# [译文]装饰者模式

> 原文网址：[Decorator Design Pattern](https://sourcemaking.com/design_patterns/decorator)

## 意图
- 动态地给对象添加新的责任。装饰者模式提供了一种灵活方式去创建子类以便扩展功能
- 由客户端决定对核心类一层层地包装
- 包装一个礼物，把它放在盒子里，再去包装这个盒子

## 问题
你想要在运行时给对象增加行为或状态。继承不够灵活，因为它是静态的而且应用到整个类上。

## 讨论
假设你在开发一个视窗类的工具，你想要给窗口加上边框和滚动条。你可能会这样定义继承关系：

![](https://sourcemaking.com/files/v2/content/patterns/Decorator.png)

但是装饰者模式建议给用户自由组合任何他想要的特性。

```
Widget widget = new BorderDecorator(
                    new HorizontalScrollBarDecorator(
                        new VerticalScrollBarDecorator(
                            new Window(80, 60))));
widget.draw();
```

其灵活性可以由下面的设计完成。

![](https://sourcemaking.com/files/v2/content/patterns/Decorator_.png)

另一个将多种特性串联（或者说是链接）在一起生成一个客户对象的例子可能是这样的：
````
Stream stream = new CompressingStream(
                    new ASCII7Stream(
                        new FileStream("fileName.dat")));
stream.putString("Hello World");
````

解决的办法就是将原始对象封装在一个抽象的转换接口里。装饰类和核心类都实现了这个接口。这个接口使用递归的组合使得装饰者类可以无限次地添加到核心类里。

注意，这个模式允许增加新的职责，而不是给对象接口增加新的方法。接口对客户端来说必须是保持不变的，因为连续的层级是固定的。

还需要注意的是，核心类被隐藏在装饰类里。试图直接访问核心类是不对的。

## 结构
调用方感兴趣的是 `CoreFunctionality.doThis() `。调用方可能对 `OptionalOne.doThis() `和 `OptionalTwo.doThis() ` 感兴趣，也可能不关心。可选类都将它的责任委托给装饰者类，装饰者类将责任委托给其包含的封装对象。

![](https://sourcemaking.com/files/v2/content/patterns/Decorator__1.png)

## 举例
被挂在松树或杉树上的装饰品就是装饰者模式的例子。灯、花环、糖果、玻璃饰品等等都可以挂在树上，使它看起来充满了节日的气氛。饰品并没有改变树本身，它依然是棵圣诞树，不管它有没有特殊的饰品。作为附加功能的一个例子，挂上灯可以点亮圣诞树。

另一个例子：冲锋枪本身就是武器了。但你可以加一些“装饰”使它更精确，更静默，更具破坏性。

![](https://sourcemaking.com/files/v2/content/patterns/Decorator_example.png)

## 核查清单（也可以理解为应用步骤）
1. 确保如下的场景：一个核心（或没有多余操作的）组件，多个可选的装饰或封装，还有一个对他们都通用的接口
2. 创建一个最低通用性（LCD）的接口使得所有类可以互相替换
3. 创建一个二级基类（装饰类）来支持可选的封装类
4. 核心类和封装类都实现了LCD接口
5. 封装类里包含一个LCD接口的引用，这个成员变量在构造函数中初始化
6. 封装类委托给LCD对象
7. 为每一个可选"装饰"创建一个装饰类的衍生类
8. 衍生类实现他们自己的可选功能，然后委托给装饰基类
9. 客户端决定核心类和装饰类的类型和顺序
