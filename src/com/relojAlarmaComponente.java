/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class relojAlarmaComponente extends JLabel implements ActionListener {
    private String formatoHora = "HH:mm";
    private Timer timer;
    private Date horaAlarma;
    private ActionListener alarmaListener;
    private String horaAnterior;
    private Clip clip;
    
    public relojAlarmaComponente() {
        timer = new Timer(1000, this);
        timer.start();
        horaAnterior = "";
        cargarSonido();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String horaActual = actualizarHora();
        if(!horaActual.equals(horaAnterior)){
            horaAnterior = horaActual;
            verificarAlarma();
        }
    }
    
    private String actualizarHora() {
        SimpleDateFormat formato = new SimpleDateFormat(formatoHora);
        String horaActual = formato.format(new Date());
        setText(horaActual);
        return horaActual;
    }
    
    public void setFormatHora(String formatoHora) {
        this.formatoHora = formatoHora;
        actualizarHora();
    }
    
    public void setAlarma(Date horaAlarma) {
        this.horaAlarma = horaAlarma;
    }
    
    private void verificarAlarma() {
        if(horaAlarma != null) {
            SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
            String horaActual = formato.format(new Date());
            if (horaActual.equals(formato.format(horaAlarma))) {
                dispararAlarma();
            }
        }
    }
    
    private void dispararAlarma() {
        if (alarmaListener != null) {
            reproducirSonido();
            alarmaListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "alarmaDisparada"));
        }
    }
    
    private void cargarSonido() {
        try{
            String basePath = new File("").getAbsolutePath();
            String soundPath = basePath + "/src/source/biohazard-alarm-143105.wav";
            File audioFile = new File(soundPath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }catch(LineUnavailableException | UnsupportedAudioFileException | IOException e){
            e.printStackTrace();
        }
    }
    
    private void reproducirSonido() {
        if(clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }
    
    public void addActionListener(ActionListener listener) {
        this.alarmaListener = listener;
    }
}
