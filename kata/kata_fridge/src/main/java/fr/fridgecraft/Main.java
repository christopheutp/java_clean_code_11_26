package fr.fridgecraft;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        SmartRefrigerator fridge = new SmartRefrigerator();
        LocalDateTime now = LocalDateTime.now();
        fridge.addItem("Lait", now.plusDays(3), PackagingState.SEALED);
        fridge.addItem("Yaourt", now.plusHours(2), PackagingState.OPENED);
        fridge.open();
        System.out.println(fridge.displayContents());
    }
}
