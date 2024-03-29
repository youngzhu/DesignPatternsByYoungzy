# [译文]适配器模式

> 原文网址：[Adapter Design Pattern](https://sourcemaking.com/design_patterns/adapter)

## 意图
- 将一个类的接口（公共的对外的方法）转换成另一个客户想要的接口。适配器使得原本不兼容的类可以一起协作
- 给已存在的类封装一个新的接口
- 使旧组件能够适配新系统

## 问题
现有的组件包含了一些不得不用的功能，但它已不能适应当前系统的架构和设计理念。

## 讨论
重用一直是痛苦的和难以捉摸的。原因之一在于设计新东西时不得不使用旧的东西简直是个灾难。新和旧之间总会有一些不协调的地方：可能是客观上的不兼容，可能是时序或同步问题，可能是一些错误的假设或竟态标准。

就像要把三脚插头插入两孔插座一样，需要一个适配器或者转换器。

![](https://sourcemaking.com/files/v2/content/patterns/Adapter_realexample.png)

适配器就是提供一个中间的转换或者映射，使得老组件可以适用于新系统。客户端使用适配器，适配器通过一些转换再去调用老组件。它可以使用继承或者聚合来实现。

适配器是已经存在的类的一个封装或改进。它为该类提供了不同的或者转换过的视图。

## 结构
如下图所示，遗留的“长方形”组件的 `display()` 方法希望接受的参数是 `x, y, w, h`。但是客户端想传递左上角的坐标(x,y)和右下角的坐标(x,y)。这种不协调可以通过增加一个中间层来解决，例如适配器。

![](https://sourcemaking.com/files/v2/content/patterns/Adapter_1.png)

适配器也可以当做是一种包装。

![](https://sourcemaking.com/files/v2/content/patterns/Adapter.png)

## 举例
套筒扳手是个很好的例子。螺帽的大小应该跟扳手的大小一样。在美国，典型的扳手的大小是1/2英寸和1/4英寸。很显然，1/2英寸的螺钉不适合1/4英寸的螺帽，除非使用适配器。一个“大配小”的适配器，可以使1/2英寸的螺帽和1/2英寸的螺钉配对，也可以使1/4英寸的螺帽和1/4英寸的螺钉配对。

![](https://sourcemaking.com/files/v2/content/patterns/Adapter_example1.png)

## 核查清单（也可以理解为应用步骤）
1. 确定好角色：需要被满足的组件，即客户端；需要被适配的组件，即受适配者
2. 确定客户端想要的接口
3. 设计一个包装类使“旧的”可以匹配“新的”
4. 这个包装类/适配器/包含一个旧类的引用
5. 这个包装类/适配器映射了客户端和受适配者之间的接口
6. 客户端使用这个新的接口

## 经验法则
- 适配器模式是用来改变一个现有对象的接口。装饰者模式是在不改变接口的前提下增强这个类。所以，装饰者模式比适配器模式更透明。因此，装饰者模式支持递归组合，这在纯适配器模式中是不可能的。



- - -

Bolg: [www.youngzy.com | 设计模式 ｜ 适配器模式](http://www.youngzy.com/blog/2021/06/adapter/)
