# [译文]抽象工厂模式

> 原文网址：[Abstract Factory Design Pattern](https://sourcemaking.com/design_patterns/abstract_factory)

## 意图
- 提供一个创建一组相关或相似对象的接口，而不用关心具体的类
- 一种继承关系，包括了各种可能的"平台"和一组对象的创建
- `new` 操作是有害的（言下之意就是少用）

## 问题
如果一个应用是便捷式的，那么它就要封装对平台的依赖。这里说的“平台”包括视窗系统、操作系统、数据库等等。通常这些封装不会提前设计，所以当要增加新的平台时 `if` 的条件语句就会像兔子一样在代码里到处乱串。

## 讨论
采用一种迂回的方式将一组类似或相关的对象的创建与具体的对象类隔离开来。工厂对象负责提供所有这一类对象的创建服务。客户端不用直接创建平台对象，让“工厂”去创建。

这样的机制使得不同“产品”之间的切换变得简单，因为具体的类只在工厂对象类里出现一次——实例化对象的地方。通过在抽象工厂里使用不同的具体实现类的方式，应用程序可以大规模地替换整个产品簇。

由于抽象工厂的方式使用得很普遍，它惯常用单例模式实现。

## 结构
抽象工厂为每一类产品定义一个工厂方法。每一个工厂方法封装了一个具体的产品类的 `new` 操作。通过工厂派生出的类，每一个"平台"都被模块化了。

![图](https://sourcemaking.com/files/v2/content/patterns/Abstract_Factory.png)

## 举例
这种模式在日本的汽车制造行业钣金冲压设备的生产过程中可以发现。冲压设备是一个用来制造汽车零部件的“抽象工厂”。同样的设备为不同的汽车模型制造右侧门、左侧门、右侧前挡泥板、左侧前挡泥板、引擎盖等等。通过辊轴改变冲压模具，设备可以在3分钟内切换生产不同的零部件。

![图片](https://sourcemaking.com/files/v2/content/patterns/Abstract_Factory_example1.png)

## 核查清单（也可以理解为应用步骤）
1. 确定“平台相关”的对象的创建是当前的痛点
2. 画一个“平台-产品”的矩阵图
3. 定义一个抽象工厂，该接口包含每一类产品的工厂方法
4. 定义各个平台的工厂类，其封装了对每一个平台相关产品的创建操作
5. 在客户端的调用中去掉所有的 `new` 操作，而改用工厂方法

----
_以下非译文_

将上述译文中的“举例”转为了代码。

一开始是按产品来划分的，如 `PlatformDoor` 包含了不同型号的门： `SedanDoor` `SportsDoor` `SUVDoor`

后来觉得按平台来划分对以后的扩展更友好。 `PlatformSedan` 里面包含了 `SedanDoor` `SedanHood` `SedanWheel` ，即包含了该型号车的所有零部件。

如果新增了一个xx型号，直接复制 `PlatformSedan` 改改即可，不像之前的结构比较分散。

Blog: [www.youngzy.com | 设计模式 ｜ 抽象工厂模式](http://www.youngzy.com/blog/2020/11/abstract-factory/)
