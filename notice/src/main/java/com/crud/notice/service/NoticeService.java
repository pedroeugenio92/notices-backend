package com.crud.notice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.crud.notice.converter.NoticeConverter;
import com.crud.notice.dto.NoticeDTO;
import com.crud.notice.model.Notice;
import com.crud.notice.repository.NoticeRepository;


@Service("noticeService")
public class NoticeService {

	
	@Autowired
	private NoticeRepository noticeRepository;
	
	public List<NoticeDTO> filter() {
		List<Notice> notices = noticeRepository.findAll();
		List<NoticeDTO> noticesDTO = NoticeConverter.toListDTO(notices);

		return noticesDTO;
	}
	
	public Optional<NoticeDTO> findById(long id) {
		Optional<Notice> notice = noticeRepository.findById(id);
		Optional<NoticeDTO> resultNotice = Optional.empty();
		if(notice.isPresent()) {
			resultNotice = Optional.of(NoticeConverter.toDTO(notice.get()));
		}
		return resultNotice;
	}
	
	public NoticeDTO save(NoticeDTO noticeDTO) {
		Notice notice = NoticeConverter.fromDTO(noticeDTO);
		return NoticeConverter.toDTO(noticeRepository.save(notice));
	}
	
	public NoticeDTO update(long id,NoticeDTO noticeUpdateDTO , NoticeDTO oldNotice) {
		
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setId(id);
		
		if((noticeUpdateDTO.getTitle()!= null && !noticeUpdateDTO.getTitle().isEmpty()) 
				&& (oldNotice.getTitle() != noticeUpdateDTO.getTitle() )) {
			noticeDTO.setTitle(noticeUpdateDTO.getTitle());
		}
		
		if((noticeUpdateDTO.getDescription()!= null && !noticeUpdateDTO.getDescription().isEmpty()) 
				&& (oldNotice.getDescription() != noticeUpdateDTO.getDescription() )) {
			noticeDTO.setDescription(noticeUpdateDTO.getDescription());
		}
		
		if((noticeUpdateDTO.getDate_publish()!= null && !noticeUpdateDTO.getDate_publish().toString().isEmpty()) 
				&& (oldNotice.getDate_publish() != noticeUpdateDTO.getDate_publish() )) {
			noticeDTO.setDate_publish(noticeUpdateDTO.getDate_publish());
		}
		
		if((noticeUpdateDTO.getDate_viewed()!= null && !noticeUpdateDTO.getDate_viewed().toString().isEmpty()) 
				&& (oldNotice.getDate_viewed() != noticeUpdateDTO.getDate_viewed() )) {
			noticeDTO.setDate_viewed(noticeUpdateDTO.getDate_viewed());
		}
		
		Notice notice = NoticeConverter.fromDTO(noticeDTO);
		return NoticeConverter.toDTO(noticeRepository.save(notice));
		
	}
	
	public void delete(long id) {
		noticeRepository.deleteById(id);
	}

}
