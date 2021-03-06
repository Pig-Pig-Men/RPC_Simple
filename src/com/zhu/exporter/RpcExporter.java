package com.zhu.exporter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/*
* RPC服务端服务发布者代码实现
* */
public class RpcExporter {
    static Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void exporter(String hostName,int port) throws Exception{
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(hostName,port));
        try {
            while (true){
                executor.execute(new ExproterTask(server.accept()));
            }
        }
        finally {
            server.close();
        }
    }

    private static class ExproterTask implements Runnable{
        Socket client = null;
        public ExproterTask(Socket client){
            this.client = client;
        }

        @Override
        public void run() {
            ObjectInputStream input = null;
            ObjectOutputStream output = null;
            try {
                input = new ObjectInputStream(client.getInputStream());
                String interfaceName = input.readUTF();
                Class<?> service = Class.forName(interfaceName);
                String methodName = input.readUTF();
                //通过input.readObject进行反序列化
                Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                Object[] arguments = (Object[]) input.readObject();
                Method method = service.getMethod(methodName,parameterTypes);
                //调用类中的方法
                Object result = method.invoke(service.newInstance(),arguments);
                output = new ObjectOutputStream(client.getOutputStream());
                output.writeObject(result);
            } catch (IOException | ClassNotFoundException | NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }finally {
                if (output != null){
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (input != null){
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (client != null){
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
