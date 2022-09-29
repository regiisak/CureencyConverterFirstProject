package Calculations;

import Currency.Coin;
import Currency.Coins;
import Currency.ILS;
import Currency.USD;

public class CoinFactory {

    public static Coin getCoinsInstances(Coins coin){
        try{
            if (coin==null)
                return null;
            switch(coin){
                case ILS:
                    return new ILS();
                case USD:
                    return new USD();}
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}



