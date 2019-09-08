import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class RS232Connector {

    private static SerialPort mSerialComm[];
    private static SerialPort mComPort;
    private static Scanner mDataReceiver;

    public static void main(String[] args) throws IOException {
        mSerialComm = SerialPort.getCommPorts();

        for(SerialPort port: mSerialComm)
            System.out.println(port.getDescriptivePortName());

        mComPort = mSerialComm[Integer.valueOf(new Scanner(System.in).next())];

        if(mComPort.openPort())
            System.out.println("Порт открыт");
        else
            System.out.println("Не удалось открыть порт");

        mComPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

        mDataReceiver = new Scanner(mComPort.getInputStream());

        while(mDataReceiver.hasNext()) {
            System.out.println(mDataReceiver.nextLine());
            mComPort.getOutputStream().write(new Random().nextInt(100));
        }

    }
}