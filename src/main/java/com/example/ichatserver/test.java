package com.example.ichatserver;


import com.example.ichatserver.util.Mail;

import javax.mail.MessagingException;

public class test {
    public static void main(String[] args) {

        new Thread(()->{
            String subject="Welcome to iChat - Your Signup Was Successful!";
            String text="Dear Sir/ Madam,\n" +
                    "\n" +
                    "Congratulations and welcome to iChat! We're excited to have you as a new member of our chat community. Your signup process was successful, and we are thrilled to provide you with a platform to connect with people from around the world.\n" +
                    "\n" +
                    "As a member of iChat, you'll enjoy a range of features designed to enhance your chatting experience. Whether you want to connect with friends, meet new people, or engage in group discussions, our application offers a seamless and user-friendly interface for all your communication needs.\n" +
                    "\n" +
                    "We encourage you to take advantage of the following features:\n" +
                    "\n" +
                    "1. Group Chats: Engage in group discussions on various topics and find like-minded individuals.\n" +
                    "\n" +
                    "2. Emojis: Express yourself with a wide array of emojis to add fun to your conversations.\n" +
                    "\n" +
                    "5. Privacy and Security: Rest assured, your data and conversations are encrypted and protected.\n" +
                    "\n" +
                    "\n" +
                    "Once again, welcome to the iChat community! We hope you have a wonderful experience connecting with people, making new friends, and engaging in exciting conversations.\n" +
                    "\n" +
                    "Happy chatting!\n" +
                    "\n" +
                    "Best regards,\n" +
                    "iChat Team";

            String to = "subhasinghe.rr2000@gmail.com";
            try {
                Mail.sendMail(to, subject, text);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }
}
