package com.testbook.base.interf;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.testbook.base.entity.BaseEntity;
import com.testbook.base.entity.BaseRequest;
import com.testbook.base.entity.BaseResponse;
import com.testbook.base.repo.BaseRepository;

public abstract class AbstractCrudService<REQ extends BaseEntity<?>, ID> 
    implements BaseCrudService<ID, REQ> {

    protected final BaseRepository<REQ, ID> repository;

    protected AbstractCrudService(BaseRepository<REQ, ID> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public BaseResponse<REQ> create(BaseRequest<REQ> request) {
    	REQ entity = convertToEntity(request.getRequestBody());
    	REQ saved = repository.save(entity);
        return createSuccessResponse((REQ) saved, request);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BaseResponse<REQ>> findById(ID id) {
        return repository.findById(id)
               .map(entity -> createSuccessResponse((REQ) entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BaseResponse<REQ>> findAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size))
               .stream()
               .map(entity -> createSuccessResponse((REQ) entity))
               .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BaseResponse<REQ>> findAll() {
        return repository.findAll()
               .stream()
               .map(entity -> createSuccessResponse((REQ) entity))
               .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BaseResponse<REQ> update(ID id, BaseRequest<REQ> request) {
    	REQ existing = repository.findById(id).orElseThrow();
        updateEntity(existing, request.getRequestBody());
        REQ updated = repository.save(existing);
        return createSuccessResponse((REQ) updated, request);
    }

    @Override
    @Transactional
    public BaseResponse<REQ> delete(ID id) {
        return repository.findById(id)
               .map(entity -> {
                   repository.delete(entity);
                   return createSuccessResponse((REQ) entity);
               })
               .orElseThrow();
    }

    // ===== PROTECTED HELPERS =====
    protected REQ convertToEntity(REQ request) {
        return (REQ) request;
    }

    protected void updateEntity(REQ existing, REQ updates) {
        org.springframework.beans.BeanUtils.copyProperties(updates, existing, "id");
    }

    protected BaseResponse<REQ> createSuccessResponse(REQ data) {
        BaseResponse<REQ> response = new BaseResponse<>();
        response.setResponseBody(data);
        
        BaseResponse.ResponseMetadata metadata = new BaseResponse.ResponseMetadata();
        metadata.setSuccess(true);
        metadata.setTimestamp(Instant.now());
        response.setMetadata(metadata);
        return response;
    }

    protected BaseResponse<REQ> createSuccessResponse(REQ data, BaseRequest<?> request) {
        BaseResponse<REQ> response = createSuccessResponse(data);
        if (request != null && request.getMetadata() != null) {
            response.getMetadata().setCorrelationId(request.getMetadata().getCorrelationId());
        }
        return response;
    }
}