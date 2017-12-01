package java24point;

import java.util.ArrayList;

public class JavaTest {
    /**
     * 使用4张牌计算出24点，只使用加减乘除四种运算符
     * @param cards : 输入的4张牌
     * @return 计算24点的算式
     */
    public static String res=null;

    public String make24Points(String cards) {
        if(cards==null||cards.contains("joker")||cards.contains("JOKER")){
            return "ERROR";
        }
        String[] c=cards.split(" ");
        if(c.length!=4){
            return "ERROR";
        }
        ArrayList<Double> d=new ArrayList<Double>();
        ArrayList<String> s=new ArrayList<String>();

        for(int i=0;i<4;i++){
            double td=toD(c[i]);
            if(td==-1){
                return "ERROR";
            }
            d.add(td);
            s.add(c[i]);
        }
        res=null;		//清空数据
        recursion(d,s);
        if(res==null){
            return "NONE";
        }
        return res;
    }

    //递归，数组中取出两个数计算后结果放回数组
    private void recursion(ArrayList<Double> d,ArrayList<String> s){
        int n=d.size();
        if(n==1){
            if(Math.abs(d.get(0)-24.0)<=0.000000001){	//防止有分数而产生误差
                res=s.get(0);		//若数组只有一个数，且为24，则将算式字符串赋值给类静态变量
//				System.out.println(res);
            }
            return;
        }

        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                ArrayList<Double> dd=new ArrayList<Double>();//用于递归的新数组
                ArrayList<String> ss=new ArrayList<String>();//用于存放算式的新数组
                //任意取出两个数的不同组合情况
                for(int r=0;r<n;r++){
                    if(r!=i&&r!=j){
                        dd.add(d.get(r));
                        ss.add(s.get(r));
                    }
                }
                dd.add(d.get(i)+d.get(j));//加入取出的两数计算结果 a+b
                ss.add("("+s.get(i)+"+"+s.get(j)+")");//记录两数计算的算式
                recursion(dd, ss);//递归
                if(res!=null){	//若静态变量已有值，则返回
                    return;
                }

                dd.remove(dd.size()-1);//移除上次添加的两数计算结果
                ss.remove(ss.size()-1);
                dd.add(d.get(i)-d.get(j));//a-b
                ss.add("("+s.get(i)+"-"+s.get(j)+")");
                recursion(dd, ss);
                if(res!=null){
                    return;
                }

                dd.remove(dd.size()-1);
                ss.remove(ss.size()-1);
                dd.add(d.get(i)*d.get(j));//a*b
                ss.add(s.get(i)+"*"+s.get(j));
                recursion(dd, ss);
                if(res!=null){
                    return;
                }

                dd.remove(dd.size()-1);
                ss.remove(ss.size()-1);
                dd.add(d.get(i)/d.get(j));//a/b
                ss.add(s.get(i)+"/"+s.get(j));
                recursion(dd, ss);
                if(res!=null){
                    return;
                }

                dd.remove(dd.size()-1);
                ss.remove(ss.size()-1);
                dd.add(d.get(j)-d.get(i));//b-a
                ss.add("("+s.get(j)+"-"+s.get(i)+")");
                recursion(dd, ss);
                if(res!=null){
                    return;
                }

                dd.remove(dd.size()-1);
                ss.remove(ss.size()-1);
                dd.add(d.get(j)/d.get(i));//b/a
                ss.add(s.get(j)+"/"+s.get(i));
                recursion(dd, ss);
                if(res!=null){
                    return;
                }
            }
        }


    }
    //转换牌为数字
    private double toD(String s){
        if(s.equals("A")){
            return 1;
        }
        if(s.equals("J")){
            return 11;
        }
        if(s.equals("Q")){
            return 12;
        }
        if(s.equals("K")){
            return 13;
        }
        int n=Integer.parseInt(s);
        if(n<2||n>10){
            return -1;
        }
        return n;
    }


    public static void main(String[] args) {
        JavaTest c = new JavaTest();
        String cards = "Q A 6 A";
//		String cards = "5 5 5 A";
        System.out.println("The method to make 24 points is: " + c.make24Points(cards));
    }
}