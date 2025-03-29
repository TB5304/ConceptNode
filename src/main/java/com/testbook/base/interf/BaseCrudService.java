package com.testbook.base.interf;
import java.util.List;
import java.util.Optional;

import com.testbook.base.entity.BaseEntity;
import com.testbook.base.entity.BaseRequest;
import com.testbook.base.entity.BaseResponse;

public interface BaseCrudService<ID, REQ extends BaseEntity<?>> {
    
    BaseResponse<REQ> create(BaseRequest<REQ> request);
    
    Optional<BaseResponse<REQ>> findById(ID id);
    
    List<BaseResponse<REQ>> findAll(int page, int size);
    
    List<BaseResponse<REQ>> findAll();

    BaseResponse<REQ> update(ID id, BaseRequest<REQ> request);
    
    BaseResponse<REQ> delete(ID id);
} 