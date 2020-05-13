package com.java.study.javastudy.lambda;

/**
 * @Classname Template
 * @Description
 * @Date 2020/3/26 0:39
 * @Author HXL
 */
public class Template {




    abstract class OnlineBanking {
        public void processCustomer(int id){
            String c = String.valueOf(id);
            makeCustomerHappy(c);
        }
        abstract void makeCustomerHappy(String c);
    }



    class Transfer extends OnlineBanking{


        @Override
        public void processCustomer(int id) {
            super.processCustomer(id);
        }

        @Override
        void makeCustomerHappy(String c) {

        }
    }
}
