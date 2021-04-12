package com.devalphaing.VoiceCommand;

import java.io.IOException;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class App {
	public static void main(String[] args) {
		Configuration configuration = new Configuration();

		configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		configuration.setDictionaryPath("src\\main\\resources\\5057.dic");
		configuration.setLanguageModelPath("src\\main\\resources\\5057.lm");

		try {

			LiveSpeechRecognizer speech = new LiveSpeechRecognizer(configuration);
			speech.startRecognition(true);

			SpeechResult speechResult = null;
			
			while ((speechResult = speech.getResult()) != null) {
				String voiceCommand = speechResult.getHypothesis();
				System.out.println("Voice command is " +voiceCommand);
				
				if(voiceCommand.equalsIgnoreCase("Open Chrome")) {
					Runtime.getRuntime().exec("cmd.exe /c start chrome https://devalphaing.com/about-me/");
				} else if(voiceCommand.equalsIgnoreCase("Close Chrome")){
					Runtime.getRuntime().exec("cmd.exe /c TASKKILL /IM chrome.exe");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
