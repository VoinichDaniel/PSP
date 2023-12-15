package lab4.part.first.servertcp;
import java.util.ArrayList;
import java.util.List;
import java.io.*;//импорт пакета, содержащего классы для
//ввода/вывода
import java.net.*;//импорт пакета, содержащего классы для работы
//сети Internet
public class server {
    public static String storedText = "Mancera - Red Tobacco. Jean Paul Gaultier - Scandal. Red.";



    public static void main(String[] arg) {//объявление объекта класса ServerSocket

        ServerSocket serverSocket = null;
    Socket clientAccepted     = null;//объявление объекта класса Socket
    ObjectInputStream  sois   = null;//объявление байтового потока ввода
    ObjectOutputStream soos   = null;//объявление байтового потока вывода
    try {
        System.out.println("server starting....");
        serverSocket = new ServerSocket(2525);//создание сокета сервера для //заданного порта
        clientAccepted = serverSocket.accept();//выполнение метода, который //обеспечивает реальное подключение сервера к клиенту
        System.out.println("connection established....");
//создание потока ввода soos = new
        sois = new ObjectInputStream(clientAccepted.getInputStream());

        soos = new ObjectOutputStream(clientAccepted.getOutputStream());//создание потока


//вывода
        String clientMessageRecieved = (String)sois.readObject();//объявление //строки и присваивание ей данных потока ввода, представленных
//в виде строки (передано клиентом)
        while(!clientMessageRecieved.equals("quite"))//выполнение цикла: пока
//строка не будет равна «quite»
        {
            System.out.println("word recieved: '"+clientMessageRecieved+"'");
            String[] sentences = storedText.split("[.!?]");

            List<String> sentencesWithWord = new ArrayList<>();

            // Поиск слова в каждом предложении
            for (String sentence : sentences) {
                if (sentence.toLowerCase().contains(clientMessageRecieved.toLowerCase())) {
                    sentencesWithWord.add(sentence.trim());
                }
            }
            String mas = "";
            int i = 0;
// создать массив в который я запихну все нужные предложения и передать потоку
            // Вывод предложений с найденным словом
            if (!sentencesWithWord.isEmpty()) {
                System.out.println("Предложения с словом '" + clientMessageRecieved + "':");
                for (String sentence : sentencesWithWord) {
                    mas += sentence + ". ";
                    System.out.printf(mas);
                }
            } else {
                mas = "Слово не найдено...";
            }
            System.out.printf(mas);

                soos.writeObject(mas);//потоку вывода
                //присваивается значение строковой переменной (передается клиенту)
                mas = (String)sois.readObject();//строке
                //присваиваются данные потока ввода, представленные в виде строки
                //(передано клиентом)


        }   }catch(Exception e)	{
    } finally {
        try {
            sois.close();//закрытие потока ввода
            soos.close();//закрытие потока вывода
            clientAccepted.close();//закрытие сокета, выделенного для клиента
            serverSocket.close();//закрытие сокета сервера
        } catch(Exception e) {
            e.printStackTrace();//вызывается метод исключения е
        }
    }
}
}

