package com.example.football.service.Impl;

import com.example.football.dto.Request.RefereeRequest;
import com.example.football.dto.Response.PlayerResponse;
import com.example.football.dto.Response.RefereeResponse;
import com.example.football.entity.Referees;
import com.example.football.repo.RefereeRepository;
import com.example.football.service.RefereeService;
import com.example.football.untils.MapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RefereeServiceImpl implements RefereeService {
    private final RefereeRepository refereeRepository;
    @Override
    public RefereeResponse addReferee(RefereeRequest refereeRequest) {
        Referees referees = new Referees(null,refereeRequest.getNameReferee(),refereeRequest.getPhone(),refereeRequest.getDOB(),refereeRequest.getAddress(),refereeRequest.getCCCD(),"on");
        refereeRepository.save(referees);
        return MapperUtils.toDTO(referees,RefereeResponse.class);
    }

    @Override
    public List<RefereeResponse> getAllRefereesON() {
        List<Referees> refereesList = refereeRepository.getAll();
        return MapperUtils.toDTOs(refereesList,RefereeResponse.class);
    }

    @Override
    public List<RefereeResponse> getAllReferees() {
        List<Referees> refereesList = refereeRepository.findAll();
        return MapperUtils.toDTOs(refereesList,RefereeResponse.class);
    }


}
