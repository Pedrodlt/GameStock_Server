/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamestock.servergamestockapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author pedro
 */
class MockSocketsServer {
    
    private int port;
    private PrintStream logFile;
    private ServerSocket serverSocket;
    private boolean running;

    public void initialize(int port, String logFile, boolean logTime) throws IOException {
        this.port = port;
        this.logFile = new PrintStream(logFile);
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            running = true;
            System.out.println("Server is running on port: " + port);

            while (running && !serverSocket.isClosed()) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("New client connected: " + clientSocket.getRemoteSocketAddress());
                    new Thread(() -> handleClient(clientSocket)).start();
                } catch (SocketException e) {
                    if (!running) break;
                    e.printStackTrace();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                try {
                    serverSocket.close();
                    System.out.println("Server stopped.");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void handleClient(Socket clientSocket) {
        try (clientSocket;
             PrintStream out = new PrintStream(clientSocket.getOutputStream());
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                log("Received: " + inputLine);
                out.println("Response from server: " + inputLine);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void log(String message) {
        if (logFile != null) {
            logFile.println(message);
        }
    }

    public void stop() {
        running = false;
        if (serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

}
