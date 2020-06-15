package com.java.study.javastudy.base.enums;

/**
 * @Classname TestEnum
 * @Description 枚举跟方法结合写法
 * @Date 2020/6/1 15:18
 * @Author HXL
 */
public class TestEnum {


    private PizzaStatus status;


    public PizzaStatus getStatus() {
        return status;
    }

    public void setStatus(PizzaStatus status) {
        this.status = status;
    }

    public boolean isDeliverable() {
        return this.status.isReady();
    }

    public void printTimeToDeliver() {
        System.out.println("Time to delivery is " +
                this.getStatus().getTimeToDelivery());
    }


    /**
     *
     */
    public enum PizzaStatus {
        ORDERED(5) {
            @Override
            public boolean isOrdered() {
                return true;
            }
        },
        READY(2) {
            @Override
            public boolean isReady() {
                return true;
            }
        },
        DELIVERED(0) {
            @Override
            public boolean isDelivered() {
                return true;
            }
        };


        PizzaStatus(int timeToDelivery) {
            this.timeToDelivery = timeToDelivery;
        }

        private int timeToDelivery;



        public boolean isOrdered() {
            return false;
        }

        public boolean isReady() {
            return false;
        }

        public boolean isDelivered() {
            return false;
        }

        public int getTimeToDelivery() {
            return timeToDelivery;
        }


    }




    /**
     *测试
     * @param args
     * @date 2020/6/1 15:32
     * @author HXL
     * @exception
     * @return void
     */

    public static void main(String[] args) {
        TestEnum testEnum = new TestEnum();
        testEnum.setStatus(PizzaStatus.ORDERED);
        testEnum.printTimeToDeliver();
    }
}
