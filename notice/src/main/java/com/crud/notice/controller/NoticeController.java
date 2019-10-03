package com.crud.notice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.notice.dto.NoticeDTO;
import com.crud.notice.model.Notice;
import com.crud.notice.service.NoticeService;



@RestController
@RequestMapping({"/notices"})
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NoticeController {
	
//	private NoticeService noticeService;
//
//	NoticeController(NoticeService noticeService) {
//	       this.noticeService = noticeService;
//	}
	
	@Autowired
    private NoticeService noticeService;

	
	@GetMapping
	public List<NoticeDTO> findAll(){
	   return noticeService.filter();
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Optional<NoticeDTO>> findById(@PathVariable long id){
		Optional<NoticeDTO> noticeDTO = noticeService.findById(id);
		
		if(noticeDTO.isPresent()) {
            return ResponseEntity.ok().body(noticeDTO);

		}else {
            return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public NoticeDTO create(@RequestBody NoticeDTO noticeDTO){
	   return noticeService.save(noticeDTO);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<NoticeDTO> update(@PathVariable("id") long id,
            @RequestBody NoticeDTO noticeUpdateDTO) {
		
		Optional<NoticeDTO> oldNotice = noticeService.findById(id);
		if(oldNotice.isPresent()) {
			
			NoticeDTO noticeUpdated = noticeService.update(id,noticeUpdateDTO, oldNotice.get());
			return ResponseEntity.ok().body(noticeUpdated);
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity<?> delete(@PathVariable long id) {
		
		Optional<NoticeDTO> noticeDTO = noticeService.findById(id);
		if(noticeDTO.isPresent()) {
			noticeService.delete(id);
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}

	
	
	
	


}
