package t1;

import java.util.ArrayList;
import java.util.List;

public class Test {

    List<Goods> goodsList = new ArrayList<>();
    List<Machine> machineList = new ArrayList<>();

    public Test(){

        goodsList.add(new Goods(1,25,9,4));
        goodsList.add(new Goods(2,25,6,5));
        goodsList.add(new Goods(3,18,5,8));
        goodsList.add(new Goods(4,19,6,6));
        goodsList.add(new Goods(5,31,8,7));
        goodsList.add(new Goods(6,34,10,3));
        goodsList.add(new Goods(7,25,7,9));
        goodsList.add(new Goods(8,13,2,3));
        goodsList.add(new Goods(9,19,9,7));
        goodsList.add(new Goods(10,21,11,7));
        goodsList.add(new Goods(11,16,5,10));
        goodsList.add(new Goods(12,19,7,5));
        goodsList.add(new Goods(13,15,4,2));
        goodsList.add(new Goods(14,23,8,8));
        goodsList.add(new Goods(15,21,6,3));
        goodsList.add(new Goods(16,18,9,5));
        goodsList.add(new Goods(17,12,3,3));
        goodsList.add(new Goods(18,16,7,7));

        machineList.add(new Machine(1,"机器1",0));
        machineList.add(new Machine(2,"机器2",0));
        machineList.add(new Machine(3,"机器3",0));
        machineList.add(new Machine(4,"机器4",0));

    }

    private void start() {

        //每1个零件 都有可能在 4台机子上制作
        for(Goods g : goodsList){
            for(Machine m :machineList){
                System.out.println("零件"+g.getId()+"在"+m.getName() +"上制作");
            }
        }

    }

    public static void main(String[] args) {
        new Test().start();
    }



}
