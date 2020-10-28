# 创建型设计模式（Creational Patterns）
创建型设计模式是用来处理对象创建机制的模式。在特定场景下用更合适的方式去创建对象。原始的对象创建方式（如Java的`new`）可能会导致设计问题或者增加设计的复杂度。创建型设计模式通过控制对象的创建在一定程度上解决了这个问题。

## 经验法则
1. 不同的创建型设计模式之间，有时候它们是竞争的：有些场景，[原型模式][url-prototype]和[抽象工厂模式][url-abstract-factory]都可以很好地解决；有时候它们又是互补的：[抽象工厂模式][url-abstract-factory]会用[原型模式][url-prototype]来存放将要返回的对象；[构建模式][url-builder]可以使用其他的模式去构建组件；[抽象工厂模式][url-abstract-factory]、[构建模式][url-builder]、[原型模式][url-prototype]在实现的过程中可以使用[单例模式][url-singleton]。
2. [抽象工厂模式][url-abstract-factory]、[构建模式][url-builder]、[原型模式][url-prototype]定义了一个工厂对象，用来创建对象实例，并且提供入参来实现不同的行为。[抽象工厂模式][url-abstract-factory]的工厂对象用来创建不同对象的实例。[构建模式][url-builder]的工厂对象根据相应的复杂的协议逐步地构建一个复杂的对象。[原型模式][url-prototype]的工厂对象通过复制原型对象的方式创建对象。
3. [抽象工厂模式][url-abstract-factory]通常使用[工厂方法模式][url-factory-method]实现，但也有用[原型模式][url-prototype]实现的。
4. [抽象工厂模式][url-abstract-factory]在隐藏平台特定的类方面可以作为[门面模式][url-facade]的替代品。
5. [构建模式][url-builder]专注于一步步地构建复杂的对象。[抽象工厂模式][url-abstract-factory]着重于一组相似的对象（简单或复杂的）。[构建模式][url-builder]在最后一步完成对象的组装并返回，而[抽象工厂模式][url-abstract-factory]会立即返回对象。
6. [构建模式][url-builder]是一种构建过程，[策略模式][url-strategy]是一种算法。
7. [构建模式][url-builder]通常构成了[组合模式][url-composite]。
8. [工厂方法模式][url-factory-method]通常在[模板方法模式][url-template-method]中被使用。
9. [工厂方法模式][url-factory-method]使用继承创建对象；[原型模式][url-prototype]通过委托创建对象。
10. 通常，设计是从[工厂方法模式][url-factory-method]开始，因为它简单、可定制化、子类可扩展。逐步地向[抽象工厂模式][url-abstract-factory]、[原型模式][url-prototype]和[构建模式][url-builder]转变和演化，因为他们更灵活，但也更复杂。
11. [原型模式][url-prototype]不需要子类，但一定要有初始化过程；[工厂方法模式][url-factory-method]需要子类，但不一定有初始化过程。
12. 有些过度使用了[组合模式][url-composite]和[装饰者模式][url-decorator]的设计也可以使用[原型模式][url-prototype]。

原文链接：https://sourcemaking.com/design_patterns/creational_patterns

[url-prototype]: #
[url-abstract-factory]: #
[url-builder]: #
[url-singleton]: #
[url-facade]: #
[url-strategy]: #
[url-composite]: #
[url-factory-method]: #
[url-template-method]: #
[url-decorator]: #
