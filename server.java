import java.net.*;
import java.io.*;
class server
{
    ServerSocket Server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;
    //constructor..
    public server()
    {
        try{
            Server=new ServerSocket(55555);
            System.out.println("Server is ready to accept connection");
            System.out.println("waiting.....");
            socket=Server.accept();
            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream());
            startReading();
            startWriting();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    public void startReading()
    {
        Runnable r1=()->{
            System.out.println("Reader started");
            try{

            
            while(true)
            {
               
                String msg=br.readLine();
                if(msg.equals("exit"))
                {
       
                    System.out.println("client terminated the chat");
                    socket.close();
                    break;
                }
                System.out.println("client "+ msg);
            
          
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        };
        new Thread(r1).start();
    }
    public void startWriting()
    {
        Runnable r2=()->{
            System.out.println("Writer started");
            try{

            
            while(!socket.isClosed())
            {
               
                    BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
                    String content =br1.readLine();
                  
                    out.println(content);
                    out.flush();
                    if(content.equals("exit"))
                    {
                        socket.close();
                        break;
                    }

               
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        };
        new Thread(r2).start();
    }
    public static void main(String[] args) {
        System.out.println("This is server going to start server");
        new server();
    }
}