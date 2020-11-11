> **2.（必做）**思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程？

## 方法1  future get方法

创建线程池submit任务，得到Future对象，然后通过future的get方法拿到返回值

```java
 public static void main(String[] args) throws Exception {
        
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(() -> sum());
        //future get 拿到返回值
        int result = future.get();
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);
         
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        
        // 然后退出main线程
    }
```

使用时间约300ms 左右。

## 方法2 使用CountDownLatch

使用CountDownLatch 等待所有的线程都执行完成

```java
    public static void main(String[] args) throws Exception {
        
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute( () -> {
           int result = sum();
            System.out.println("异步计算结果为："+result);
            countDownLatch.countDown();
        });
        executorService.shutdown();
        countDownLatch.await();
    
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }     
```

使用时间大约300ms左右

## 方法3 调用子线程的join方法

通过调用子线程的join方法等待到子线程执行完

```java
  public static void main(String[] args) throws Exception {     
      long start=System.currentTimeMillis();
       Thread t1 =  new Thread(() -> {
            int result = sum();
            System.out.println("异步计算结果为："+result);
        });
       t1.start();
       t1.join();   
       System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
  }   
```

耗时约300ms



## 方法4 CompletableFuture方法

通过CompletableFuture提交异步任务给ForkJoinPool去执行任务，通过join方法 拿到任务返回的结果

```java
public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        int result = CompletableFuture.supplyAsync(()-> sum()).join();

        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }
```

## 方法5  循环阻塞主线程，直到拿到返回结果

```java
    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();
     
        AtomicReference<Integer> result = new AtomicReference<>();
        new Thread(() -> result.set(sum())).start();
        // 没拿到结果之前，阻塞当前线程
        while (result.get() == null) {

        }
        System.out.println("异步计算结果为："+result.get());

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }
```

