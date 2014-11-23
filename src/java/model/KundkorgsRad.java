/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 *
 * @author Jeff
 */
public class KundkorgsRad {
    public static final String ATTRIBUTE_NAME = "musicsite_kundkorg";
    private int amount = 1;
    private Album album = null;
    
    public KundkorgsRad(Album album) {
        this.album = album;
    }
    
    public KundkorgsRad(Album album, int amount) {
        this.album = album;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
    
    public void increaseAmount() {
        amount++;
    }
    
    public void reduceAmount() {
        amount--;
    }
    
    public static int getCartTotalItems(ArrayList<KundkorgsRad> cart) {
        int tot = 0;
        
        
        for (KundkorgsRad row : cart) {
            tot += row.getAmount();
        }
        
        return tot;
    }
    
    public static String getCartTotalPrice(ArrayList<KundkorgsRad> cart) {
        double tot = 0.0;
        
        
        for (KundkorgsRad row : cart) {
            tot += row.getAmount() * row.getAlbum().getPrice();
        }
        
        return NumberFormat.getCurrencyInstance().format(tot);
    }
}
