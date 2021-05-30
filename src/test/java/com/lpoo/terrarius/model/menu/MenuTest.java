package com.lpoo.terrarius.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MenuTest {
    Menu menu;

    @BeforeEach
    public void setup() {
        menu = new Menu();
    }

    @Test
    public void previousOption() {
        Assertions.assertEquals(0, menu.getSelectedIndex());
        menu.previousOption();
        Assertions.assertEquals(menu.getNumOptions() - 1, menu.getSelectedIndex());
        menu.previousOption();
        Assertions.assertEquals(menu.getNumOptions() - 2, menu.getSelectedIndex());
    }

    @Test
    public void nextOption() {
        Assertions.assertEquals(0, menu.getSelectedIndex());
        menu.nextOption();
        Assertions.assertEquals(1, menu.getSelectedIndex());
        menu.nextOption();
        Assertions.assertEquals(0, menu.getSelectedIndex());
    }

    @Test
    public void buttonsSelected() {
        Assertions.assertTrue(menu.isPlaySelected());
        Assertions.assertFalse(menu.isQuitSelected());

        menu.nextOption();

        Assertions.assertTrue(menu.isQuitSelected());
        Assertions.assertFalse(menu.isPlaySelected());
    }
}
