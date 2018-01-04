import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.fazecast.jSerialComm.SerialPort;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame window = new JFrame();
		JSlider slider= new JSlider();
		slider.setMaximum(1023);
		window.add(slider);
		window.pack();
		window.setVisible(true);
		
		SerialPort ports[] = SerialPort.getCommPorts();
		System.out.println("Select a port");
		
		int i=0;
		for(SerialPort port:ports) {
			System.out.println(i+1 + "." + ports[i].getSystemPortName());
			i++;
		}
		Scanner s =  new Scanner(System.in);
		int chosenPort = s.nextInt();
		SerialPort port =ports[chosenPort - 1];
		//open port
		if(port.openPort()) {
			System.out.println("successfully");
		}else {
			System.out.println("fail");
			return;
		}
		port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
		
		Scanner data = new Scanner(port.getInputStream());
		while(data.hasNextLine()) {
			System.out.println(data.nextLine());
		}
		

	}

}
