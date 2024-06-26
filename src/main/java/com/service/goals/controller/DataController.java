package com.service.goals.controller;

import com.service.goals.dto.DataNodeDTO;
import com.service.goals.service.DataService;
import com.service.goals.utils.Constants;
import com.service.goals.utils.Constants.Endpoints;
import com.service.goals.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(Endpoints.DATANODE_ENDPOINT)
public class DataController {

    @Autowired
    DataService dataService;

    @PostMapping(Endpoints.CREATE_NODE)
    public ResponseEntity<Response<Boolean>> createDataNode(@RequestBody DataNodeDTO dataNodeDTO) {
        try {
            boolean result = dataService.createNode(dataNodeDTO);
            Response<Boolean> response = new Response<>(Constants.SUCCESS, null, HttpStatus.CREATED.value(), result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Response<Boolean> errorResponse = new Response<>(Constants.ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @GetMapping(Endpoints.READ_ALL)
    public ResponseEntity<Response<List<Map<String,Object>>>> getAllRecord() {
        try {
            List<Map<String,Object>> result = dataService.readAllNodes();
            Response<List<Map<String,Object>>> response = new Response<>(Constants.SUCCESS, null, HttpStatus.OK.value(), result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Response<List<Map<String,Object>>> errorResponse = new Response<>(Constants.ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @PatchMapping(Endpoints.UPDATE_NODE)
    public ResponseEntity<Response<Boolean>> updateRecord(@RequestParam Long id,@RequestBody DataNodeDTO dataNodeDTO) {
        try {
            boolean result = dataService.updateNode(id, dataNodeDTO);
            Response<Boolean> response = new Response<>(Constants.SUCCESS, null, HttpStatus.OK.value(), result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Response<Boolean> errorResponse = new Response<>(Constants.ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @GetMapping(Endpoints.READ_BY_ID)
    public ResponseEntity<Response<Map<String,Object>>> getRecord(@RequestParam Long id) {
        try {
            Map<String,Object> result = dataService.readNodeById(id);
            Response<Map<String,Object> > response = new Response<>(Constants.SUCCESS, null, HttpStatus.OK.value(), result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Response<Map<String,Object>> errorResponse = new Response<>(Constants.ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @PostMapping(Endpoints.FILTER_BY)
    public ResponseEntity<Response<List<Map<String, Object>>>> getDataNodesByFilter(@RequestBody Map<String, Object> filter) {
        try {
            List<Map<String, Object>> result = dataService.filterSearch(filter);
            Response<List<Map<String, Object>>> response = new Response<>(Constants.SUCCESS, null, 200, result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Response<List<Map<String, Object>>> errorResponse = new Response<>(Constants.ERROR, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping(Endpoints.DELETE)
    public ResponseEntity<Response<Boolean>> deleteRecord(@RequestParam Long id){
        try {
            boolean result = dataService.retireNode(id);
            Response response = new Response(Constants.SUCCESS,null,200,result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Response response = new Response(Constants.ERROR, e.getMessage(), 500,null);
            return ResponseEntity.ok(response);
        }
    }

}
