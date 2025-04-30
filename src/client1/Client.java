package client1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client
{
  private Socket socket;
  private BufferedReader input;
  private PrintWriter output;

  public Client(String serverAddress, int port) throws IOException
  {
    socket = new Socket(serverAddress, port);
    input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    output = new PrintWriter(socket.getOutputStream(), true);
  }

  public void sendRequest(String request)
  {
    output.println(request);
  }

  public String getResponse() throws IOException
  {
    return input.readLine();
  }

  public void close() throws IOException
  {
    socket.close();
  }
}
