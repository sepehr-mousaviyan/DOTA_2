package sbu.cs.mahkats.Client.Connection;

import sbu.cs.mahkats.Configuration.Config;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Connection {
    Config config = Config.getInstance();
    private Socket socket;
    private static String Host = "host";
    private static String PORT = "port";
    private static String USERNAME_LENGTH = "input.limit.userName";
    private static String PASSWORD_LENGTH = "input.limit.passWord";
    private static String EMAIL_LENGTH = "input.limit.email";
    char[] charArray = {'%','&','*','(',')','/',':',';','!','?','$'};
    boolean enter1 = true,flag = true;
    boolean enter2 = true;
    boolean enter3 = true;


    public Connection() throws IOException {
        this.socket = new Socket(config.getStringValue(Host
        ),config.getIntValue(PORT));
    }

    public void start(){
        Scanner scanner = new  Scanner(System.in);
        System.out.println("Already have an account?(1-Sign up/2-Log in)");
        String answer = scanner.next();

        if (answer.equals("2")){

            System.out.println("Please enter your userName");
            String userName= scanner.next();
            while (enter1) {
                for (int i = 0; i < charArray.length; i++) {
                    if (userName.indexOf(charArray[i]) > -1){
                        System.out.println("invalid username! Do not use *,&,%,/,:,;,!,?,$,(,) characters");
                        userName= scanner.next();
                        i = 0;
                    }
                    else
                        enter1 = false;
                }
            }
            while (userName.length()>config.getIntValue(USERNAME_LENGTH) ){
                System.out.println("Your userName's length is invalid. please enter your userName again!");
                userName=scanner.next();
            }
            System.out.println("Please enter your passWord");
            String passWord = scanner.next();
            while (enter2) {
                for (int i = 0; i < charArray.length; i++) {
                    if (passWord.indexOf(charArray[i]) > -1){
                        System.out.println("invalid passWord! Do not use *,&,%,/,:,;,!,?,$,(,) characters");
                        passWord= scanner.next();
                    }
                    else
                        enter2 = false;
                }
            }

            while (passWord.length()>config.getIntValue(PASSWORD_LENGTH)){
                System.out.println("Your userName is invalid. please enter your userName again!");
                passWord=scanner.next();
            }
            if(!checkUserlogIn(userName,passWord))
                System.out.println("Sorry! Your passWord or your userName isn't right.");

            else
                System.out.println("Login Successful :D");

        }

        if (answer.equals("1")){
            while(flag) {
                enter1 = true;
                enter2 = true;
                System.out.println("Please enter your userName");
                String userName = scanner.next();
                while (enter1) {
                    for (int i = 0; i < charArray.length; i++) {
                        if (userName.indexOf(charArray[i]) > -1) {
                            System.out.println("invalid username! Do not use *,&,%,/,:,;,!,?,$,(,) characters");
                            userName = scanner.next();
                            i = 0;
                        } else
                            enter1 = false;
                    }
                }
                while (userName.length() > config.getIntValue(USERNAME_LENGTH)) {
                    System.out.println("Your userName's length is invalid. please enter your userName again!");
                    userName = scanner.next();
                }
                System.out.println("Please enter your passWord");
                String passWord = scanner.next();
                while (enter2) {
                    for (int i = 0; i < charArray.length; i++) {
                        if (passWord.indexOf(charArray[i]) > -1) {
                            System.out.println("invalid passWord! Do not use *,&,%,/,:,;,!,?,$,(,) characters");
                            passWord = scanner.next();
                            i = 0;
                        } else
                            enter2 = false;
                    }
                }

                while (passWord.length() > config.getIntValue(PASSWORD_LENGTH)) {
                    System.out.println("Your userName is invalid. please enter your userName again!");
                    passWord = scanner.next();
                }
                System.out.println("Please enter your EmailAddress ");
                String email = scanner.next();
                while (enter3) {
                    for (int i = 0; i < charArray.length; i++) {
                        if (email.indexOf(charArray[i]) > -1) {
                            System.out.println("invalid email! Do not use *,&,%,/,:,;,!,?,$,(,) characters");
                            email = scanner.next();
                            i = 0;
                        } else
                            enter3 = false;
                    }
                }
                while (email.length() > config.getIntValue(EMAIL_LENGTH)) {
                    System.out.println("Your userName is invalid. please enter your userName again!");
                    email = scanner.next();
                }
                if(!checkUserSignUp(userName,passWord,email))
                    flag = false;
            }

        }
    }

    public boolean checkUserlogIn(String userName, String passWord){
        boolean resault = false;



        return resault;
    }
    public boolean checkUserSignUp(String username, String passWord, String email){
        boolean resault = false;

        return resault;
    }

}
