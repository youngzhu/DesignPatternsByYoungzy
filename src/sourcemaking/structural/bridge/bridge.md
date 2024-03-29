# [译文]桥接模式

> 原文网址：[Bridge Design Pattern](https://sourcemaking.com/design_patterns/bridge)

## 意图
- 将抽象与实现分离，以便两者可以独立地改变
- 在一个继承结构上发布接口，并将实现隐藏在自己的继承机构中
- 不仅有封装性，还独立

## 问题
通过继承抽象基类来完成不同实现的方式已经使软件变得“僵化”。它会在编译期间使接口和实现绑定，接口和实现不能独立的扩展或者组合。

## 动机
看看下面的类图。

![](https://sourcemaking.com/files/v2/content/patterns/Bridge.png)

有两种线程调度程序，两个操作系统（平台）。按照这种实现方式，我们必须从这两个维度去定义类。如果我们要新增一个平台（就叫JVM），结构图是怎样的？

![](https://sourcemaking.com/files/v2/content/patterns/Bridge_.png)

如果有3类线程调度，4种平台呢？5类线程调度，10个平台呢？我们要定义的类的数量是线程调度数量和平台数量的乘积。

桥接模式将这种指数增长的继承机构重构为两个正交的结构：一个是与平台无关的抽象，另一个是与平台相关的实现。

![](https://sourcemaking.com/files/v2/content/patterns/Bridge__.png)

## 讨论
将接口和实现拆解为正交结构。接口类包含一个实现类的引用。这个引用是一个具体的实现类的实例，后续的接口类和实现类之间的交互都限定在基础的实现类里。客户端调用接口类，接口类把所有的请求委托给实现类。

接口对象是被客户端熟知并使用的，而实现对象是被很好地封装着以便可以扩展或者在运行时被替换或共享。

在以下场景中可以使用桥接模式：
- 想要在运行时决定具体的实现类
- 由于耦合的接口和众多的实现类导致类过多
- 需要在多个对象之间共享一个实现
- 需要将类结构转变为正交结构

产生的影响包括：
- 使接口间解耦
- 提高了扩展性（可以独立地继承抽象类和实现类）
- 对客户端隐藏细节

桥接模式是“handle/body”（实在不知道咋翻译）的同义词。这是一种把实现类封装在接口中的设计原理。前者（实现类）是主体（body），后者（接口类）是操纵杆（handle）。操纵杆是客户端可见的，而实际的工作是在主体中完成的。“这种模式可以用来将复杂的接口分解为更小的更容易管理的类。它可以用来控制一个资源被多个类共享时的访问机制，如引用计数。”

## 结构
客户端不会想要处理平台相关的细节。桥接模式将这些复杂的细节隐藏起来。

桥接模式的主要作用就是识别出接口类和实现类，并将他们解耦。

![](https://sourcemaking.com/files/v2/content/patterns/Bridge___.png)

## 举例
家用的电灯、吊扇等开关就是桥接模式的一个例子。开关的目的是打开或者关闭设备。实际的开关可能是推拉式的，可能是简单的两位开关，也可能是多种调光器开关。

![](https://sourcemaking.com/files/v2/content/patterns/Bridge_example.png)

## 核查清单（也可以理解为应用步骤）
1. 确定当前是否存在两个正交的维度。这些不相关的概念包括：抽象和平台，领域和基础设施，前端和后端，接口和实现。
2. 设计好隔离点：客户端需要什么，平台可以提供什么。
3. 将平台相关的接口设计成小巧的，充分的，且是必要的。它的目标是将抽象和实现之间解耦
4. 为每一个平台定义一个接口衍生类
5. 创建一个抽象的基类，包含一个平台对象实例，并把具体工作委托给平台相关的类去完成。
6. 如果有必要，定义一些专门的抽象类。

## 经验法则
- 状态模式、策略模式和桥接模式（一定程度上也包括适配器模式）有着类似的结构。他们都是用“handle/body”的方式，只是他们的目的不同，为了解决不同的问题。
- 状态模式和桥接模式的结构几乎是一模一样的，除了桥接模式允许封装的继承结构，而状态模式只有一个（一种模式一个类）。这两种模式使用相同的结构解决不同的问题：状态模式允许对象的行为随着它的状态而改变；而桥接模式的目的是使接口和实现之间解耦以便他们能独立的改变。
- 如果接口类委托了实现类的创建，通常使用抽象工厂模式来创建实现类，而不是直接地耦合地创建他们。



- - -

Bolg: [www.youngzy.com | 设计模式 ｜ 桥接模式](http://www.youngzy.com/blog/2021/08/bridge/)
