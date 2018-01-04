import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.fazecast.jSerialComm.SerialPort;
public class SendToArduino {
	static SerialPort chosenPort;
	static int x=0;
	public static void main(String[] args) { 
		//create and configure window
		JFrame window =  new JFrame();
		window.setTitle("Arduino");
		window.setSize(400,125);
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//create a droop-down box and connect button, then
		//place them at the top of the window
		
		JComboBox<String> portlist = new JComboBox<String>();
		
		JButton connectButton = new JButton("Connect");
		JButton disconnectButton = new JButton("Disconnect");
		
		
		JPanel topPanel=new JPanel();
		topPanel.add(portlist);
		
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.add(connectButton,BorderLayout.WEST);
		bottomPanel.add(disconnectButton, BorderLayout.EAST);
		
		window.add(topPanel,BorderLayout.NORTH);
		window.add(bottomPanel,BorderLayout.SOUTH);
		
		//populate the drop-down box
		SerialPort[] portNames = SerialPort.getCommPorts();
		for(int i=0; i < portNames.length; i++) 
			portlist.addItem(portNames[i].getSystemPortName());
	
			//configure the connect button and use another thread to listen for data
			connectButton.addActionListener(new ActionListener() {
				@Override public void actionPerformed(ActionEvent argo) {
					if(connectButton.getText().equals("Connect")) {
						//attempt to connect button and use another thread to listen to data
						chosenPort = SerialPort.getCommPort(portlist.getSelectedItem().toString());
						chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER,0,0);
						boolean ifConnect=chosenPort.openPort();
						System.out.println(ifConnect);
						if(ifConnect) {
							
							portlist.setEnabled(false);
						
							//create a new thread for sending data to arduino
							Thread thread = new Thread() {
								@Override public void run() {
									//wait after connecting
									try {
										Thread.sleep(400);
									}catch(Exception e) {
										
									}
									PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
									
									while(true) {
									output.print("on");	
								    output.flush();
									try {
										Thread.sleep(4000);
									}catch(Exception e) {
										
									}
									}
									
								}
							};
							thread.start();
							
						}
						
						//chosenPort.closePort();	
								
					}
				}
			});
			
	
			
			//show the window
			window.setVisible(true);
		}
	
	
	}
	
	
	
	

	
	
	
	
	


