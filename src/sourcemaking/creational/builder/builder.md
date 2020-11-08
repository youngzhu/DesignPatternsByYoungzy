# 构造器模式
## 含义
- 将一个复杂对象的创建和它可能的表现形式分开，以便于同一个创建过程可以创建不同的表现形式
- 将一个复杂的表现形式解体（拆解的步骤形成接口），然后创建一系列具体的构造器

## 问题
一个应用需要创建一个有很多元素组成的复杂的集合体。该集合体的具体内容存储在辅助存储器内（如文件），但其中某一种展现形式需要在主存储器（内存）中构建。

## 讨论
将对数据——从文件或数据库等——的解析（读取和分析）与数据的展示分离开。焦点是创建一个复杂的集合体。

“主管”在解析底层数据时调用“构建者”。“构建者”每次被调用都构建这个复杂对象的某一部分，并且掌控所有中间状态。构建完成后，客户端可以从“构建者”处获取。

对创建过程有了更好的控制。不像之前的“抽象工厂模式”一步构建（`new`）对象，“构造器模式”是在“主管”的控制下一步步地完成构建。

## 结构
`Reader` 负责读取和解析数据。 `Converter` 的一组对象可以创建数据的不同展现形式。

![](https://sourcemaking.com/files/v2/content/patterns/Builder.png)

## 举例
这种模式被速食店用来制作儿童套餐。典型的儿童套餐包括一份主食、一份辅食、一杯饮料和一个玩具。例如：汉堡、薯条、可乐和玩具恐龙。套餐的内容可能不一样，但制作的流程是一样的，不管客户点的是火腿汉堡还是芝士汉堡亦或是鸡肉汉堡。店员负责将主食、辅食和玩具准备好并放入袋中。饮料被装在被子里放到另一个袋子里。所有速食店都是这么做的。

![](https://sourcemaking.com/files/v2/content/patterns/Builder_example1.png)

## 核查清单（也可以理解为应用步骤）
1. 确定是不是“一种输入，多种输出”的情况
2. 把对数据的读取和解析放到“读取器”中
3. 针对所有可能的输出形式定义一个标准的流程，形成一个构造器接口
4. 根据不同的输出形式定义不同的构造器
5. 客户端创建“读取器”和“构造器”，并将“构造器”注册到“读取器”中
6. 客户端通知“读取器”去构建
7. 客户端从“构造器”获取结果


原文：https://sourcemaking.com/design_patterns/builder