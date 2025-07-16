package Lib;
import java.util.ArrayList;
public class ShoppingCartCalculator 
{
    /**
    * คำนวณราคาสินค้าและการใช้คูปองส่วนลด
    *คูปองส่วนลด 'BOGO' คือโปรโมชั่น 1 แถม 1
    *คูปองส่วนลด 'BULK' คือโปรโมชั่น ซื้อสินค้า 6 ชิ้นขึ้นไปลด 10% จากราคาสินค้าทั้งหมด
    *@param คำนวณราคาสินค้าจาก price*quantity
    *@return ราคาสินค้าที่ต้องจ่ายพร้อมส่วนลดในกรณีที่ใช้คูปอง
    *@exception ถ้ามีการกรอกคูปองส่วนลดผิดหรือไม่ถูกต้องจะมีการส่ง error
    */
    public static double calculateTotalPrice(ArrayList<CartItem> items) 
    {
        //ตะกร้าว่าง
        if(items == null || items.isEmpty())
        {
            return 0.0;
        }
        //มีสินค้า 1 ชิ้นขึ้นไป
        double total = 0.0;
        for(int i = 0;i<items.size();i++)
        {
            CartItem item = items.get(i);
            double itemTotal = 0.0;
            String sku = item.sku();
            if ((item.quantity() > 0) && (item.price() > 0))
            {
                switch (sku) 
                {
                    case "BOGO":
                    {
                        int p = (item.quantity() / 2) + (item.quantity() % 2);
                        itemTotal = p * item.price();
                        break;
                    }
                    case "BULK":
                    {
                        if(item.quantity() >= 6)
                        {
                            itemTotal = ((item.price() * item.quantity()) * 90) / 100;
                        }
                        else
                        {
                            itemTotal = item.price() * item.quantity();
                        }
                        break;
                    }
                    default :
                    {
                        itemTotal = item.price() * item.quantity();
                    }
                }
            }
            else
            {
                continue;
            }
            total += itemTotal;
        }
        
        return total;
    }
}
