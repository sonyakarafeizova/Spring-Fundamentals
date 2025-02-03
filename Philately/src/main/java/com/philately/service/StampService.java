package com.philately.service;

import com.philately.model.dto.StampCreateDTO;
import com.philately.model.dto.StampDTO;
import com.philately.model.entity.Paper;
import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;
import com.philately.repository.PaperRepository;
import com.philately.repository.StampRepository;
import com.philately.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StampService {

    private final StampRepository stampRepository;
    private final PaperRepository paperRepository;
    private final UserRepository userRepository;

    public StampService(StampRepository stampRepository, PaperRepository paperRepository, UserRepository userRepository) {
        this.stampRepository = stampRepository;
        this.paperRepository = paperRepository;
        this.userRepository = userRepository;
    }

    public StampDTO addStamp(StampCreateDTO stampDTO, Long ownerId) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));

        Paper paper = paperRepository.findById(stampDTO.getPaperId())
                .orElseThrow(() -> new IllegalArgumentException("Paper type not found"));

        Stamp stamp = new Stamp();
        stamp.setName(stampDTO.getName());
        stamp.setDescription(stampDTO.getDescription());
        stamp.setPaper(paper);
        stamp.setPrice(stampDTO.getPrice());
        stamp.setImageUrl(stampDTO.getImageUrl());
        stamp.setOwner(owner);

        stampRepository.save(stamp);

        return new StampDTO(stamp.getId(), stamp.getName(), stamp.getDescription(),
                stamp.getPaper().getPaperName().name(), stamp.getPrice(),
                stamp.getImageUrl(), owner.getUsername());
    }

    public List<StampDTO> getAllStamps() {
        return stampRepository.findAll().stream()
                .map(stamp -> new StampDTO(stamp.getId(), stamp.getName(), stamp.getDescription(),
                        stamp.getPaper().getPaperName().name(), stamp.getPrice(),
                        stamp.getImageUrl(), stamp.getOwner().getUsername()))
                .toList();
    }

    public List<Stamp> findAllAvailableStamps() {
        return stampRepository.findAllByOwnerIsNull();
    }
    public List<Stamp> findPurchasedStamps(Long userId) {

        return stampRepository.findByOwner_Id(userId);
    }

    public List<Stamp> getAllStampsByUser(Long id) {
        return stampRepository.findByAddedById(id);
    }

    public Set<Stamp> getWishedStampsByUser(Long id) {
        return userRepository.getWishedStamps(id);
    }

    public Set<Stamp> getBoughtStampsByUser(Long id) {
        return userRepository.getBoughtStamps(id);
    }

    public List<Stamp> getAllStamps(User userId) {
        return stampRepository.findStampsByAddedByNot(userId);
    }
}