package com.philately.init;

import com.philately.model.entity.Paper;
import com.philately.model.entity.PaperType;
import com.philately.repository.PaperRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PaperInitializer implements CommandLineRunner {

    private final PaperRepository paperRepository;

    public PaperInitializer(PaperRepository paperRepository) {
        this.paperRepository = paperRepository;
    }

    @Override
    public void run(String... args) {
        if (paperRepository.count() == 0) {
            List<Paper> papers = Arrays.stream(PaperType.values())
                    .map(type -> {
                        Paper paper = new Paper();
                        paper.setPaperName(type);
                        paper.setDescription(type.getDescription());
                        return paper;
                    })
                    .toList();
            paperRepository.saveAll(papers);
        }
    }
}
