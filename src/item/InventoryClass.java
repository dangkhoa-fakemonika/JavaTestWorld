package item;

import item.subitem.ITM_Pickaxe;
import main.GamePanel;

import java.security.KeyPair;

public class InventoryClass {
    GamePanel gp;
    public Item[] items;
    public int[] itemCount;

    public Item primaryEquip;
    public Item bootsEquip;
    public Item bodyEquip;

    public InventoryClass(GamePanel gp){
        this.gp = gp;
        items = new Item[10];
        itemCount = new int[10];
        for (int i = 0; i < 10; i++)
            itemCount[i] = 0;
        primaryEquip = null;
        bootsEquip = null;
        bodyEquip = null;

        setCustomInventory();
    }

    void setCustomInventory(){
        items[0] = new ITM_Pickaxe(gp);
        itemCount[0]++;
    }

    public void addItem(){

    }

    public void removeItem(){

    }
}
