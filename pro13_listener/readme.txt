事务管理
1)及到的组件
  OpenSessionInViewFilterTransactionManager
  ThreadLocal
  ConnUtil
   BaseDAO
2) ThreadLocal
  get()，set(obj)：
  - ThreadLoca1称之为本地线程
  - 我们可以通过set方法在当前线程上存储数据、通过get方法在当前线程上获取数据