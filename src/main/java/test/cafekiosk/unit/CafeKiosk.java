package test.cafekiosk.unit;

import lombok.Getter;
import test.cafekiosk.unit.beverage.Beverage;
import test.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CafeKiosk {
    public static final LocalTime SHOP_OPEN_TIME = LocalTime.of(10,0);
    public static final LocalTime SHOP_CLOSE_TIME = LocalTime.of(22,0);
    private final List<Beverage> beverages = new ArrayList<>();

    public void add(Beverage beverage) {
        beverages.add(beverage);
    }

    public void remove(Beverage beverage) {
        beverages.remove(beverage);
    }

    public void clear() {
        beverages.clear();
    }

    public int calculateTotalPrice() {
        return beverages.stream().mapToInt(beverage -> beverage.getPrice()).sum();
    }

    public Order createOrder(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalTime currentTime = LocalTime.from(LocalDateTime.of(2023,1,17,15,0));
        if(currentTime.isBefore(SHOP_OPEN_TIME) || currentTime.isAfter(SHOP_CLOSE_TIME)){
            throw new IllegalArgumentException("주문 시간이 아닙니다. 관리자에게 문의하세요.");
        }
        return new Order(LocalDateTime.now(),beverages);
    }

    public Order createOrder(LocalDateTime currentDateTime){
        LocalTime currentTime = currentDateTime.toLocalTime();
        if(currentTime.isBefore(SHOP_OPEN_TIME) || currentTime.isAfter(SHOP_CLOSE_TIME)){
            throw new IllegalArgumentException("주문 시간이 아닙니다. 관리자에게 문의하세요.");
        }
        return new Order(LocalDateTime.now(),beverages);
    }
}
