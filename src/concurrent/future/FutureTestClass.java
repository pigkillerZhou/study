package concurrent.future;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTestClass {

    static class BoilWater implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(10000);
            return System.currentTimeMillis()+"热水器：主人，水已经烧开";
        }
    }


    public static void main(String[] args) throws Exception{
        //创建一个烧水的线程
        FutureTask futureTask = new FutureTask(new BoilWater());
        //试用线程池的形式
        Executors.newCachedThreadPool().submit(futureTask);
        //new Thread(futureTask).start();采用thread的形式的开启futureTask
        System.out.println(System.currentTimeMillis()+"小热你可以开始烧水了么");
        //继续主线程
        System.out.println(System.currentTimeMillis()+"我继续打dota");
        Thread.sleep(3000);
        System.out.println(System.currentTimeMillis()+"哦，我挂掉了");

        System.out.println(System.currentTimeMillis()+"我看看水烧好了没？");
        while (!futureTask.isDone()){
            System.out.println(System.currentTimeMillis()+"热水器：我水还没烧开");
            Thread.sleep(1000);
        }

        System.out.println((String) futureTask.get());

    }
}
