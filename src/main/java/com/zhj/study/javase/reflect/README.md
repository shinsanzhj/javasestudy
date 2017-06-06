# Java反射相关知识
## obj.getClass().getComponentType().getName()返回原生数组中的对象的类型名称，前面不带[L前缀
## Array.newInstance(Button.class, 3)：返回的是一个原生数组Button[]，如果要转成ArrayList，可以进一步加工：List list = Array.asList(...);
