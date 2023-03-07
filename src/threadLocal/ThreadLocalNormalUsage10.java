package threadLocal;

/**
 * 演示: 演示ThreadLocal 用法2: 避免传递参数的麻烦
 */
public class ThreadLocalNormalUsage10 {
    public static void main(String[] args) {
        new Service1().process("big bro");
        new Service1().process("big");
    }
}

/**
 * 生成User 对象
 */
class Service1{
    public void process(String name){
        User user =  new User(name);
        UserContentHolder.holder.set(user);
        // 执行Service 2
        new Service2().process();
    }
}
class Service2{
    public void process(){
        User user = UserContentHolder.holder.get();
        System.out.println("Service 2 : "+user.name);
        new Service3().process();
    }
}

class Service3{
    public void process(){
        User user = UserContentHolder.holder.get();
        System.out.println("Service 3 : "+user.name);
        UserContentHolder.holder.remove();
    }
}

class UserContentHolder{
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class User{
    String name;

    public User(String name) {
        this.name = name;
    }
}