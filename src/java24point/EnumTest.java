package java24point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnumTest {

    private static final String ACTIVITY_NAME = "drawLots";



    public static void main(String[] args){
       System.out.println(LotsEnum.DJDL.ordinal());
    }
}



enum LotsEnum{
    DJDL("大吉大利签", "9.9", 50),//50%的概率
    CYHT("财运亨通签", "19.9",30),//30%概率
    QCSJ("前程似锦签", "29.9",20);//20%概率

    private String name;
    private String price;
    private int rate;

    private LotsEnum(String name, String price, int rate) {
        this.name = name;
        this.price = price;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * 按照概率抽签
     * @return 枚举类型的签
     */
    public static LotsEnum randomInt () {
        int randomNum = new Random().nextInt(100) + 1 ;
        int num ;//校验基础
        if(randomNum >= 0 && randomNum <= LotsEnum.DJDL.getRate()) {
            return LotsEnum.DJDL;
        }else {
            num = LotsEnum.DJDL.getRate() + LotsEnum.CYHT.getRate();
            if(randomNum <= num){
                return LotsEnum.CYHT;
            }else {
                return LotsEnum.QCSJ;
            }
        }
    }

}
