package org.gsn.engine;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hungnm_c04211
 */
public class MClient {

    static SocketChannel client;
    static Selector selector;

    public static void main(String[] avg) {

        try {
            String ip = "10.198.36.71";//avg[0];
            int port = 9999;//Integer.parseInt(avg[1]);
            String cmd = "date";
//            for (int i = 2; i < avg.length; i++) {
//                cmd += avg[i] + " ";
//            }
//
//
//
           System.out.println("full cmd: " + ip + " " + port + " " + cmd);
            client = SocketChannel.open();
            client.configureBlocking(false);
            client.connect(new InetSocketAddress(ip, port));
           // client.connect(new InetSocketAddress("10.198.36.214", 9999));
            //String cmd = "date";
            client.configureBlocking(false);
            selector = Selector.open();
            client.register(selector, SelectionKey.OP_CONNECT|SelectionKey.OP_READ);
            while(true){
                selector.select();
                Set readyKeys = selector.selectedKeys();
                Iterator it = readyKeys.iterator();
                while(it.hasNext()){
                    SelectionKey key = (SelectionKey)it.next();
                    it.remove();
                    SocketChannel channel = (SocketChannel)key.channel();
                    if(key.isConnectable()){

                        if(channel.isConnectionPending()){
                            channel.finishConnect();
                        }
                        ByteBuffer buff = ByteBuffer.wrap(cmd.getBytes());
                        // ByteBuffer buff = ByteBuffer.wrap("cmd /c ipconfig".getBytes());
                         client.write(buff);
                         System.out.println("Cmd: " + cmd);
                    }else if(key.isReadable()){
                        String result ="";
                        int byteRead;
                        ByteBuffer buffRead = ByteBuffer.allocate(1024);
                        do{
                            byteRead = channel.read(buffRead);
                            if(byteRead ==-1){
                                channel.close();
                            }                           
                            result += new String(buffRead.array(),0,byteRead);
                             buffRead.flip();
                        }while(byteRead!=0);
                        System.out.println("Result  : "+ result );
                        System.exit(1);
                    }else{
                        System.out.println("Ooops connection false");
                    }
                }

            }
            
            //System.out.println("Cmd: date");
            
        } catch (IOException ex) {
            Logger.getLogger(MClient.class.getName()).log(Level.SEVERE, null, ex);
        }




//             test
//            int numRead;
//            String data = "date +%Y-%m-%d -s 2008-12-28";
//            Process p = Runtime.getRuntime().exec(data);
//            String result = "";
//            byte[] buff = new byte[1024];
//            InputStream is = p.getInputStream();
//            while ((numRead = is.read(buff)) != -1) {
//                result += new String(buff, 0, numRead);
//            }
//            msg.channel.close();
//            System.out.println("Cmd: " + data);
//            System.out.println("Result " + result);


    }
}
