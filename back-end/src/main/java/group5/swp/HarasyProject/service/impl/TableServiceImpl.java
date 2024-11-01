package group5.swp.HarasyProject.service.impl;

import group5.swp.HarasyProject.dto.request.table.TableRequest;
import group5.swp.HarasyProject.dto.response.ApiResponse;
import group5.swp.HarasyProject.dto.response.table.TableResponse;
import group5.swp.HarasyProject.entity.branch.TableEntity;
import group5.swp.HarasyProject.enums.ErrorCode;
import group5.swp.HarasyProject.enums.TableStatus;
import group5.swp.HarasyProject.exception.AppException;
import group5.swp.HarasyProject.mapper.TableMapper;
import group5.swp.HarasyProject.repository.TableRepository;
import group5.swp.HarasyProject.service.TableService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {


    TableRepository tableRepository;
    TableMapper tableMapper;


    @Override
    public ApiResponse<TableResponse> updateTable(int tableId, TableRequest request) {
        TableEntity tableEntity = tableRepository.findById(tableId)
                .orElseThrow(()-> new AppException(ErrorCode.TABLE_NOT_FOUND));
        tableEntity = tableMapper.toTable(request,tableEntity);
        tableEntity = tableRepository.save(tableEntity);
        TableResponse tableResponse = tableMapper.toResponse(tableEntity);
        return ApiResponse.<TableResponse>builder()
                .data(tableResponse)
                .build();
    }

    @Override
    public ApiResponse<?> deleteTable(int tableId) {
        TableEntity tableEntity = tableRepository.findById(tableId)
                .orElseThrow(()-> new AppException(ErrorCode.TABLE_NOT_FOUND));
        tableEntity.setStatus(TableStatus.DELETED);
        tableRepository.save(tableEntity);
        return ApiResponse.<TableResponse>builder()
                .build();
    }
}
