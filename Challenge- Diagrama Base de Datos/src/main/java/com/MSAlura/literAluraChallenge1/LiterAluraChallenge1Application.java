package com.MSAlura.literAluraChallenge1;

import com.MSAlura.literAluraChallenge1.Principal.Principal;
import com.MSAlura.literAluraChallenge1.Repository.IAutorRepository;
import com.MSAlura.literAluraChallenge1.Repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraChallenge1Application implements CommandLineRunner {
@Autowired
private ILibroRepository libroRepository;
	@Autowired
	private IAutorRepository autorRepository;
	public static void main(String[] args) {
		SpringApplication.run(LiterAluraChallenge1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepository,autorRepository);
		//Principal principal = new Principal();
		principal.VisualizarMenu();
	}
}
