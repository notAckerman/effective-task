package org.example.effectivetask.service.priority;

import lombok.RequiredArgsConstructor;
import org.example.effectivetask.model.entity.Priority;
import org.example.effectivetask.model.enums.TaskPriority;
import org.example.effectivetask.repository.PriorityRepository;
import org.example.effectivetask.util.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultPriorityService implements PriorityService {

    private final PriorityRepository priorityRepository;

    @Override
    public Priority getPriority(Long id) {
        return priorityRepository.findById(id).orElseThrow(() -> new NotFoundException("Priority not found"));
    }
}
