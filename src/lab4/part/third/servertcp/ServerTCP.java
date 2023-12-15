package lab4.part.third.servertcp;
import java.net.*;
import java.io.*;
class ServerTCP {
    static int countclients = 0;
    public static void main(String args[]) throws IOException {
        ServerSocket sock = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            sock = new ServerSocket(1024);
            while (true) {
                Socket client = sock.accept();
                countclients++;
                System.out.println("=======================================");
                System.out.println("Client " + countclients + " connected");
                is = client.getInputStream();
                os = client.getOutputStream();
                boolean flag = true;
                while (flag) {
                    byte[] bytes = new byte[1024];
                    is.read(bytes);
                    String str = new String(bytes, "UTF-8");
                    String[] words = str.split(" ");
                    String m = "";
                    bytes = new byte[1024];
                        System.out.println("клиент прислал слово " + words[0]);
                    System.out.println("клиент прислал слово " + words[1]);
                    if (words[0].equals(words[1])) {
                            m = "True";
                        }
                    else {
                        m = "False";
                    }
                    System.out.println(m);
                    bytes = m.getBytes();
                    os.write(bytes);
                    flag = false;
                }
            }
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
        } finally {
            is.close();
            os.close();
            sock.close();
            System.out.println("Client " + countclients + " disconnected");
        }
    }}
