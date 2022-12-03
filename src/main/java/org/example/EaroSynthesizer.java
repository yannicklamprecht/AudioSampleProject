package org.example;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class EaroSynthesizer {


    private static final int SAMPLE_RATE = 48000;
    private static final int FRAME_RATE = SAMPLE_RATE;
    private static final int CHANNELS = 2; // STEREO
    private static final int SAMPLE_SIZE_BITS = 16;
    private static final int BUFFER_SIZE = 1 << 12; // 4096

    public EaroSynthesizer() {
    }

    public static void main(String[] args) {
        EaroSynthesizer synthesizer = new EaroSynthesizer();
        synthesizer.playYoyoWav();

    }

    public void playYoyoWav() {
        // URL url = getClass().getResource("/BabyElephantWalk60.wav");
        URL url = getClass().getResource("/ImperialMarch60.wav");
        System.out.println("url = " + url);

        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url)) {

            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            try (var sourceDataLine = (SourceDataLine) AudioSystem.getLine(info)) {
                sourceDataLine.open();
                sourceDataLine.start();

                byte[] bufferBytes = new byte[BUFFER_SIZE];
                int readBytes;
                while ((readBytes = audioInputStream.read(bufferBytes)) != -1) {
                    sourceDataLine.write(bufferBytes, 0, readBytes);
                }

                byte frameSize = CHANNELS * SAMPLE_SIZE_BITS / 8;

            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    private byte[] squareWave(int amplitude, int phase, int frequency) {
        int seconds = 1;
        byte[] bytes = new byte[SAMPLE_RATE * seconds];
        for (int i = 0; i < bytes.length; i++) {

        }
        return bytes;
    }

}
