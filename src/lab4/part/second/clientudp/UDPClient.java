package lab4.part.second.clientudp;

import java.net.*;
import java.io.*;
public class UDPClient {//описание класса клиента
    public void runClient() throws IOException {//метод клиента runClient
        DatagramSocket s = null;//создание дейтаграммы
        try {
            byte[] buf = new byte[512]; //буфер для приема/передачи дейтаграммы
            s = new DatagramSocket();//привязка сокета к реальному объету
            System.out.println("UDPClient: Started");
            byte[] verCmd = { 'V', 'E', 'R', 'S' };
            DatagramPacket sendPacket = new DatagramPacket(verCmd, verCmd.length, InetAddress.getByName("127.0.0.1"), 8001);//создание
//дейтаграммы для отсылки данных
            s.send(sendPacket);//посылка дейтаграммы
            DatagramPacket recvPacket = new DatagramPacket(buf, buf.length);//создание дейтаграммы для получения данных
            s.receive(recvPacket);//получение дейтаграммы
            String version = new String(recvPacket.getData()).trim();//извлечение
//данных (версии сервера)
            System.out.println("UDPClient: Server Version: " + version);
            byte[] quitCmd = { 'Q', 'U', 'I', 'T' };
            sendPacket.setData(quitCmd);//установить массив посылаемых данных
            sendPacket.setLength(quitCmd.length);//установить длину посылаемых
// данных
            s.send(sendPacket); //послать данные серверу
            System.out.println("UDPClient: Ended");
        }
        finally {
            if (s != null) {
                s.close();//закрытие сокета клиента
            }  }  }
    public static void main(String[] args) {//метод main
        try {
            UDPClient client = new UDPClient();//создание объекта client
            client.runClient();//вызов метода объекта client
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}

