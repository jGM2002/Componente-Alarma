/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        relojAlarmaComponente reloj = new relojAlarmaComponente();
        
        JFrame frame = new JFrame("Aplicación con Reloj y Alarma");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        frame.add(reloj, BorderLayout.CENTER);
        
        JButton btnAgregarAlarma = new JButton("Agregar Alarma");
        btnAgregarAlarma.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String horaAlarmaString = JOptionPane.showInputDialog(frame, "Ingresa la hora para la alarma (HH:mm):");
                
                if(horaAlarmaString != null && !horaAlarmaString.isEmpty()){
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    Date horaAlarma = null;
                    
                    try{
                        horaAlarma = sdf.parse(horaAlarmaString);
                    }catch(ParseException ex){
                        JOptionPane.showMessageDialog(frame, "Formato de hora incorrecto. Por favor la hora en formato HH:mm.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    reloj.setAlarma(horaAlarma);
                }
            }
        });
        
        frame.add(btnAgregarAlarma, BorderLayout.SOUTH);
        
        frame.setSize(300, 100);
        frame.setVisible(true);
        
        reloj.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "¡Alarma!", "Alarma", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
    
}
