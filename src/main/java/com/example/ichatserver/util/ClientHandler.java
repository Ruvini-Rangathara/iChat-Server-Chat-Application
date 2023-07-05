package com.example.ichatserver.util;

import javax.mail.MessagingException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable {
    public static final List<ClientHandler> clientList = new ArrayList<>();
    private final Socket socket;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;
    private final String clientName;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
        clientName = inputStream.readUTF();
        clientList.add(this);
        
    }


    public static void broadcast(String msg) throws IOException {
        for (ClientHandler handler : clientList) {
            handler.sendMessage("SERVER", msg);
        }
    }

    @Override
    public void run() {
        while (socket.isConnected()) {
            try {
                String utf = inputStream.readUTF();
                if (utf.equals("*image*")) {
                    receiveImage();
                } else {
                    for (ClientHandler handler : clientList) {
                        if (!handler.clientName.equals(clientName)) {
                            handler.sendMessage(clientName, utf);
                        }
                    }
                }
            } catch (IOException e) {
                clientList.remove(this);
                System.out.println(clientName+" removed");
//                System.out.println(clientName.size());
                break;
            }
        }
    }

    public void sendMessage(String sender, String msg) throws IOException {
        msg = sender + ": " + msg;
        outputStream.writeUTF(msg);
        outputStream.flush();
    }

    private void receiveImage() throws IOException {
        int size = inputStream.readInt();
        byte[] bytes = new byte[size];
        inputStream.readFully(bytes);
        for (ClientHandler handler : clientList) {
            if (!handler.clientName.equals(clientName)) {
                handler.sendImage(clientName, bytes);
                System.out.println(clientName+" - image sent ");

            }
        }
    }

    private void sendImage(String sender, byte[] bytes) throws IOException {
        outputStream.writeUTF("*image*");
        outputStream.writeUTF(sender);
        outputStream.writeInt(bytes.length);
        outputStream.write(bytes);
        outputStream.flush();
        System.out.println("Image Sent");
    }
}
