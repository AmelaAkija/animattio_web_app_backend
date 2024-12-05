package com.animattio.animattio_web_app_backend;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Primary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
public class AnimattioWebAppBackendApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(AnimattioWebAppBackendApplication.class, args);
	}

	@Primary
	@Autowired
	public void InitFirebase(@Value("${custom.firebase_keys}") String path) throws IOException {
		ClassLoader classLoader = AnimattioWebAppBackendApplication.class.getClassLoader();
		File file = new File(Objects.requireNonNull(classLoader.getResource(path)).getFile());
		FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();

		FirebaseApp.initializeApp(options);
	}
}
