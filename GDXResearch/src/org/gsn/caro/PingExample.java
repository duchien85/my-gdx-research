package org.gsn.caro;

import java.net.InetAddress;

public class PingExample 
{
    public static void main(String[] args)
    {
        try
        {
            InetAddress address = InetAddress.getByName("google.com.vn");

            // Try to reach the specified address within the timeout
            // periode. If during this periode the address cannot be
            // reach then the method returns false.
            long time = System.currentTimeMillis();
            boolean reachable = address.isReachable(100000);
            //if (reachable)
            	System.out.println("reached : " + (System.currentTimeMillis() - time));
            System.out.println("Is host reachable? " + reachable);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
